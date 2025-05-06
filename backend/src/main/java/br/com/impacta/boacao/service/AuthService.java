package br.com.impacta.boacao.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.impacta.boacao.dto.request.EmailRequestDTO;
import br.com.impacta.boacao.dto.request.NovaSenhaDTO;
import br.com.impacta.boacao.entity.PasswordRecover;
import br.com.impacta.boacao.entity.Usuario;
import br.com.impacta.boacao.exception.RecursoNaoEncontradoException;
import br.com.impacta.boacao.repository.PasswordRecoverRepository;
import br.com.impacta.boacao.repository.UsuarioRepository;

@Service
public class AuthService {

    @Value("${email.password-recover.token.minutes}")
    private Long tokenMinutes;

    @Value("${email.password-recover.uri}")
    private String recoverUri;

    private final UsuarioRepository usuarioRepository;
    private final PasswordRecoverRepository passwordRecoverRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthService(UsuarioRepository repository, EmailService emailService, PasswordEncoder passwordEncoder,
            PasswordRecoverRepository passwordRecoverRepository) {
        this.usuarioRepository = repository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.passwordRecoverRepository = passwordRecoverRepository;
    }

    @Transactional
    public void criarTokenRecuperacao(EmailRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());
        if (usuario == null) {
            throw new RecursoNaoEncontradoException("Email não encontrado");
        }

        /*
         * Vai gerar um 'token' UUID e enviar um email com o link do frontend
         * com o UUID que tem duração de 30 minutos
         * 
         * exemplo de link gerado no email (rota do front/ + UUID):
         * http://localhost:4200/recuperar-senha/8aeb6b5a-b15c-45b9-a8eb-9a6e1ab65c0e
         */
        PasswordRecover entity = buildPasswordRecover(dto);

        /*
         * Salvando o UUID e Email em uma entidade separada
         * para depois comparar se o usuário que está enviando a nova senha do front
         * tem o mesmo UUID que está no backend, confirmando que é realmente o usuario
         */
        passwordRecoverRepository.save(entity);
        emailService.sendPasswordResetTokenEmail(dto.getEmail(), entity.getToken());
        logger.info("Email enviado com sucesso");
    }

    @Transactional
    public void salvarNovaSenha(NovaSenhaDTO dto) {

        /*
         * verificando se o token não expirou (30 minutos)
         * verifica o horário que o UUID foi salvo e compara com a hora atual
         * essa entidade também traz o email associado ao UUID
         */
        Optional<PasswordRecover> result = passwordRecoverRepository.searchValidToken(dto.getToken(), Instant.now());

        if (result.isEmpty()) {
            throw new RecursoNaoEncontradoException("Token inválido");
        }

        // buscando usuario no banco e alterando a senha já criptografada
        Usuario usuario = usuarioRepository.findByEmail(result.get().getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getPassword()));
        usuario = usuarioRepository.save(usuario);
        logger.info("Senha alterada com sucesso");
    }

    // método para criar uma entidade de recuperação de senha com UUID e email
    private PasswordRecover buildPasswordRecover(EmailRequestDTO body) {
        PasswordRecover entity = new PasswordRecover();
        entity.setEmail(body.getEmail());
        entity.setToken(UUID.randomUUID().toString());
        entity.setExpiration(Instant.now().plusSeconds(tokenMinutes * 60));
        return entity;
    }

}

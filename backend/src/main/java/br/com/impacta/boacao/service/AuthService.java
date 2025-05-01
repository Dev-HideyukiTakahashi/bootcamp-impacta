package br.com.impacta.boacao.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.impacta.boacao.dto.request.EmailRequestDTO;
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

    public AuthService(UsuarioRepository repository, EmailService emailService, PasswordEncoder passwordEncoder,
            PasswordRecoverRepository passwordRecoverRepository) {
        this.usuarioRepository = repository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.passwordRecoverRepository = passwordRecoverRepository;
    }

    @Transactional
    public void createRecoverToken(EmailRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());
        if (usuario == null) {
            throw new RecursoNaoEncontradoException("Email não encontrado");
        }

        PasswordRecover entity = buildPasswordRecover(dto);
        passwordRecoverRepository.save(entity);
        emailService.sendPasswordResetTokenEmail(dto.getEmail(), entity.getToken());
    }

    private PasswordRecover buildPasswordRecover(EmailRequestDTO body) {
        PasswordRecover entity = new PasswordRecover();
        entity.setEmail(body.getEmail());
        entity.setToken(UUID.randomUUID().toString());
        entity.setExpiration(Instant.now().plusSeconds(tokenMinutes * 60));
        return entity;
    }

}

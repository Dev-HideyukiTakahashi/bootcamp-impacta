package br.com.impacta.boacao.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.impacta.boacao.dto.request.VoluntarioRequestDTO;
import br.com.impacta.boacao.dto.response.VoluntarioResponseDTO;
import br.com.impacta.boacao.entity.Role;
import br.com.impacta.boacao.entity.Usuario;
import br.com.impacta.boacao.entity.Voluntario;
import br.com.impacta.boacao.mapper.VoluntarioMapper;
import br.com.impacta.boacao.repository.RoleRepository;
import br.com.impacta.boacao.repository.UsuarioRepository;
import br.com.impacta.boacao.repository.VoluntarioRepository;


@Service
public class VoluntarioService {

    private final VoluntarioRepository voluntarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;



    @Autowired
    public VoluntarioService(VoluntarioRepository voluntarioRepository,
                         RoleRepository roleRepository,
                         UsuarioRepository usuarioRepository,
                         PasswordEncoder passwordEncoder) {
    this.voluntarioRepository = voluntarioRepository;
    this.roleRepository = roleRepository;
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
}

    /**
     * Cadastra um novo voluntário.
     * @param dto dados vindos da camada de API
     * @return DTO de resposta, já com o ID gerado
     */

    @Transactional
    public VoluntarioResponseDTO salvar(VoluntarioRequestDTO dto) {

        // Validação de campos obrigatórios
        // Aqui você pode validar se os campos obrigatórios no DTO estão preenchidos corretamente.
        // (como já feito)
        if (dto.getNomeCompleto() == null || dto.getNomeCompleto().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome completo é obrigatório.");
        }
        if (dto.getCpf() == null || dto.getCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("O preenchimento do CPF é obrigatório.");
        }
        if (dto.getTelefone() == null || dto.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("O preenchimento do telefone é obrigatório.");
        }
        if (dto.getDataNascimento() == null) {
            throw new IllegalArgumentException("O preenchimento da data de nascimento é obrigatória.");
        }
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("O preenchimento do e-mail é obrigatório.");
        }
        // Verificação de CPF duplicado
        // Antes de criar um novo voluntário, verifica se já existe um registro com o mesmo CPF.
        if (voluntarioRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("Já existe um voluntário cadastrado com este CPF.");
        }
        // verificação do email na usuarioRepository
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Já existe um usuário cadastrado com este e-mail.");
        }
        // Buscar o Role do tipo VOLUNTARIO
        // Obtém a Role "ROLE_VOLUNTARIO" do banco de dados. Caso não exista, lança uma exceção.
        Role roleVoluntario = roleRepository.findByAuthority("ROLE_VOLUNTARIO")
            .orElseThrow(() -> new IllegalArgumentException("Role ROLE_VOLUNTARIO não encontrada"));

        // Criar a data de criação
        // Gera um timestamp com a data e hora atuais para registrar quando o usuário foi criado.
        Timestamp agora = new Timestamp(System.currentTimeMillis());

        // Criar o usuário
        // Criptografa a senha fornecida no DTO e cria um novo objeto Usuario com o e-mail e a senha criptografada.
        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
        Usuario novoUsuario = new Usuario(dto.getEmail(), senhaCriptografada);
        novoUsuario.setCriadoEm(agora); // Define a data de criação.
        novoUsuario.setRole(roleVoluntario); // Associa a Role "ROLE_VOLUNTARIO" ao usuário.

        // Mapear o Voluntário e vincular o usuário
        // Converte o DTO para a entidade Voluntario e associa o usuário recém-criado a ele.
        Voluntario entidade = VoluntarioMapper.toEntity(dto);
        entidade.setUsuario(novoUsuario);

        // Salvar o Voluntário (e o Usuário junto)
        // Persiste o voluntário (e o usuário associado) no banco de dados.
        Voluntario salvo = voluntarioRepository.save(entidade);

        // Retorna o DTO de resposta
        // Converte a entidade salva para um DTO de resposta e o retorna.
        return VoluntarioMapper.toResponse(salvo);
    }

}


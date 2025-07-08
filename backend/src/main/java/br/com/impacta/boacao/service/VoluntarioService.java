package br.com.impacta.boacao.service;

import java.sql.Timestamp;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.impacta.boacao.dto.request.VoluntarioRequestDTO;
import br.com.impacta.boacao.dto.request.VoluntarioUpdateRequestDTO;
import br.com.impacta.boacao.dto.response.DadosVoluntarioResponseDTO;
import br.com.impacta.boacao.dto.response.PerfilVoluntarioResponseDTO;
import br.com.impacta.boacao.dto.response.VoluntarioResponseDTO;
import br.com.impacta.boacao.entity.Endereco;
import br.com.impacta.boacao.entity.Role;
import br.com.impacta.boacao.entity.Usuario;
import br.com.impacta.boacao.entity.Voluntario;
import br.com.impacta.boacao.exception.RecursoNaoEncontradoException;
import br.com.impacta.boacao.mapper.VoluntarioMapper;
import br.com.impacta.boacao.repository.RoleRepository;
import br.com.impacta.boacao.repository.UsuarioRepository;
import br.com.impacta.boacao.repository.VoluntarioRepository;

@Service
public class VoluntarioService {

    private final VoluntarioRepository voluntarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final VoluntarioMapper mapper;
    private final AvaliacaoService avaliacaoService;

    public VoluntarioService(VoluntarioRepository voluntarioRepository, UsuarioRepository usuarioRepository,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder, VoluntarioMapper mapper,
            AvaliacaoService avaliacaoService) {
        this.voluntarioRepository = voluntarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.avaliacaoService = avaliacaoService;
    }

    @Transactional
    public VoluntarioResponseDTO salvar(VoluntarioRequestDTO dto) {
        if (dto.getNomeCompleto() == null || dto.getNomeCompleto().trim().isEmpty())
            throw new IllegalArgumentException("O nome completo é obrigatório.");
        if (dto.getCpf() == null || dto.getCpf().trim().isEmpty())
            throw new IllegalArgumentException("O preenchimento do CPF é obrigatório.");
        if (dto.getTelefone() == null || dto.getTelefone().trim().isEmpty())
            throw new IllegalArgumentException("O preenchimento do telefone é obrigatório.");
        if (dto.getDataNascimento() == null)
            throw new IllegalArgumentException("O preenchimento da data de nascimento é obrigatório.");
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty())
            throw new IllegalArgumentException("O preenchimento do e-mail é obrigatório.");
        if (voluntarioRepository.existsByCpf(dto.getCpf()))
            throw new IllegalArgumentException("Já existe um voluntário cadastrado com este CPF.");
        if (usuarioRepository.existsByEmail(dto.getEmail()))
            throw new IllegalArgumentException("Já existe um usuário cadastrado com este e-mail.");

        Role roleVoluntario = roleRepository.findByAuthority("ROLE_VOLUNTARIO")
                .orElseThrow(() -> new IllegalArgumentException("Role ROLE_VOLUNTARIO não encontrada"));

        Timestamp agora = new Timestamp(System.currentTimeMillis());

        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
        Usuario novoUsuario = new Usuario(dto.getEmail(), senhaCriptografada);
        novoUsuario.setCriadoEm(agora);
        novoUsuario.setRole(roleVoluntario);

        Voluntario entidade = mapper.toEntity(dto);
        entidade.setUsuario(novoUsuario);

        Voluntario salvo = voluntarioRepository.save(entidade);

        return mapper.toResponse(salvo);
    }

    @Transactional(readOnly = true)
    public PerfilVoluntarioResponseDTO getMeuPerfil(Authentication auth) {
        String email = ((JwtAuthenticationToken) auth).getToken().getClaim("username");
        Voluntario v = voluntarioRepository.findByUsuarioEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Voluntário não encontrado"));

        int media = avaliacaoService.getMediaAvaliacao(v.getHistoricoAtividades());
        return mapper.toPerfilVoluntarioDTO(v, media);
    }

    @Transactional(readOnly = true)
    public DadosVoluntarioResponseDTO getDadosVoluntario(Authentication auth) {
        String email = ((JwtAuthenticationToken) auth).getToken().getClaim("username");
        Voluntario v = voluntarioRepository.findByUsuarioEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Voluntário não encontrado"));

        return mapper.toDadosVoluntarioDTO(v);
    }

    @Transactional
    public void atualizarVoluntario(Authentication auth, VoluntarioUpdateRequestDTO dto) {
        String email = ((JwtAuthenticationToken) auth).getToken().getClaim("username");
        Voluntario v = voluntarioRepository.findByUsuarioEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Voluntário não encontrado"));

        // Atualiza telefone se veio preenchido
        if (dto.getTelefone() != null && !dto.getTelefone().isBlank()) {
            v.setTelefone(dto.getTelefone());
        }

        if (dto.getEndereco() != null) {
            Endereco endereco = v.getEndereco();
            if (endereco == null) {
                endereco = new Endereco();
                v.setEndereco(endereco);
            }
            mapper.updateEnderecoFromDto(dto.getEndereco(), endereco);
        }

        // Atualiza senha se fornecida, validando se é diferente da atual
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            String senhaAtualCriptografada = v.getUsuario().getSenha();
            if (passwordEncoder.matches(dto.getSenha(), senhaAtualCriptografada)) {
                throw new IllegalArgumentException("A nova senha não pode ser igual à senha atual.");
            }
            v.getUsuario().setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        voluntarioRepository.save(v);
    }

}

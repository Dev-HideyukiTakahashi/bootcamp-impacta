package br.com.impacta.boacao.service;

import java.sql.Timestamp;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.impacta.boacao.dto.request.OngRequestDTO;
import br.com.impacta.boacao.dto.request.OngUpdateRequestDTO;
import br.com.impacta.boacao.dto.response.DadosOngResponseDTO;
import br.com.impacta.boacao.dto.response.OngResponseDTO;
import br.com.impacta.boacao.dto.response.PerfilOngResponseDTO;
import br.com.impacta.boacao.entity.Endereco;
import br.com.impacta.boacao.entity.Ong;
import br.com.impacta.boacao.entity.Role;
import br.com.impacta.boacao.entity.Usuario;
import br.com.impacta.boacao.exception.RecursoNaoEncontradoException;
import br.com.impacta.boacao.mapper.OngMapper;
import br.com.impacta.boacao.repository.OngRepository;
import br.com.impacta.boacao.repository.RoleRepository;
import br.com.impacta.boacao.repository.UsuarioRepository;

@Service
public class OngService {

    private final OngRepository ongRepository;
    private final RoleRepository roleRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final OngMapper mapper;

    public OngService(OngRepository ongRepository,
            RoleRepository roleRepository,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            OngMapper mapper) {
        this.ongRepository = ongRepository;
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Transactional
    public OngResponseDTO salvar(OngRequestDTO dto) {
        // validações...
        if (dto.getNomeEntidade() == null || dto.getNomeEntidade().trim().isEmpty())
            throw new IllegalArgumentException("O nome da entidade é obrigatório.");
        if (dto.getCnpj() == null || dto.getCnpj().trim().isEmpty())
            throw new IllegalArgumentException("O preenchimento do CNPJ é obrigatório.");
        if (dto.getTelefone() == null || dto.getTelefone().trim().isEmpty())
            throw new IllegalArgumentException("O preenchimento do telefone é obrigatório.");
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty())
            throw new IllegalArgumentException("O preenchimento do e-mail é obrigatório.");
        if (ongRepository.existsByCnpj(dto.getCnpj()))
            throw new IllegalArgumentException("Já existe uma ONG cadastrada com este CNPJ.");
        if (usuarioRepository.existsByEmail(dto.getEmail()))
            throw new IllegalArgumentException("Já existe um usuário cadastrado com este e-mail.");

        Role roleOng = roleRepository.findByAuthority("ROLE_ONG")
                .orElseThrow(() -> new IllegalArgumentException("Role ROLE_ONG não encontrada"));

        Timestamp agora = new Timestamp(System.currentTimeMillis());
        String senhaCrip = passwordEncoder.encode(dto.getSenha());
        Usuario novoUsuario = new Usuario(dto.getEmail(), senhaCrip);
        novoUsuario.setCriadoEm(agora);
        novoUsuario.setRole(roleOng);

        Ong entidade = mapper.toEntity(dto);
        entidade.setUsuario(novoUsuario);

        Ong salvo = ongRepository.save(entidade);
        return mapper.toResponse(salvo);
    }

    public PerfilOngResponseDTO getMeuPerfil(Authentication auth) {
        String email = ((JwtAuthenticationToken) auth).getToken().getClaim("username");
        Ong ong = ongRepository.findByUsuarioEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("ONG não encontrada"));
        return mapper.toPerfilOngDTO(ong);
    }

    @Transactional
    public DadosOngResponseDTO getDadosOng(Authentication auth) {
        String email = ((JwtAuthenticationToken) auth).getToken().getClaim("username");
        Ong ong = ongRepository.findByUsuarioEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("ONG não encontrada"));
        return mapper.toDadosOngDTO(ong);
    }

    @Transactional
    public void atualizarOng(Authentication auth, OngUpdateRequestDTO dto) {
        String email = ((JwtAuthenticationToken) auth).getToken().getClaim("username");
        Ong ong = ongRepository.findByUsuarioEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("ONG não encontrada"));

        // Atualiza telefone se veio preenchido
        if (dto.getTelefone() != null && !dto.getTelefone().isBlank()) {
            ong.setTelefone(dto.getTelefone());
        }

        // Atualiza endereço apenas se veio endereço no DTO
        if (dto.getEndereco() != null) {
            Endereco endereco = ong.getEndereco();
            if (endereco == null) {
                endereco = new Endereco();
                ong.setEndereco(endereco);
            }
            mapper.updateEnderecoFromDto(dto.getEndereco(), endereco);
        }

        // Atualiza senha se fornecida e diferente da atual
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            String senhaAtualCriptografada = ong.getUsuario().getSenha();
            if (passwordEncoder.matches(dto.getSenha(), senhaAtualCriptografada)) {
                throw new IllegalArgumentException("A nova senha não pode ser igual à senha atual.");
            }
            ong.getUsuario().setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        ongRepository.save(ong);
    }

}
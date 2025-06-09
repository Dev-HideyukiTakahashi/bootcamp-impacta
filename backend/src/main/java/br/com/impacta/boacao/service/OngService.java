package br.com.impacta.boacao.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.impacta.boacao.dto.request.OngRequestDTO;
import br.com.impacta.boacao.dto.response.OngResponseDTO;
import br.com.impacta.boacao.entity.Ong;
import br.com.impacta.boacao.entity.Role;
import br.com.impacta.boacao.entity.Usuario;
import br.com.impacta.boacao.mapper.OngMapper;
import br.com.impacta.boacao.repository.OngRepository;
import br.com.impacta.boacao.repository.RoleRepository;
import br.com.impacta.boacao.repository.UsuarioRepository;

@Service
public class OngService {

    private final OngRepository ongRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;


    @Autowired
    public OngService(OngRepository ongRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.ongRepository = ongRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Cadastra uma nova ONG.
     * @param dto dados vindos da camada de API
     * @return DTO de resposta, já com o ID gerado
     */
    @Transactional
    public OngResponseDTO salvar(OngRequestDTO dto) {

        // Validação de campos obrigatórios
        if (dto.getNomeOng() == null || dto.getNomeOng().isEmpty()) {
            throw new IllegalArgumentException("O campo nomeOng é obrigatório.");
        }
        if (dto.getCnpj() == null || dto.getCnpj().isEmpty()) {
            throw new IllegalArgumentException("O campo CNPJ é obrigatório.");
        }
        if (dto.getTelefone() == null || dto.getTelefone().isEmpty()) {
            throw new IllegalArgumentException("O campo telefone é obrigatório.");
        }
        if (dto.getEndereco() == null || dto.getEndereco().isEmpty()) {
            throw new IllegalArgumentException("O campo endereço é obrigatório.");
        }
        if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O campo email é obrigatório.");
        }
        if (dto.getSenha() == null || dto.getSenha().isEmpty()) {
            throw new IllegalArgumentException("O campo senha é obrigatório.");
        }

        // Verificação de CNPJ duplicado
        if (ongRepository.existsByCnpj(dto.getCnpj())) {
            throw new IllegalArgumentException("Já existe uma ONG cadastrada com este CNPJ.");
        }
        // verificação do email na usuarioRepository
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Já existe um usuário cadastrado com este e-mail.");
        }

        // Buscar o Role do tipo ONG
        Role roleOng = roleRepository.findByAuthority("ROLE_ONG")
            .orElseThrow(() -> new IllegalArgumentException("Role ROLE_ONG não encontrada"));

        // Criar a data de criação
        Timestamp agora = new Timestamp(System.currentTimeMillis());

        // Criar o usuário
        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
        Usuario novoUsuario = new Usuario(dto.getEmail(), senhaCriptografada);
        novoUsuario.setCriadoEm(agora);
        novoUsuario.setRole(roleOng);

        // Mapear a ONG e vincular o usuário
        Ong entidade = OngMapper.toEntity(dto);
        entidade.setUsuario(novoUsuario);

        // Salvar a ONG (e o Usuário junto)
        Ong salvo = ongRepository.save(entidade);

        return OngMapper.toResponse(salvo);
    }
}

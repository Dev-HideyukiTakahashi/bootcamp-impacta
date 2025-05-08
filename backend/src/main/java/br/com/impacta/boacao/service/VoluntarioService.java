package br.com.impacta.boacao.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.impacta.boacao.dto.request.VoluntarioRequestDTO;
import br.com.impacta.boacao.dto.response.VoluntarioResponseDTO;
import br.com.impacta.boacao.entity.Voluntario;
import br.com.impacta.boacao.mapper.VoluntarioMapper;
import br.com.impacta.boacao.repository.VoluntarioRepository;

@Service
public class VoluntarioService {

    private final VoluntarioRepository voluntarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public VoluntarioService(VoluntarioRepository voluntarioRepository) {
        this.voluntarioRepository = voluntarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Cadastra um novo voluntário.
     * @param dto dados vindos da camada de API
     * @return DTO de resposta, já com o ID gerado
     */
    @Transactional
    public VoluntarioResponseDTO salvar(VoluntarioRequestDTO dto) {
        // 1) verifica duplicidades
        if (voluntarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Já existe um voluntário cadastrado com este e-mail.");
        }
        if (voluntarioRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("Já existe um voluntário cadastrado com este CPF.");
        }

        // 2) converte DTO → entidade
        Voluntario entidade = VoluntarioMapper.toEntity(dto);;  // ou toEntity, conforme seu mapper

        // 3) criptografa a senha
        entidade.setSenha(passwordEncoder.encode(entidade.getSenha()));

        // 4) salva no banco
        Voluntario salvo = voluntarioRepository.save(entidade);

        // 5) converte entidade → DTO de resposta
        return VoluntarioMapper.toResponse(salvo);
    }
}

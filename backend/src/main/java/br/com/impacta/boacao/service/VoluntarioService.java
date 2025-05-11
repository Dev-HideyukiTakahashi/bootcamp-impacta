package br.com.impacta.boacao.service;

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
    //private final BCryptPasswordEncoder passwordEncoder;

    public VoluntarioService(VoluntarioRepository voluntarioRepository) {
        this.voluntarioRepository = voluntarioRepository;
        //this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Cadastra um novo voluntário.
     * @param dto dados vindos da camada de API
     * @return DTO de resposta, já com o ID gerado
     */
    @Transactional
    public VoluntarioResponseDTO salvar(VoluntarioRequestDTO dto) {


        // VALIDACAO DOS 
        if (dto.getNomeCompleto() == null || dto.getNomeCompleto().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome completo é obrigatório.");
        }

        if (dto.getCpf() == null || dto.getCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("O preenchimento do CPF é obrigatório.");
        }

        if (dto.getCep() == null || dto.getCep().trim().isEmpty()) {
            throw new IllegalArgumentException("O preenchimento do CEP é obrigatório.");
        }

        if (dto.getRua() == null || dto.getRua().trim().isEmpty()) {
            throw new IllegalArgumentException("O preenchimento do endereço é obrigatório.");
        }
        
        if (dto.getNumero() == null || dto.getNumero().trim().isEmpty()) {
            throw new IllegalArgumentException("O preenchimento do  número do endereço é obrigatório.");
        }

        if (dto.getCidade() == null || dto.getCidade().trim().isEmpty()) {
            throw new IllegalArgumentException("O preenchimento da cidade é obrigatória.");
        }

        if (dto.getEstado() == null || dto.getEstado().trim().isEmpty()) {
            throw new IllegalArgumentException("O preenchimento do estado é obrigatório.");
        }

        if (dto.getTelefone() == null || dto.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("O preenchimento do telefone é obrigatório.");
        }

        if (dto.getDataNascimento() == null) {
            throw new IllegalArgumentException("O preenchimento da data de nascimento é obrigatória.");
}

        // VERIFICACAO DO CPF DUPLICADO
        if (voluntarioRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("Usuário já cadastrado.");
        }

        // 2) converte DTO → entidade
        Voluntario entidade = VoluntarioMapper.toEntity(dto);;  // ou toEntity, conforme seu mapper

        // 3) criptografa a senha
        //entidade.setSenha(passwordEncoder.encode(entidade.getSenha()));

        // 4) salva no banco
        Voluntario salvo = voluntarioRepository.save(entidade);

        // 5) converte entidade → DTO de resposta
        return VoluntarioMapper.toResponse(salvo);
    }
}

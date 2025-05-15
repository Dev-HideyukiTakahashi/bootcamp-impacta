package br.com.impacta.boacao.service;

import br.com.impacta.boacao.dto.request.AtividadeStatusRequestDTO;
import br.com.impacta.boacao.dto.response.AtividadeOngResponseDTO;
import br.com.impacta.boacao.dto.response.AtividadeStatusResponseDTO;
import br.com.impacta.boacao.entity.Atividade;
import br.com.impacta.boacao.mapper.AtividadeMapper;
import br.com.impacta.boacao.repository.AtividadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtividadeService {

    private final AtividadeRepository atividadeRepository;
    private final Logger logger = LoggerFactory.getLogger(AtividadeService.class);

    public AtividadeService(AtividadeRepository atividadeRepository) {
        this.atividadeRepository = atividadeRepository;
    }

    @Transactional(readOnly = true)
    public Page<AtividadeOngResponseDTO> buscarTodos(Pageable pageable){
        return atividadeRepository.buscarTodosPage(pageable);
    }

    @Transactional
    public AtividadeStatusResponseDTO atualizarStatus(Integer id, AtividadeStatusRequestDTO dto){
        Atividade entidade = atividadeRepository.getReferenceById(id);
        entidade.setStatusAtividade(dto.getStatusAtividade());

        logger.info("Status da ong atualizado para: {}", entidade.getStatusAtividade());
        return AtividadeMapper.toOngResponseDTO(entidade);
    }

}

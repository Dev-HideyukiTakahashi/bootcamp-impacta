package br.com.impacta.boacao.service;

import br.com.impacta.boacao.dto.response.AtividadesOngResponseDTO;
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
    public Page<AtividadesOngResponseDTO> buscarTodos(Pageable pageable){
        return atividadeRepository.buscarTodosPage(pageable);
    }
}

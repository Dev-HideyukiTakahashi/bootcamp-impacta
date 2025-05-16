package br.com.impacta.boacao.service;

import br.com.impacta.boacao.dto.request.AtividadeRequestDTO;
import br.com.impacta.boacao.dto.request.AtividadeStatusRequestDTO;
import br.com.impacta.boacao.dto.response.AtividadeOngResponseDTO;
import br.com.impacta.boacao.dto.response.AtividadeResponseDTO;
import br.com.impacta.boacao.dto.response.AtividadeStatusResponseDTO;
import br.com.impacta.boacao.entity.Atividade;
import br.com.impacta.boacao.entity.enums.StatusAtividade;
import br.com.impacta.boacao.exception.DatabaseException;
import br.com.impacta.boacao.exception.RecursoNaoEncontradoException;
import br.com.impacta.boacao.mapper.AtividadeMapper;
import br.com.impacta.boacao.repository.AtividadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

    @Transactional
    public AtividadeResponseDTO cadastrar(AtividadeRequestDTO dto){
        dto.setStatusAtividade(StatusAtividade.ANDAMENTO);
        Atividade entidade = atividadeRepository.save(AtividadeMapper.toEntity(dto));

        logger.info("Atividade: {}, foi criada", entidade.getNome());
        return AtividadeMapper.toDTO(entidade);
    }

    @Transactional
        public AtividadeResponseDTO atualizar(Integer id, AtividadeRequestDTO dto){
        try{
            Atividade entidade = atividadeRepository.getReferenceById(id);
            dto.setId(entidade.getId());
            dto.setCriadoEm(entidade.getCriadoEm());
            entidade = atividadeRepository.save(AtividadeMapper.toEntity(dto));

            logger.info("Atividade: {}, foi atualizada", entidade.getNome());
            return AtividadeMapper.toDTO(entidade);
        }catch(EntityNotFoundException e){
            throw new RecursoNaoEncontradoException("Atividade não existe id: "+ id);
        }
    }

    // annotation necessaria para propagar error do java, sem ela vai lançar erro de sql
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletar(Integer id){

        if(!atividadeRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Atividade não existe id: "+ id);
        }

        try{
            atividadeRepository.deleteById(id);
            logger.info("Atividade deletada, id: {}", id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException("Não foi possível excluir a atividade pois ela está vinculada a outros registros.");
        }
    }

}

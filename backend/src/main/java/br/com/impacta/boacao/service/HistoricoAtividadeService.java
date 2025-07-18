package br.com.impacta.boacao.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.impacta.boacao.dto.request.HistoricoAtividadeRequestDTO;
import br.com.impacta.boacao.dto.response.HistoricoAtividadeDTO;
import br.com.impacta.boacao.dto.response.HistoricoAtividadeResponseDTO;
import br.com.impacta.boacao.dto.response.HistoricoAtividadeTodosResponseDTO;
import br.com.impacta.boacao.entity.enums.StatusCandidatura;

public interface HistoricoAtividadeService {
    /**
     * Retorna o histórico de voluntários aprovados para a atividade informada.
     *
     * @param request DTO contendo o ID da atividade
     * @return DTO com quantidade e lista de voluntários aprovados
     */
    HistoricoAtividadeResponseDTO buscarHistorico(HistoricoAtividadeRequestDTO request);

    // retorna lista de voluntarios inscritos para a atividade (sem filtro pelo
    // status de aprovado, devem ter todos)
    HistoricoAtividadeTodosResponseDTO listaTodosVoluntariosInscritos(Integer atividadeId);

    Page<HistoricoAtividadeDTO> buscarTodos(Pageable pageable);

    Page<HistoricoAtividadeDTO> buscarTodosPorData(String encerradoEm, Pageable pageable);
    void atualizarStatusCandidatura(HistoricoAtividadeRequestDTO request, StatusCandidatura status);


}

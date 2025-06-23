package br.com.impacta.boacao.service;

import br.com.impacta.boacao.dto.request.HistoricoAtividadeRequestDTO;
import br.com.impacta.boacao.dto.response.HistoricoAtividadeResponseDTO;
import br.com.impacta.boacao.dto.response.HistoricoAtividadeTodosResponseDTO;


public interface HistoricoAtividadeService {
    /**
     * Retorna o histórico de voluntários aprovados para a atividade informada.
     *
     * @param request DTO contendo o ID da atividade
     * @return DTO com quantidade e lista de voluntários aprovados
     */
    HistoricoAtividadeResponseDTO buscarHistorico(HistoricoAtividadeRequestDTO request);

    // retorna lista  de voluntarios inscritos para a atividade (sem filtro pelo status de aprovado, devem ter todos)
    HistoricoAtividadeTodosResponseDTO listarTodosHistorico(HistoricoAtividadeRequestDTO request);

}

package br.com.impacta.boacao.dto.response;

import java.util.List;

/**
 * DTO que agrupa todos os voluntários vinculados a uma atividade,
 * independente do status de cada inscrição.
 */
public class HistoricoAtividadeTodosResponseDTO {

    private Integer quantidade;
    private List<VoluntarioHistoricoResponseDTO> voluntarios;

    /**
     * @param quantidade  número total de voluntários vinculados
     * @param voluntarios lista de DTOs com id, nomeCompleto e statusCandidatura
     */
    public HistoricoAtividadeTodosResponseDTO(Integer quantidade,
                                              List<VoluntarioHistoricoResponseDTO> voluntarios) {
        this.quantidade = quantidade;
        this.voluntarios = voluntarios;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public List<VoluntarioHistoricoResponseDTO> getVoluntarios() {
        return voluntarios;
    }
}

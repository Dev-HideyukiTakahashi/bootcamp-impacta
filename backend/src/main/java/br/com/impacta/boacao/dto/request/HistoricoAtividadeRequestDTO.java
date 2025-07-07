package br.com.impacta.boacao.dto.request;

public class HistoricoAtividadeRequestDTO {

    private Integer atividadeId;
    private Integer voluntarioId;

    /**
     * Construtor padrão necessário para o Spring converter o JSON em DTO.
     */
    public HistoricoAtividadeRequestDTO() {
    }

    public HistoricoAtividadeRequestDTO(Integer atividadeId, Integer voluntarioId) {
        this.atividadeId = atividadeId;
        this.voluntarioId = voluntarioId;
    }

    public HistoricoAtividadeRequestDTO(Integer atividadeId) {
        this.atividadeId = atividadeId;
    }

    public Integer getAtividadeId() {
        return atividadeId;
    }

    public void setAtividadeId(Integer atividadeId) {
        this.atividadeId = atividadeId;
    }

    public Integer getVoluntarioId() {
        return voluntarioId;
    }

    public void setVoluntarioId(Integer voluntarioId) {
        this.voluntarioId = voluntarioId;
    }
}

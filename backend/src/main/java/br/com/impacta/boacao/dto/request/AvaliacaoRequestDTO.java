package br.com.impacta.boacao.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AvaliacaoRequestDTO {

    @NotNull
    private Integer historicoAtividadeId;
    private String feedback;

    @Max(value = 5)
    @Min(value = 0)
    private int estrelas;

    @NotBlank
    private String cargaHoraria;

    public AvaliacaoRequestDTO() {
    }

    public AvaliacaoRequestDTO(Integer historicoAtividadeId, String feedback, int estrelas, String cargaHoraria) {
        this.historicoAtividadeId = historicoAtividadeId;
        this.feedback = feedback;
        this.estrelas = estrelas;
        this.cargaHoraria = cargaHoraria;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public Integer getHistoricoAtividadeId() {
        return historicoAtividadeId;
    }

    public void setHistoricoAtividadeId(Integer historicoAtividadeId) {
        this.historicoAtividadeId = historicoAtividadeId;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

}

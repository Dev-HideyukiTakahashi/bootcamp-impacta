package br.com.impacta.boacao.dto.response;

import br.com.impacta.boacao.entity.enums.StatusCandidatura;

public class VoluntarioAtividadeDTO {

    private String voluntario;
    private StatusCandidatura status;
    private Integer atividadeId;
    

    public VoluntarioAtividadeDTO() {
    }

    public VoluntarioAtividadeDTO(String voluntario, StatusCandidatura status, Integer atividadeId) {
        this.voluntario = voluntario;
        this.status = status;
        this.atividadeId = atividadeId;
    }

    public String getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(String voluntario) {
        this.voluntario = voluntario;
    }

    public StatusCandidatura getStatus() {
        return status;
    }

    public void setStatus(StatusCandidatura status) {
        this.status = status;
    }

    public Integer getAtividadeId() {
        return atividadeId;
    }

    public void setAtividadeId(Integer atividadeId) {
        this.atividadeId = atividadeId;
    }

}

package br.com.impacta.boacao.dto.response;

import br.com.impacta.boacao.entity.enums.StatusAtividade;

import java.util.Objects;

public class AtividadeStatusResponseDTO {

    private Integer id;
    private StatusAtividade statusAtividade;

    public AtividadeStatusResponseDTO() {
    }

    public AtividadeStatusResponseDTO(Integer id, StatusAtividade statusAtividade) {
        this.id = id;
        this.statusAtividade = statusAtividade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatusAtividade getStatusAtividade() {
        return statusAtividade;
    }

    public void setStatusAtividade(StatusAtividade statusAtividade) {
        this.statusAtividade = statusAtividade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AtividadeStatusResponseDTO that = (AtividadeStatusResponseDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

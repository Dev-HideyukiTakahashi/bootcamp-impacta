package br.com.impacta.boacao.dto.response;

import br.com.impacta.boacao.entity.enums.PeriodoAtividade;
import br.com.impacta.boacao.entity.enums.StatusCandidatura;

public class AtividadeMinDTO {

    private Integer id;
    private PeriodoAtividade periodo;
    private String cargaHorariaDiaria;
    private Boolean possuiCertificacao;
    private String descricao;
    private StatusCandidatura statusCandidatura;

    public AtividadeMinDTO() {
    }

    public AtividadeMinDTO(Integer id, PeriodoAtividade periodo, String cargaHorariaDiaria, Boolean possuiCertificacao,
            String descricao, StatusCandidatura statusCandidatura) {
        this.id = id;
        this.periodo = periodo;
        this.cargaHorariaDiaria = cargaHorariaDiaria;
        this.possuiCertificacao = possuiCertificacao;
        this.descricao = descricao;
        this.statusCandidatura = statusCandidatura;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PeriodoAtividade getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoAtividade periodo) {
        this.periodo = periodo;
    }

    public String getCargaHorariaDiaria() {
        return cargaHorariaDiaria;
    }

    public void setCargaHorariaDiaria(String cargaHorariaDiaria) {
        this.cargaHorariaDiaria = cargaHorariaDiaria;
    }

    public Boolean getPossuiCertificacao() {
        return possuiCertificacao;
    }

    public void setPossuiCertificacao(Boolean possuiCertificacao) {
        this.possuiCertificacao = possuiCertificacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusCandidatura getStatusCandidatura() {
        return statusCandidatura;
    }

    public void setStatusCandidatura(StatusCandidatura statusCandidatura) {
        this.statusCandidatura = statusCandidatura;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((periodo == null) ? 0 : periodo.hashCode());
        result = prime * result + ((cargaHorariaDiaria == null) ? 0 : cargaHorariaDiaria.hashCode());
        result = prime * result + ((possuiCertificacao == null) ? 0 : possuiCertificacao.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((statusCandidatura == null) ? 0 : statusCandidatura.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AtividadeMinDTO other = (AtividadeMinDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (periodo != other.periodo)
            return false;
        if (cargaHorariaDiaria == null) {
            if (other.cargaHorariaDiaria != null)
                return false;
        } else if (!cargaHorariaDiaria.equals(other.cargaHorariaDiaria))
            return false;
        if (possuiCertificacao == null) {
            if (other.possuiCertificacao != null)
                return false;
        } else if (!possuiCertificacao.equals(other.possuiCertificacao))
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (statusCandidatura != other.statusCandidatura)
            return false;
        return true;
    }

}

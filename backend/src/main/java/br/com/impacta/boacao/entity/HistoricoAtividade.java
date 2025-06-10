package br.com.impacta.boacao.entity;

import br.com.impacta.boacao.entity.enums.StatusCandidatura;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
public class HistoricoAtividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date dataInscricao;
    private boolean certificado;

    @Enumerated(EnumType.STRING)
    private StatusCandidatura statusCandidatura;

    @ManyToOne
    private Voluntario voluntario;

    @ManyToOne
    private Atividade atividade;

    public HistoricoAtividade() {
    }

    public HistoricoAtividade(Integer id, Date dataInscricao, boolean certificado, StatusCandidatura statusCandidatura, Voluntario voluntario, Atividade atividade) {
        this.id = id;
        this.dataInscricao = dataInscricao;
        this.certificado = certificado;
        this.statusCandidatura = statusCandidatura;
        this.voluntario = voluntario;
        this.atividade = atividade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(Date dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public boolean isCertificado() {
        return certificado;
    }

    public void setCertificado(boolean certificado) {
        this.certificado = certificado;
    }

    public StatusCandidatura getStatusCandidatura() {
        return statusCandidatura;
    }

    public void setStatusCandidatura(StatusCandidatura statusCandidatura) {
        this.statusCandidatura = statusCandidatura;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoAtividade that = (HistoricoAtividade) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

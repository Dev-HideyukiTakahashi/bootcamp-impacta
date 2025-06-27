package br.com.impacta.boacao.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import br.com.impacta.boacao.entity.enums.StatusCandidatura;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class HistoricoAtividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date dataInscricao;
    private boolean certificado;
    private LocalDate encerradoEm;

    @Enumerated(EnumType.STRING)
    private StatusCandidatura statusCandidatura;

    @OneToOne
    private Avaliacao avaliacao;

    @ManyToOne
    private Voluntario voluntario;

    @ManyToOne
    private Atividade atividade;

    public HistoricoAtividade() {
    }

    public HistoricoAtividade(Integer id, Date dataInscricao, boolean certificado, LocalDate encerradoEm,
            StatusCandidatura statusCandidatura, Avaliacao avaliacao, Voluntario voluntario, Atividade atividade) {
        this.id = id;
        this.dataInscricao = dataInscricao;
        this.certificado = certificado;
        this.encerradoEm = encerradoEm;
        this.statusCandidatura = statusCandidatura;
        this.avaliacao = avaliacao;
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

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getEncerradoEm() {
        return encerradoEm;
    }

    public void setEncerradoEm(LocalDate encerradoEm) {
        this.encerradoEm = encerradoEm;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        HistoricoAtividade that = (HistoricoAtividade) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}

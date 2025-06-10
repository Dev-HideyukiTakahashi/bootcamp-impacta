package br.com.impacta.boacao.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.impacta.boacao.entity.enums.PeriodoAtividade;
import br.com.impacta.boacao.entity.enums.StatusAtividade;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private PeriodoAtividade periodo;

    private String cargaHorariaDiaria;

    private String enderecoCompleto;

    private Boolean possuiCertificacao;

    @Enumerated(EnumType.STRING)
    private StatusAtividade statusAtividade;
    private LocalDateTime criadoEm;

    @ManyToOne
    private Ong ong;

    @OneToMany(mappedBy = "atividade")
    private final List<HistoricoAtividade> historicoAtividades = new ArrayList<>();

    public Atividade() {
    }

    public Atividade(Integer id, String nome, String descricao, PeriodoAtividade periodo, String cargaHorariaDiaria,
            String enderecoCompleto, Boolean possuiCertificacao, StatusAtividade statusAtividade,
            LocalDateTime criadoEm,
            Ong ong) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.periodo = periodo;
        this.cargaHorariaDiaria = cargaHorariaDiaria;
        this.enderecoCompleto = enderecoCompleto;
        this.possuiCertificacao = possuiCertificacao;
        this.statusAtividade = statusAtividade;
        this.criadoEm = criadoEm;
        this.ong = ong;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }

    public Boolean getPossuiCertificacao() {
        return possuiCertificacao;
    }

    public void setPossuiCertificacao(Boolean possuiCertificacao) {
        this.possuiCertificacao = possuiCertificacao;
    }

    public List<HistoricoAtividade> getHistoricoAtividades() {
        return historicoAtividades;
    }

    public void addHistoricoAtividades(HistoricoAtividade historicoAtividade) {
        historicoAtividades.add(historicoAtividade);
    }

    public StatusAtividade getStatusAtividade() {
        return statusAtividade;
    }

    public void setStatusAtividade(StatusAtividade statusAtividade) {
        this.statusAtividade = statusAtividade;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    // Salva a data atual do sistema ao criar uma atividade
    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
    }

    public Ong getOng() {
        return ong;
    }

    public void setOng(Ong ong) {
        this.ong = ong;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Atividade atividade = (Atividade) o;
        return Objects.equals(id, atividade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}

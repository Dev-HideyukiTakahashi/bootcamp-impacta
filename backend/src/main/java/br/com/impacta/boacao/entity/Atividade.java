package br.com.impacta.boacao.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import br.com.impacta.boacao.entity.enums.PeriodoAtividade;
import br.com.impacta.boacao.entity.enums.StatusAtividade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //private Integer idOng;
    private String nome;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private PeriodoAtividade periodo;
    private String cargaHorariaDiaria;
    private String enderecoCompleto;
    private Boolean possuiCertificacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_atividade", nullable = false, length = 20)
    private StatusAtividade statusAtividade;
    
    private LocalDateTime criadoEm;
    private LocalDateTime dataAtividade;
    private String titulo;
/* 
    @OneToMany(mappedBy = "atividade")
    private final List<HistoricoAtividade> historicoAtividades = new ArrayList<>();*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TAG", nullable = false)
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ONG", nullable = false)
    private Ong ong;

    public Atividade() {
    }

    public Atividade(Integer id, Integer idOng, String nome, String descricao, PeriodoAtividade periodo, String cargaHorariaDiaria,
            String enderecoCompleto, Boolean possuiCertificacao, StatusAtividade statusAtividade, LocalDateTime dataAtividade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.periodo = periodo;
        this.cargaHorariaDiaria = cargaHorariaDiaria;
        this.enderecoCompleto = enderecoCompleto;
        this.possuiCertificacao = possuiCertificacao;
        this.statusAtividade = statusAtividade;
        this.dataAtividade = dataAtividade;
        //this.idOng = NULL; // Inicializa com null, será setado posteriormente
        this.criadoEm = LocalDateTime.now();
        this.titulo = nome; // Inicializa o título com o nome da atividade]
        this.tag = null; // Inicializa com null, será setado posteriormente
        this.ong = null; // Inicializa com null, será setado posteriormente
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
/* 
    public List<HistoricoAtividade> getHistoricoAtividades() {
        return historicoAtividades;
    }

    public void addHistoricoAtividades(HistoricoAtividade historicoAtividade) {
        historicoAtividades.add(historicoAtividade);
    }
*/
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

    public LocalDateTime getDataAtividade() {
        return dataAtividade;
    }

    public void setDataAtividade(LocalDateTime dataAtividade) {
        this.dataAtividade = dataAtividade;
    }

    public Ong getOng() {
        return ong;
    }

    public void setOng(Ong ong) {
        this.ong = ong;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Salva a data atual do sistema ao criar uma atividade
    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Atividade atividade = (Atividade) o;
        return Objects.equals(id, atividade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

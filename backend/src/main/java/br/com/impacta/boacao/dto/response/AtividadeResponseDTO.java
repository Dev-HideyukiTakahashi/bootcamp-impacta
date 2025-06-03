package br.com.impacta.boacao.dto.response;

import java.time.LocalDateTime;
import java.util.Objects;

import br.com.impacta.boacao.entity.enums.PeriodoAtividade;
import br.com.impacta.boacao.entity.enums.StatusAtividade;

public class AtividadeResponseDTO {

    private Integer id;
    private Integer idTag;            // 👈 novo
    private String titulo;            // 👈 novo
    private String nome;
    private PeriodoAtividade periodo;
    private String cargaHorariaDiaria;
    private String enderecoCompleto;
    private Boolean possuiCertificacao;
    private String descricao;
    private StatusAtividade statusAtividade;
    private LocalDateTime dataAtividade;

    private Integer idOng;

    public Integer getIdOng() {
        return idOng;
    }

    public void setIdOng(Integer idOng) {
        this.idOng = idOng;
    }

    public AtividadeResponseDTO() {
    }

    public AtividadeResponseDTO(
            Integer id,
            String nome,
            PeriodoAtividade periodo,
            String cargaHorariaDiaria,
            String enderecoCompleto,
            Boolean possuiCertificacao,
            String descricao,
            StatusAtividade statusAtividade,
            LocalDateTime dataAtividade,
            Integer idOng,
            Integer idTag,
            String titulo
    ) {
        this.id = id;
        this.nome = nome;
        this.periodo = periodo;
        this.cargaHorariaDiaria = cargaHorariaDiaria;
        this.enderecoCompleto = enderecoCompleto;
        this.possuiCertificacao = possuiCertificacao;
        this.descricao = descricao;
        this.statusAtividade = statusAtividade;
        this.dataAtividade = dataAtividade;
        this.idOng = idOng;
        this.idTag = idTag;
        this.titulo = titulo;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusAtividade getStatusAtividade() {
        return statusAtividade;
    }

    public void setStatusAtividade(StatusAtividade statusAtividade) {
        this.statusAtividade = statusAtividade;
    }

    public LocalDateTime getDataAtividade() {
        return dataAtividade;
    }

    public void setDataAtividade(LocalDateTime dataAtividade) {
        this.dataAtividade = dataAtividade;
    }


    public Integer getIdTag() {
        return idTag;
    }

    public void setIdTag(Integer idTag) {
        this.idTag = idTag;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // equals() e hashCode() continuam os mesmos..
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AtividadeResponseDTO that = (AtividadeResponseDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

package br.com.impacta.boacao.dto.request;

import java.time.LocalDateTime;
import java.util.Objects;

import br.com.impacta.boacao.entity.enums.PeriodoAtividade;
import br.com.impacta.boacao.entity.enums.StatusAtividade;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AtividadeRequestDTO {

    private Integer id;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotNull(message = "Período é obrigatório.")
    private PeriodoAtividade periodo;

    @NotBlank(message = "A carga horária diária é obrigatória.")
    private String cargaHorariaDiaria;

    @NotBlank(message = "O endereço completo é obrigatório.")
    private String enderecoCompleto;

    @NotNull(message = "O campo possui certificação é obrigatório.")
    private Boolean possuiCertificacao;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    private StatusAtividade statusAtividade;

    @NotNull(message = "A data da atividade é obrigatória.")
    @FutureOrPresent(message = "Data da atividade não pode ser no passado.")
    private LocalDateTime dataAtividade;

    private Integer idTag;

    // Construtor padrão necessário para o Jacks
    public AtividadeRequestDTO(Integer id, String nome, PeriodoAtividade periodo, String cargaHorariaDiaria, String enderecoCompleto, Boolean possuiCertificacao, String descricao, StatusAtividade statusAtividade, LocalDateTime dataAtividade, Integer idOng, Integer idTag) {
        this.id = id;
        this.nome = nome;
        this.periodo = periodo;
        this.cargaHorariaDiaria = cargaHorariaDiaria;
        this.enderecoCompleto = enderecoCompleto;
        this.possuiCertificacao = possuiCertificacao;
        this.descricao = descricao;
        this.statusAtividade = statusAtividade;
        this.dataAtividade = dataAtividade;
        // this.idOng = idOng;
        this.idTag = idTag;
    }

    public AtividadeRequestDTO() {
    }

    public AtividadeRequestDTO(Integer id, String nome, PeriodoAtividade periodo, String cargaHorariaDiaria,
            String enderecoCompleto, Boolean possuiCertificacao, String descricao,
            StatusAtividade statusAtividade, LocalDateTime dataAtividade, Integer idOng) {
        this.id = id;
        this.nome = nome;
        this.periodo = periodo;
        this.cargaHorariaDiaria = cargaHorariaDiaria;
        this.enderecoCompleto = enderecoCompleto;
        this.possuiCertificacao = possuiCertificacao;
        this.descricao = descricao;
        this.statusAtividade = statusAtividade;
        this.dataAtividade = dataAtividade;
        //this.idOng = idOng; // Inicializa com null, será setado posteriormente
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AtividadeRequestDTO that = (AtividadeRequestDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

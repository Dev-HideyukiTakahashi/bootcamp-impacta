package br.com.impacta.boacao.dto.response;

import java.time.LocalDateTime;

import br.com.impacta.boacao.entity.enums.PeriodoAtividade;
import br.com.impacta.boacao.entity.enums.StatusAtividade;

/**
 * DTO utilizado para respostas (responses) relacionadas a gestão de atividades
 * da ong. contém apenas os dados essenciais que devem ser retornados ao
 * cliente,
 */
public class AtividadeOngResponseDTO {

    private Integer id;
    private String nome;
    private Long totalAprovados;
    private String cargaHorariaDiaria;
    private PeriodoAtividade periodo;
    private LocalDateTime criadoEm;
    private StatusAtividade statusAtividade;
    private String descricao;
    private LocalDateTime dataAtividade;
    private Integer idOng;

    public AtividadeOngResponseDTO() {
    }

    public AtividadeOngResponseDTO(
            Integer id,
            String nome,
            Long totalAprovados,
            String cargaHorariaDiaria,
            PeriodoAtividade periodo,
            LocalDateTime criadoEm,
            StatusAtividade statusAtividade,
            String descricao,
            LocalDateTime dataAtividade,
            Integer idOng
    ) {
        this.id = id;
        this.nome = nome;
        this.totalAprovados = totalAprovados;
        this.cargaHorariaDiaria = cargaHorariaDiaria;
        this.periodo = periodo;
        this.criadoEm = criadoEm;
        this.statusAtividade = statusAtividade;
        this.descricao = descricao;
        this.dataAtividade = dataAtividade;
        this.idOng = idOng;
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

    public Long getTotalAprovados() {
        return totalAprovados;
    }

    public void setTotalAprovados(Long totalAprovados) {
        this.totalAprovados = totalAprovados;
    }

    public String getCargaHorariaDiaria() {
        return cargaHorariaDiaria;
    }

    public void setCargaHorariaDiaria(String cargaHorariaDiaria) {
        this.cargaHorariaDiaria = cargaHorariaDiaria;
    }

    public PeriodoAtividade getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoAtividade periodo) {
        this.periodo = periodo;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public StatusAtividade getStatusAtividade() {
        return statusAtividade;
    }

    public void setStatusAtividade(StatusAtividade statusAtividade) {
        this.statusAtividade = statusAtividade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataAtividade() {
        return dataAtividade;
    }

    public void setDataAtividade(LocalDateTime dataAtividade) {
        this.dataAtividade = dataAtividade;
    }

    public Integer getIdOng() {
        return idOng;
    }

    public void setIdOng(Integer idOng) {
        this.idOng = idOng;
    }
}

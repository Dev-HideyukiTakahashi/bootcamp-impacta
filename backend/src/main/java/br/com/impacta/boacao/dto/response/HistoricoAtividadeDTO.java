package br.com.impacta.boacao.dto.response;

import java.time.LocalDate;
import java.util.Date;

import br.com.impacta.boacao.entity.enums.PeriodoAtividade;

public class HistoricoAtividadeDTO {

    private Integer id;
    private String descricao;
    private String cargaHorariaDiaria;
    private PeriodoAtividade periodo;
    private Date dataInscricao;
    private int estrelas;
    private boolean certificado;
    private String ong;
    private String feedback;
    private LocalDate encerradoEm;

    public HistoricoAtividadeDTO() {
    }

    public HistoricoAtividadeDTO(Integer id, String descricao, String cargaHorariaDiaria, PeriodoAtividade periodo,
            Date dataInscricao, int estrelas, boolean certificado, String ong, String feedback, LocalDate encerradoEm) {
        this.id = id;
        this.descricao = descricao;
        this.cargaHorariaDiaria = cargaHorariaDiaria;
        this.periodo = periodo;
        this.dataInscricao = dataInscricao;
        this.estrelas = estrelas;
        this.certificado = certificado;
        this.ong = ong;
        this.feedback = feedback;
        this.encerradoEm = encerradoEm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Date getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(Date dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public boolean isCertificado() {
        return certificado;
    }

    public void setCertificado(boolean certificado) {
        this.certificado = certificado;
    }

    public String getOng() {
        return ong;
    }

    public void setOng(String ong) {
        this.ong = ong;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LocalDate getEncerradoEm() {
        return encerradoEm;
    }

    public void setEncerradoEm(LocalDate encerradoEm) {
        this.encerradoEm = encerradoEm;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        HistoricoAtividadeDTO other = (HistoricoAtividadeDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}

package br.com.impacta.boacao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String feedback;
    private int estrelas;
    private boolean isAvaliado;

    @OneToOne(mappedBy = "avaliacao")
    private HistoricoAtividade historicoAtividade;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    private Ong ong;

    public Avaliacao() {
    }

    public Avaliacao(Integer id, String feedback, int estrelas, boolean isAvaliado,
            HistoricoAtividade historicoAtividade, Ong ong) {
        this.id = id;
        this.feedback = feedback;
        this.estrelas = estrelas;
        this.isAvaliado = isAvaliado;
        this.historicoAtividade = historicoAtividade;
        this.ong = ong;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public boolean isAvaliado() {
        return isAvaliado;
    }

    public void setAvaliado(boolean isAvaliado) {
        this.isAvaliado = isAvaliado;
    }

    public HistoricoAtividade getHistoricoAtividade() {
        return historicoAtividade;
    }

    public void setHistoricoAtividade(HistoricoAtividade historicoAtividade) {
        this.historicoAtividade = historicoAtividade;
    }

    public Ong getOng() {
        return ong;
    }

    public void setOng(Ong ong) {
        this.ong = ong;
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
        Avaliacao other = (Avaliacao) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}

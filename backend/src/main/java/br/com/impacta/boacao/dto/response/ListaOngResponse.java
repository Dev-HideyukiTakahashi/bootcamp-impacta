package br.com.impacta.boacao.dto.response;

import java.util.ArrayList;
import java.util.List;

import br.com.impacta.boacao.entity.Atividade;
import br.com.impacta.boacao.entity.Tag;

public class ListaOngResponse {

    private String nomeEntidade;
    private String cidade;
    private String estado;
    private boolean participando;

    private final List<Tag> tags = new ArrayList<>();
    private final List<Atividade> atividades = new ArrayList<>();

    public ListaOngResponse() {
    }

    public ListaOngResponse(String nomeEntidade, String cidade, String estado, boolean participando) {
        this.nomeEntidade = nomeEntidade;
        this.cidade = cidade;
        this.estado = estado;
        this.participando = participando;
    }

    public String getNomeEntidade() {
        return nomeEntidade;
    }

    public void setNomeEntidade(String nomeEntidade) {
        this.nomeEntidade = nomeEntidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isParticipando() {
        return participando;
    }

    public void setParticipando(boolean participando) {
        this.participando = participando;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }
}

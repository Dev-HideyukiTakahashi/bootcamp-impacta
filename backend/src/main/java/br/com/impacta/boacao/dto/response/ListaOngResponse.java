package br.com.impacta.boacao.dto.response;

import java.util.ArrayList;
import java.util.List;

import br.com.impacta.boacao.dto.request.TagRequestDTO;

public class ListaOngResponse {

    private String nomeEntidade;
    private String cidade;
    private String estado;
    private boolean participando;

    private final List<TagRequestDTO> tags = new ArrayList<>();
    private final List<AtividadeResponseDTO> atividades = new ArrayList<>();

    public ListaOngResponse(String nomeEntidade, String cidade, String estado) {
        this.nomeEntidade = nomeEntidade;
        this.cidade = cidade;
        this.estado = estado;
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

    public List<TagRequestDTO> getTags() {
        return tags;
    }

    public void addTag(TagRequestDTO tagDTO) {
        tags.add(tagDTO);
    }

    public List<AtividadeResponseDTO> getAtividades() {
        return atividades;
    }

    public void addAtividade(AtividadeResponseDTO atividadeDTO) {
        atividades.add(atividadeDTO);
    }

}

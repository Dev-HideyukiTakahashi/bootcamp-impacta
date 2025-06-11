package br.com.impacta.boacao.dto.response;

import java.util.ArrayList;
import java.util.List;

import br.com.impacta.boacao.dto.request.TagRequestDTO;

public class ListaOngResponse {

    private String nomeEntidade;
    private String cidade;
    private String estado;

    private final List<TagRequestDTO> tags = new ArrayList<>();
    private final List<AtividadeMinDTO> atividades = new ArrayList<>();

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

    public List<TagRequestDTO> getTags() {
        return tags;
    }

    public void addTag(TagRequestDTO tagDTO) {
        tags.add(tagDTO);
    }

    public List<AtividadeMinDTO> getAtividades() {
        return atividades;
    }

    public void addAtividade(AtividadeMinDTO atividadeDTO) {
        atividades.add(atividadeDTO);
    }

}

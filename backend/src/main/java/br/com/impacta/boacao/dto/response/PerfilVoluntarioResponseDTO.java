package br.com.impacta.boacao.dto.response;

import java.util.ArrayList;
import java.util.List;

import br.com.impacta.boacao.dto.request.TagRequestDTO;

public class PerfilVoluntarioResponseDTO {
    private String firstName;
    private String fullName;
    private String cidade;
    private String estado;
    private Integer participacoes;
    private int media;

    private final List<TagRequestDTO> tags = new ArrayList<>();

    public PerfilVoluntarioResponseDTO() {
    }

    public PerfilVoluntarioResponseDTO(String firstName, String fullName, String cidade, String estado,
            Integer participacoes, int media) {
        this.firstName = firstName;
        this.fullName = fullName;
        this.cidade = cidade;
        this.estado = estado;
        this.participacoes = participacoes;
        this.media = media;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public void addTag(TagRequestDTO tag) {
        tags.add(tag);
    }

    public Integer getParticipacoes() {
        return participacoes;
    }

    public void setParticipacoes(Integer participacoes) {
        this.participacoes = participacoes;
    }

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }

}

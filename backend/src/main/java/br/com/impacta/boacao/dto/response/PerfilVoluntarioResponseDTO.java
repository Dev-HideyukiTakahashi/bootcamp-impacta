package br.com.impacta.boacao.dto.response;

import java.util.ArrayList;
import java.util.List;

import br.com.impacta.boacao.dto.request.TagRequestDTO;

public class PerfilVoluntarioResponseDTO {
    private String firstName;
    private String fullName;
    private String cidade;
    private String estado;

    private final List<TagRequestDTO> tags = new ArrayList<>();

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
}

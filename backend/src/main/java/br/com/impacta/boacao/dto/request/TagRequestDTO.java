package br.com.impacta.boacao.dto.request;

import java.util.Objects;

public class TagRequestDTO {

    private final Integer id;
    private final String nome;

    public TagRequestDTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TagRequestDTO entity = (TagRequestDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nome, entity.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nome = " + nome + ")";
    }
}
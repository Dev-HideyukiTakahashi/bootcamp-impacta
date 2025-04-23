package br.com.impacta.boacao.mapper;

import br.com.impacta.boacao.dto.request.TagRequestDTO;
import br.com.impacta.boacao.entity.Tag;

public class TagMapper {

    public static TagRequestDTO paraDTO(Tag entidade) {
        return new TagRequestDTO(
                entidade.getId(),
                entidade.getNome());
    }
}

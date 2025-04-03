package br.com.impacta.boacao.mapper;

import br.com.impacta.boacao.dto.TagDTO;
import br.com.impacta.boacao.entities.Tag;

public class TagMapper {

    public static TagDTO paraDTO(Tag entidade){
        return new TagDTO(
                entidade.getId(),
                entidade.getNome()
        );
    }
}

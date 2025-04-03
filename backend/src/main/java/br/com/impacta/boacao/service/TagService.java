package br.com.impacta.boacao.service;

import br.com.impacta.boacao.dto.TagDTO;
import br.com.impacta.boacao.entities.Tag;
import br.com.impacta.boacao.mapper.TagMapper;
import br.com.impacta.boacao.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final Logger log = LoggerFactory.getLogger(TagService.class);

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Transactional(readOnly = true)
    public List<TagDTO> buscarTodos(){
        List<Tag> lista = tagRepository.findAll();
        log.info("Busca de todas as tags concluída com sucesso.");

        return lista.stream()
                .map(tag -> TagMapper.paraDTO(tag))
                .toList();
    }
}

package br.com.impacta.boacao.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.impacta.boacao.dto.TagDTO;
import br.com.impacta.boacao.entities.Tag;
import br.com.impacta.boacao.exception.RecursoNaoEncontrado;
import br.com.impacta.boacao.mapper.TagMapper;
import br.com.impacta.boacao.repository.TagRepository;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final Logger log = LoggerFactory.getLogger(TagService.class);

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Transactional(readOnly = true)
    public List<TagDTO> buscarTodos() {
        List<Tag> lista = tagRepository.findAll();
        log.info("Busca de todas as tags concluída com sucesso.");

        return lista.stream()
                .map(tag -> TagMapper.paraDTO(tag))
                .toList();
    }

    @Transactional(readOnly = true)
    public TagDTO buscarPorId(Integer id) {
        Tag entidade = tagRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Tag de não encontrada! Id: " + id));
        log.info("Busca de tag realizada com sucesso! Id: {}", id);

        return TagMapper.paraDTO(entidade);
    }
}

package br.com.impacta.boacao.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.boacao.dto.TagDTO;
import br.com.impacta.boacao.service.TagService;

@RestController
@RequestMapping(path = "/tags")
public class TagController {

    private final TagService tagService;
    private final Logger log = LoggerFactory.getLogger(TagController.class);

    public TagController(TagService service) {
        this.tagService = service;
    }

    @GetMapping
    public ResponseEntity<List<TagDTO>> buscarTodos() {
        log.info("Iniciando busca de todas as tags.");
        List<TagDTO> dto = tagService.buscarTodos();

        return ResponseEntity.ok(dto);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TagDTO> buscarPorId(@PathVariable Integer id) {
        log.info("Iniciando busca de tag id {}.", id);
        TagDTO dto = tagService.buscarPorId(id);

        return ResponseEntity.ok(dto);
    }
}

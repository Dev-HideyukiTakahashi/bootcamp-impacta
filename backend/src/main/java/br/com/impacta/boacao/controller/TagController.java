package br.com.impacta.boacao.controller;

import br.com.impacta.boacao.dto.TagDTO;
import br.com.impacta.boacao.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/tags")
public class TagController {

    private final TagService tagService;
    private final Logger log = LoggerFactory.getLogger(TagController.class);

    public TagController(TagService service) {
        this.tagService = service;
    }

    public ResponseEntity<List<TagDTO>> buscarTodos(){
        log.info("Iniciando busca de todas as tags.");
        List<TagDTO> dto = tagService.buscarTodos();

        return ResponseEntity.ok(dto);
    }
}

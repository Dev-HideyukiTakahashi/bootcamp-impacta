package br.com.impacta.boacao.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.impacta.boacao.dto.request.AvaliacaoRequestDTO;
import br.com.impacta.boacao.dto.response.AvaliacaoResponseDTO;
import br.com.impacta.boacao.service.AvaliacaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;
    private final Logger log = LoggerFactory.getLogger(AvaliacaoController.class);

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> avaliarVoluntario(@RequestBody @Valid AvaliacaoRequestDTO request) {
        log.info("Iniciando avaliacao de voluntario");
        AvaliacaoResponseDTO response = avaliacaoService.avaliarVoluntario(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/historicoId/{historicoId}")
    public ResponseEntity<AvaliacaoResponseDTO> getByHistoricoId(@PathVariable Integer historicoId) {
        return ResponseEntity.ok(avaliacaoService.buscarPorHistoricoAtividade(historicoId));
    }
}

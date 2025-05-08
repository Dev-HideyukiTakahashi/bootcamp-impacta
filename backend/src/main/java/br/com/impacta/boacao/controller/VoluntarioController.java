package br.com.impacta.boacao.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.boacao.dto.request.VoluntarioRequestDTO;
import br.com.impacta.boacao.dto.response.VoluntarioResponseDTO;
import br.com.impacta.boacao.service.VoluntarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/voluntarios")
public class VoluntarioController {

    private final VoluntarioService voluntarioService;

    @Autowired
    public VoluntarioController(VoluntarioService voluntarioService) {
        this.voluntarioService = voluntarioService;
    }

    @PostMapping
    public ResponseEntity<VoluntarioResponseDTO> cadastrar(
            @Valid @RequestBody VoluntarioRequestDTO dto
    ) {
        // Salva e obtém o DTO de resposta já com o ID criado
        VoluntarioResponseDTO response = voluntarioService.salvar(dto);

        // Monta a URI do recurso criado: /api/voluntarios/{id}
        URI location = URI.create(String.format("/api/voluntarios/cadastrar", response.getId()));

        // Retorna 201 Created + Location header
        return ResponseEntity
                .created(location)
                .body(response);
    }
}

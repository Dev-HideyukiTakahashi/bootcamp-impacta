package br.com.impacta.boacao.controller;

import java.net.URI;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.boacao.dto.response.DadosVoluntarioResponseDTO;
import br.com.impacta.boacao.dto.request.VoluntarioRequestDTO;
import br.com.impacta.boacao.dto.request.VoluntarioUpdateRequestDTO;
import br.com.impacta.boacao.dto.response.VoluntarioResponseDTO;
import br.com.impacta.boacao.dto.response.PerfilVoluntarioResponseDTO;
import br.com.impacta.boacao.service.VoluntarioService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/voluntario")
public class VoluntarioController {

    private final VoluntarioService voluntarioService;

    @Autowired
    public VoluntarioController(VoluntarioService voluntarioService) {
        this.voluntarioService = voluntarioService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<VoluntarioResponseDTO> cadastrar(@Valid @RequestBody VoluntarioRequestDTO dto) {
        VoluntarioResponseDTO response = voluntarioService.salvar(dto);

        URI location = URI.create(String.format("/cadastrar", response.getId()));

        return ResponseEntity
                .created(location)
                .body(response);
    }

    @GetMapping("/perfil-voluntario")
    public ResponseEntity<PerfilVoluntarioResponseDTO> meuPerfil(Authentication auth) {
        PerfilVoluntarioResponseDTO dto = voluntarioService.getMeuPerfil(auth);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/dados-voluntario")
    public ResponseEntity<DadosVoluntarioResponseDTO> getDadosVoluntario(Authentication auth) {
        DadosVoluntarioResponseDTO dto = voluntarioService.getDadosVoluntario(auth);
        return ResponseEntity.ok(dto);
    }

 @PutMapping("/editar")
public ResponseEntity<Void> atualizarVoluntario(
        Authentication auth,
        @Valid @RequestBody VoluntarioUpdateRequestDTO dto) {
    voluntarioService.atualizarVoluntario(auth, dto);
    return ResponseEntity.noContent().build();
}

}

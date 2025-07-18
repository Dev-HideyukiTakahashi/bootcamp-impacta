package br.com.impacta.boacao.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.boacao.dto.request.OngRequestDTO;
import br.com.impacta.boacao.dto.request.OngUpdateRequestDTO;
import br.com.impacta.boacao.dto.response.DadosOngResponseDTO;
import br.com.impacta.boacao.dto.response.OngResponseDTO;
import br.com.impacta.boacao.dto.response.PerfilOngResponseDTO;
import br.com.impacta.boacao.service.OngService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ong")
public class OngController {

  private OngService ongService;

  public OngController(OngService ongService) {
    this.ongService = ongService;
  }

  @PostMapping("/cadastrar")
  public ResponseEntity<OngResponseDTO> cadastrar(@Valid @RequestBody OngRequestDTO dto) {
    OngResponseDTO response = ongService.salvar(dto);

    URI location = URI.create(String.format("/api/ong", response.getId()));

    return ResponseEntity
        .created(location)
        .body(response);
  }

  @GetMapping("/perfil-ong")
  @PreAuthorize("hasRole('ROLE_ONG')")
  public ResponseEntity<PerfilOngResponseDTO> meuPerfil(Authentication auth) {
    PerfilOngResponseDTO dto = ongService.getMeuPerfil(auth);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/dados-ong")
  @PreAuthorize("hasRole('ROLE_ONG')")
  public ResponseEntity<DadosOngResponseDTO> getDadosOng(Authentication auth) {
    var dto = ongService.getDadosOng(auth);
    return ResponseEntity.ok(dto);
  }

  @PutMapping("/editar")
  @PreAuthorize("hasRole('ROLE_ONG')")
  public ResponseEntity<Void> atualizarOng(
      Authentication auth,
      @Valid @RequestBody OngUpdateRequestDTO dto) {
    ongService.atualizarOng(auth, dto);
    return ResponseEntity.noContent().build();
  }
}

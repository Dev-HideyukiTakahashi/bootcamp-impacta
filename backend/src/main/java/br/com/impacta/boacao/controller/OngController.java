package br.com.impacta.boacao.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.boacao.dto.request.OngRequestDTO;
import br.com.impacta.boacao.dto.request.OngUpdateRequestDTO;
import br.com.impacta.boacao.dto.response.DadosOngResponseDTO;
import br.com.impacta.boacao.dto.response.ListaOngResponse;
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
  public ResponseEntity<PerfilOngResponseDTO> meuPerfil(Authentication auth) {
    PerfilOngResponseDTO dto = ongService.getMeuPerfil(auth);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/dados-ong")
  public ResponseEntity<DadosOngResponseDTO> getDadosOng(Authentication auth) {
    var dto = ongService.getDadosOng(auth);
    return ResponseEntity.ok(dto);
  }

  @PutMapping("/editar")
  public ResponseEntity<Void> atualizarOng(
      Authentication auth,
      @Valid @RequestBody OngUpdateRequestDTO dto) {
    ongService.atualizarOng(auth, dto);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(path = "/{tag}")
  public ResponseEntity<Page<ListaOngResponse>> buscarPorTag(@PathVariable String tag, Pageable pageable) {

    Page<ListaOngResponse> response = ongService.buscarPorTag(tag, pageable);

    return ResponseEntity.ok().body(response);

  }

}

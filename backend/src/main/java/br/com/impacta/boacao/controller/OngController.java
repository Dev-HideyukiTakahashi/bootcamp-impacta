package br.com.impacta.boacao.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.boacao.dto.request.OngRequestDTO;
import br.com.impacta.boacao.dto.response.OngResponseDTO;
import br.com.impacta.boacao.service.OngService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/ong")
public class OngController {

  private final OngService ongService;

  @Autowired
  public OngController(OngService ongService) {
    this.ongService = ongService;
  }

  @PostMapping("/cadastrar")
  public ResponseEntity<OngResponseDTO> cadastrar(@Valid @RequestBody OngRequestDTO dto) {
    OngResponseDTO response = ongService.salvar(dto);

    URI location = URI.create(String.format("/cadastrar", response.getId()));

    return ResponseEntity
        .created(location)
        .body(response);
  }   
  
}

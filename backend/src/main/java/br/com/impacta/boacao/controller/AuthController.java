package br.com.impacta.boacao.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.boacao.dto.request.EmailRequestDTO;
import br.com.impacta.boacao.dto.request.NovaSenhaDTO;
import br.com.impacta.boacao.service.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final AuthService authService;
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/recuperar-senha")
    public ResponseEntity<Void> criarTokenRecuperacao(@Valid @RequestBody EmailRequestDTO dto) {
        logger.info("Criando email com token de recuperação para {}", dto.getEmail());
        authService.criarTokenRecuperacao(dto);
        return ResponseEntity.noContent().build();
    }

    // Criando uma nova senha ao clicar no link com token enviado por email
    @PutMapping(path = "/resetar-senha")
    public ResponseEntity<Void> salvarNovaSenha(@Valid @RequestBody NovaSenhaDTO dto) {
        logger.info("Iniciando reset de senha ");
        authService.salvarNovaSenha(dto);
        return ResponseEntity.noContent().build();
    }

}

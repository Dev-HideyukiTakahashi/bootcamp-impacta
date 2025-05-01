package br.com.impacta.boacao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.boacao.dto.request.EmailRequestDTO;
import br.com.impacta.boacao.service.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/recover-token")
    public ResponseEntity<Void> createRecoverToken(@Valid @RequestBody EmailRequestDTO dto) {
        authService.createRecoverToken(dto);
        return ResponseEntity.noContent().build();
    }

}

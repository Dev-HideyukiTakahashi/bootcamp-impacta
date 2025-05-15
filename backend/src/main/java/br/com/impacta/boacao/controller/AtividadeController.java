package br.com.impacta.boacao.controller;

import br.com.impacta.boacao.dto.response.AtividadesOngResponseDTO;
import br.com.impacta.boacao.service.AtividadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/atividades")
public class AtividadeController {

    private final AtividadeService atividadeService;
    private final Logger logger = LoggerFactory.getLogger(AtividadeController.class);

    public AtividadeController(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }

    @GetMapping()
    public ResponseEntity<Page<AtividadesOngResponseDTO>> buscarTodos(Pageable pageable){
        logger.info("Ong começando busca por todas atividades");
        Page<AtividadesOngResponseDTO> response = atividadeService.buscarTodos(pageable);
        logger.info("Sucesso em busca por todas atividades");
        return ResponseEntity.ok(response);
    }

}

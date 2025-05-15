package br.com.impacta.boacao.controller;

import br.com.impacta.boacao.dto.request.AtividadeStatusRequestDTO;
import br.com.impacta.boacao.dto.response.AtividadeOngResponseDTO;
import br.com.impacta.boacao.dto.response.AtividadeStatusResponseDTO;
import br.com.impacta.boacao.service.AtividadeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/atividades")
public class AtividadeController {

    private final AtividadeService atividadeService;
    private final Logger logger = LoggerFactory.getLogger(AtividadeController.class);

    public AtividadeController(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }

    @GetMapping()
    public ResponseEntity<Page<AtividadeOngResponseDTO>> buscarTodos(Pageable pageable){
        logger.info("Ong começando busca por todas atividades");
        Page<AtividadeOngResponseDTO> response = atividadeService.buscarTodos(pageable);
        logger.info("Sucesso em busca por todas atividades");
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}/status")
    public ResponseEntity<AtividadeStatusResponseDTO> atualizarStatus(@PathVariable Integer id,
                                                                      @Valid @RequestBody AtividadeStatusRequestDTO dto){
        logger.info("Ong começando atualização de status da atividade");

        AtividadeStatusResponseDTO response = atividadeService.atualizarStatus(id, dto);
        return ResponseEntity.ok(response);
    }

}

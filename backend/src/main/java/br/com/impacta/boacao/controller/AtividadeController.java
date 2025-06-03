package br.com.impacta.boacao.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.impacta.boacao.dto.request.AtividadeRequestDTO;
import br.com.impacta.boacao.dto.response.AtividadeResponseDTO;
import br.com.impacta.boacao.service.AtividadeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/atividades")
public class AtividadeController {

    private final AtividadeService atividadeService;
    private final Logger logger = LoggerFactory.getLogger(AtividadeController.class);

    public AtividadeController(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }

    // GET sem paginação
    @GetMapping("/buscar")
    public ResponseEntity<List<AtividadeResponseDTO>> buscarTodas() {
        logger.info("Ong começando busca por todas as atividades (sem paginação)");
        List<AtividadeResponseDTO> response = atividadeService.buscarTodas();
        logger.info("Sucesso na busca por todas as atividades: {} itens", response.size());
        return ResponseEntity.ok(response);
    }


    /*   @PutMapping(path = "/{id}/status")
    public ResponseEntity<AtividadeStatusResponseDTO> atualizarStatus(@PathVariable Integer id,
                                                                      @Valid @RequestBody AtividadeStatusRequestDTO dto){
        logger.info("Ong começando atualização de status da atividade");

        AtividadeStatusResponseDTO response = AtividadeServiceImpl.atualizarStatus(id, dto);
        return ResponseEntity.ok(response);
    }*/
    @PutMapping(path = "/{id}")
    public ResponseEntity<AtividadeResponseDTO> atualizar(@PathVariable Integer id,
            @Valid @RequestBody AtividadeRequestDTO dto) {
        logger.info("Ong começando atualização da atividade");

        AtividadeResponseDTO response = atividadeService.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<AtividadeResponseDTO> cadastrar(@Valid @RequestBody AtividadeRequestDTO dto) {
        logger.info("Iniciando cadastro de nova atividade");

        AtividadeResponseDTO response = atividadeService.cadastrar(dto);
        return ResponseEntity.ok(response);
    }

    /*@DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        logger.info("Ong deletando atividade de id: {}", id);

        atividadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }*/
}

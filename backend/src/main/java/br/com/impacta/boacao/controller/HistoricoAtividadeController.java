package br.com.impacta.boacao.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.boacao.dto.response.HistoricoAtividadeDTO;
import br.com.impacta.boacao.service.HistoricoAtividadeService;

@RestController
@RequestMapping(path = "/api/historico-atividades")
public class HistoricoAtividadeController {

    private final HistoricoAtividadeService historicoAtividadeService;
    private final Logger logger = LoggerFactory.getLogger(HistoricoAtividadeController.class);

    public HistoricoAtividadeController(HistoricoAtividadeService historicoAtividadeService) {
        this.historicoAtividadeService = historicoAtividadeService;
    }

    @GetMapping
    public ResponseEntity<Page<HistoricoAtividadeDTO>> buscarTodos(Pageable pageable) {
        logger.info("Iniciando busca do histórico de atividades");
        Page<HistoricoAtividadeDTO> response = historicoAtividadeService.buscarTodos(pageable);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(path = "/data")
    public ResponseEntity<Page<HistoricoAtividadeDTO>> buscarTodosPorData(
            @RequestParam String encerradoEm,
            Pageable pageable) {
        logger.info("Iniciando busca do histórico de atividades filtrado por data");
        Page<HistoricoAtividadeDTO> response = historicoAtividadeService.buscarTodosPorData(encerradoEm, pageable);

        return ResponseEntity.ok().body(response);
    }

}

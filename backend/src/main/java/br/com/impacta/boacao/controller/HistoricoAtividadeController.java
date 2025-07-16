package br.com.impacta.boacao.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.boacao.dto.request.HistoricoAtividadeRequestDTO;
import br.com.impacta.boacao.dto.response.HistoricoAtividadeDTO;
import br.com.impacta.boacao.dto.response.HistoricoAtividadeResponseDTO;
import br.com.impacta.boacao.dto.response.HistoricoAtividadeTodosResponseDTO;
import br.com.impacta.boacao.entity.enums.StatusCandidatura;
import br.com.impacta.boacao.exception.handler.StatusCandidaturaInvalidoException;
import br.com.impacta.boacao.service.HistoricoAtividadeService;
@RestController
@RequestMapping(path = "/api/historico-atividades")
public class HistoricoAtividadeController {

    private final HistoricoAtividadeService historicoAtividadeService;
    private final Logger logger = LoggerFactory.getLogger(HistoricoAtividadeController.class);

    public HistoricoAtividadeController(HistoricoAtividadeService historicoAtividadeService) {
        this.historicoAtividadeService = historicoAtividadeService;
    }

    @PreAuthorize("hasRole('ROLE_VOLUNTARIO')")
    @GetMapping
    public ResponseEntity<Page<HistoricoAtividadeDTO>> buscarTodos(Pageable pageable) {
        logger.info("Iniciando busca do histórico de atividades");
        Page<HistoricoAtividadeDTO> response = historicoAtividadeService.buscarTodos(pageable);

        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('ROLE_VOLUNTARIO')")
    @GetMapping(path = "/data")
    public ResponseEntity<Page<HistoricoAtividadeDTO>> buscarTodosPorData(
            @RequestParam String encerradoEm,
            Pageable pageable) {
        logger.info("Iniciando busca do histórico de atividades filtrado por data");
        Page<HistoricoAtividadeDTO> response = historicoAtividadeService.buscarTodosPorData(encerradoEm, pageable);

        return ResponseEntity.ok().body(response);
    }

    /**
     * GET /api/atividades/{id}/historico
     *
     * @param id ID da atividade
     * @return quantidade e lista de voluntários aprovados
     */
    @PreAuthorize("hasRole('ROLE_ONG')")
    @GetMapping("/listar-voluntarios-aprovados/atividade/{id}")
    public ResponseEntity<HistoricoAtividadeResponseDTO> getHistorico(
            @PathVariable("id") Integer id) {

        logger.info("Voluntarios-aprovados/atividade/{id} - Iniciando busca do histórico de atividade com ID: {}", id);

        // encapsula o path-variable em um DTO de request
        HistoricoAtividadeRequestDTO request = new HistoricoAtividadeRequestDTO(id);

        // delega ao service e retorna o response DTO
        HistoricoAtividadeResponseDTO response = historicoAtividadeService.buscarHistorico(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Novo endpoint: busca todos (sem filtro de status) para poder gerenciar
     */
    @PreAuthorize("hasRole('ROLE_ONG')")
    @GetMapping("/gestao-voluntarios/atividade/{id}")
    public ResponseEntity<HistoricoAtividadeTodosResponseDTO> listaTodosVoluntariosInscritos(
            @PathVariable Integer id) {
        var req = new HistoricoAtividadeRequestDTO(id);
        HistoricoAtividadeTodosResponseDTO response = historicoAtividadeService.listaTodosVoluntariosInscritos(req.getAtividadeId());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ROLE_ONG')")
    @PutMapping("/gestao-voluntarios/atividade/{atividadeId}/statusCandidatura/{status}")
    public ResponseEntity<Void> atualizarStatus(
            @PathVariable Integer atividadeId,
            @PathVariable String status, // STRING aqui
            @RequestBody HistoricoAtividadeRequestDTO req
    ) {
        req.setAtividadeId(atividadeId);

        StatusCandidatura st;
        try {
            st = StatusCandidatura.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new StatusCandidaturaInvalidoException(status);
        }

        historicoAtividadeService.atualizarStatusCandidatura(req, st);
        return ResponseEntity.noContent().build();
    }
}

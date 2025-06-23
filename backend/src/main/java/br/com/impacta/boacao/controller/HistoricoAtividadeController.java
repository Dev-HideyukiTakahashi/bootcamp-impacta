package br.com.impacta.boacao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.boacao.dto.request.HistoricoAtividadeRequestDTO;
import br.com.impacta.boacao.dto.response.HistoricoAtividadeResponseDTO;
import br.com.impacta.boacao.dto.response.HistoricoAtividadeTodosResponseDTO;
import br.com.impacta.boacao.service.HistoricoAtividadeService;

@RestController
@RequestMapping("/api/atividades")
public class HistoricoAtividadeController {

    private final HistoricoAtividadeService service;

    public HistoricoAtividadeController(HistoricoAtividadeService service) {
        this.service = service;
    }

    /**
     * GET /api/atividades/{id}/historico
     *
     * @param id ID da atividade
     * @return quantidade e lista de voluntários aprovados
     */
    @GetMapping("/{id}/historico")
    public ResponseEntity<HistoricoAtividadeResponseDTO> getHistorico(
            @PathVariable("id") Integer id) {

        // encapsula o path-variable em um DTO de request
        HistoricoAtividadeRequestDTO request = new HistoricoAtividadeRequestDTO(id);

        // delega ao service e retorna o response DTO
        HistoricoAtividadeResponseDTO response = service.buscarHistorico(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Novo endpoint: busca todos (sem filtro de status)
     */
    @GetMapping("/{id}/historico/todos")
    public ResponseEntity<HistoricoAtividadeTodosResponseDTO> getTodosHistorico(
            @PathVariable Integer id) {
        var req = new HistoricoAtividadeRequestDTO(id);
        HistoricoAtividadeTodosResponseDTO response = service.listarTodosHistorico(req);
        return ResponseEntity.ok(response);
    }

}
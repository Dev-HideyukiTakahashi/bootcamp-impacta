package br.com.impacta.boacao.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.boacao.dto.request.AtividadeIdDTO;
import br.com.impacta.boacao.dto.request.AtividadeRequestDTO;
import br.com.impacta.boacao.dto.response.AtividadeResponseDTO;
import br.com.impacta.boacao.dto.response.AtividadeStatusResponseDTO;
import br.com.impacta.boacao.dto.response.VoluntarioAtividadeDTO;
import br.com.impacta.boacao.entity.enums.StatusAtividade;
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
    @PreAuthorize("hasRole('ROLE_ONG')")
    public ResponseEntity<List<AtividadeResponseDTO>> buscarTodas() {
        logger.info("Ong começando busca por todas as atividades (sem paginação)");
        List<AtividadeResponseDTO> response = atividadeService.buscarTodas();
        logger.info("Sucesso na busca por todas as atividades: {} itens", response.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_VOLUNTARIO')")
    public ResponseEntity<Page<AtividadeResponseDTO>> listarAtividades(Pageable pageable) {
        Page<AtividadeResponseDTO> atividades = atividadeService.buscarAtividades(pageable);
        return ResponseEntity.ok(atividades);
    }

    /**
     * PATCH /api/atividades/{id}/status/{novoStatus}
     *
     * Exemplo de chamada no Postman: PATCH
     * http://localhost:8080/api/atividades/12/status/CONGELADA
     *
     * Isso fará com que o service valide e altere apenas o campo
     * `statusAtividade` na entidade.
     *
     * @param id         o ID da atividade que queremos atualizar
     * @param novoStatus o novo status (string que bate com o enum
     *                   StatusAtividade)
     * @return 200 OK + a própria entidade Atividade (com status alterado) em
     *         caso de sucesso, 404 Not Found se o ID não existir, 409 Conflict se a
     *         transição de status não for permitida, 400 Bad Request se
     *         `novoStatus`
     *         não for um valor válido do enum.
     */
    @PatchMapping("/{id}/status/{novoStatus}")
    @PreAuthorize("hasRole('ROLE_ONG')")
    public ResponseEntity<?> atualizarStatus(
            @PathVariable Integer id,
            @PathVariable String novoStatus) {
        StatusAtividade statusEnum;

        // 1) Converte a string para o enum (pode lançar IllegalArgumentException)
        try {
            statusEnum = StatusAtividade.valueOf(novoStatus);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity
                    .badRequest()
                    .body("Status inválido. Use: ANDAMENTO ou CONGELADA.");
        }

        // 2) Chama o service que já faz a validação de transição e salva no banco
        try {
            AtividadeStatusResponseDTO dto = atividadeService.atualizarStatus(id, statusEnum);
            return ResponseEntity.ok(dto);

        } catch (IllegalArgumentException ex) {
            // Provém de: “Atividade não encontrada com id: X”
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Atividade não encontrada com id: " + id);

        } catch (IllegalStateException ex) {
            // Provém de: “Transição de status não permitida: Y → Z”
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Transição não permitida: " + ex.getMessage());
        }
    }

    @GetMapping("/dados-atividade/{id}")
    @PreAuthorize("hasRole('ROLE_ONG')")
    public ResponseEntity<AtividadeResponseDTO> getDadosAtividade(@PathVariable Integer id) {
        var dto = atividadeService.getDadosAtividadePorId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(path = "/editar/{id}")
    @PreAuthorize("hasRole('ROLE_ONG')")
    public ResponseEntity<AtividadeResponseDTO> atualizar(@PathVariable Integer id,
            @Valid @RequestBody AtividadeRequestDTO dto) {
        logger.info("Ong começando atualização da atividade");

        AtividadeResponseDTO response = atividadeService.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ONG')")
    public ResponseEntity<AtividadeResponseDTO> cadastrar(@Valid @RequestBody AtividadeRequestDTO dto) {
        logger.info("Iniciando cadastro de nova atividade");

        AtividadeResponseDTO response = atividadeService.cadastrar(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/atualizar-candidatura")
    @PreAuthorize("hasAnyRole('ROLE_ONG','ROLE_VOLUNTARIO')")
    public ResponseEntity<VoluntarioAtividadeDTO> atualizarStatusCandidatura(@RequestBody AtividadeIdDTO request) {
        logger.info("Iniciando associação de histórico de atividade a um voluntário");

        VoluntarioAtividadeDTO response = atividadeService.atualizarStatusCandidatura(request.getAtividadeId());
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/estado/{estado}")
    @PreAuthorize("hasRole('ROLE_VOLUNTARIO')")
    public ResponseEntity<Page<AtividadeResponseDTO>> buscarAtividadePorEstadoOng(@PathVariable String estado,
            Pageable pageable) {
        logger.info("Iniciando busca de atividades no estado: {}", estado);
        Page<AtividadeResponseDTO> response = atividadeService.buscarAtividadePorEstadoOng(estado, pageable);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(path = "/tag/{tag}")
    @PreAuthorize("hasRole('ROLE_VOLUNTARIO')")
    public ResponseEntity<Page<AtividadeResponseDTO>> buscarAtividadePorTag(@PathVariable String tag,
            Pageable pageable) {
        logger.info("Iniciando busca de atividade por tag: {}", tag);
        Page<AtividadeResponseDTO> response = atividadeService.buscarAtividadePorTag(tag, pageable);

        return ResponseEntity.ok().body(response);
    }

}

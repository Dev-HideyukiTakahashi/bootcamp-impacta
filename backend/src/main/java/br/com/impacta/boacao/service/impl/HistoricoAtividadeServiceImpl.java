// src/main/java/br/com/impacta/boacao/service/impl/HistoricoAtividadeServiceImpl.java
package br.com.impacta.boacao.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.impacta.boacao.dto.request.HistoricoAtividadeRequestDTO;
import br.com.impacta.boacao.dto.response.HistoricoAtividadeDTO;
import br.com.impacta.boacao.dto.response.HistoricoAtividadeResponseDTO;
import br.com.impacta.boacao.dto.response.HistoricoAtividadeTodosResponseDTO;
import br.com.impacta.boacao.dto.response.VoluntarioAprovadoResponseDTO;
import br.com.impacta.boacao.dto.response.VoluntarioHistoricoResponseDTO;
import br.com.impacta.boacao.entity.HistoricoAtividade;
import br.com.impacta.boacao.entity.Usuario;
import br.com.impacta.boacao.entity.enums.StatusCandidatura;
import br.com.impacta.boacao.repository.HistoricoAtividadeRepository;
import br.com.impacta.boacao.service.HistoricoAtividadeService;
import br.com.impacta.boacao.service.UsuarioService;

@Service
public class HistoricoAtividadeServiceImpl implements HistoricoAtividadeService {

    private final HistoricoAtividadeRepository repo;
    private final UsuarioService usuarioService;

    /**
     * Construtor para injeção de dependência do repositório.
     */
    public HistoricoAtividadeServiceImpl(HistoricoAtividadeRepository repo, UsuarioService usuarioService) {
        this.repo = repo;
        this.usuarioService = usuarioService;
    }

    /**
     * Busca o histórico de voluntários aprovados para uma determinada atividade.
     *
     * @param request DTO contendo o ID da atividade
     * @return DTO de resposta com a quantidade e a lista de voluntários aprovados
     */
    @Override
    public HistoricoAtividadeResponseDTO buscarHistorico(HistoricoAtividadeRequestDTO request) {
        // Extrai o ID da atividade do DTO de request
        Integer atividadeId = request.getAtividadeId();

        // 1) Conta quantos registros de HistoricoAtividade têm status APROVADO para
        // esta atividade.
        long quantidade = repo.countByAtividadeIdAndStatusCandidatura(
                atividadeId,
                StatusCandidatura.APROVADO);

        // 2) Busca todos os registros de HistoricoAtividade (join table) que:
        // - estão vinculados à atividade passada
        // - têm status APROVADO
        List<HistoricoAtividade> historicosAprovados = repo
                .findByAtividade_IdAndStatusCandidatura(
                        atividadeId,
                        StatusCandidatura.APROVADO);

        // 3) Transforma a lista de entidades HistoricoAtividade em DTOs específicos de
        // voluntário:
        // a) map(HistoricoAtividade::getVoluntario) -> pega a entidade Voluntario
        // associada a cada histórico
        // b) map(v -> new VoluntarioAprovadoResponseDTO(...))
        // -> constrói um DTO com apenas ID e nomeCompleto do voluntário
        List<VoluntarioAprovadoResponseDTO> voluntariosDTO = historicosAprovados.stream()
                .map(HistoricoAtividade::getVoluntario)
                .map(v -> new VoluntarioAprovadoResponseDTO(
                        v.getId(),
                        v.getNomeCompleto()))
                .collect(Collectors.toList());

        // 4) Monta e retorna o DTO de resposta que agrupa:
        // - a quantidade total de voluntários aprovados
        // - a lista de VoluntarioAprovadoResponseDTO para exibição
        return new HistoricoAtividadeResponseDTO(quantidade, voluntariosDTO);
    }

    /**
     * Retorna todos os voluntários vinculados à atividade, independente do status.
     */
    /**
     * Retorna todos os voluntários vinculados à atividade, independente do status.
     */
    @Override
    public HistoricoAtividadeTodosResponseDTO listarTodosHistorico(HistoricoAtividadeRequestDTO request) {
        Integer atividadeId = request.getAtividadeId();

        // 1) busca todos os históricos sem filtrar status
        List<HistoricoAtividade> listarTodosHistorico = repo.findByAtividade_Id(atividadeId);

        // 2) mapeia para DTO que inclui id, nomeCompleto e statusCandidatura
        List<VoluntarioHistoricoResponseDTO> todosDTO = listarTodosHistorico.stream()
                .map(h -> {
                    var v = h.getVoluntario();
                    return new VoluntarioHistoricoResponseDTO(
                            Integer.valueOf(v.getId()),
                            v.getNomeCompleto(),
                            h.getStatusCandidatura().name());
                })
                .collect(Collectors.toList());

        // 3) monta e retorna o DTO de todos
        return new HistoricoAtividadeTodosResponseDTO(
                todosDTO.size(),
                todosDTO);
    }

    @Transactional(readOnly = true)
    public Page<HistoricoAtividadeDTO> buscarTodos(Pageable pageable) {
        Usuario usuario = usuarioService.getUsuarioAutenticado();

        return repo.buscarTodosPorId(usuario.getId(), pageable);
    }

    @Transactional(readOnly = true)
    public Page<HistoricoAtividadeDTO> buscarTodosPorData(String encerradoEm, Pageable pageable) {
        Usuario usuario = usuarioService.getUsuarioAutenticado();
        LocalDate data = LocalDate.parse(encerradoEm);
        Integer ano = data.getYear();
        Integer mes = data.getMonthValue();

        return repo.buscarTodosPorIdEData(usuario.getId(), ano, mes, pageable);
    }
}

// src/main/java/br/com/impacta/boacao/service/impl/HistoricoAtividadeServiceImpl.java
package br.com.impacta.boacao.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.com.impacta.boacao.dto.request.HistoricoAtividadeRequestDTO;
import br.com.impacta.boacao.dto.response.HistoricoAtividadeDTO;
import br.com.impacta.boacao.dto.response.HistoricoAtividadeResponseDTO;
import br.com.impacta.boacao.dto.response.HistoricoAtividadeTodosResponseDTO;
import br.com.impacta.boacao.dto.response.VoluntarioAprovadoResponseDTO;
import br.com.impacta.boacao.dto.response.VoluntarioHistoricoResponseDTO;
import br.com.impacta.boacao.entity.Atividade;
import br.com.impacta.boacao.entity.HistoricoAtividade;
// importar entity de Tag
import br.com.impacta.boacao.entity.Tag;
import br.com.impacta.boacao.entity.Usuario;
import br.com.impacta.boacao.entity.enums.StatusCandidatura;
import br.com.impacta.boacao.exception.RecursoNaoEncontradoException;
import br.com.impacta.boacao.repository.AtividadeRepository;
import br.com.impacta.boacao.repository.HistoricoAtividadeRepository;
import br.com.impacta.boacao.service.HistoricoAtividadeService;
import br.com.impacta.boacao.service.UsuarioService;
import br.com.impacta.boacao.repository.UsuarioRepository;
// importar log
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class HistoricoAtividadeServiceImpl implements HistoricoAtividadeService {

    private final HistoricoAtividadeRepository repo;
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private static final Logger log = LoggerFactory.getLogger(AtividadeServiceImpl.class);
    private final AtividadeRepository atividadeRepository;

    /**
     * Construtor para injeção de dependência do repositório.
     */
    public HistoricoAtividadeServiceImpl(HistoricoAtividadeRepository repo, UsuarioService usuarioService,
            UsuarioRepository usuarioRepository, AtividadeRepository atividadeRepository) {
        this.repo = repo;
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.atividadeRepository = atividadeRepository;
    }

    private Integer getIdUsuarioLogado() {
        log.debug("Buscando ID do usuário logado...");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email;

        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            // extrai o claim "username" do JWT (é o email no password-grant padrão)
            Jwt jwt = jwtAuth.getToken();
            email = jwt.getClaim("username");
            log.debug("Email extraído do JWT: {}", email);
        } else {
            // fallback: pode ser UsernamePasswordAuthenticationToken ou outro
            email = auth.getName();
            log.debug("Email extraído da autenticação: {}", email);
        }

        Usuario u = usuarioRepository.findByEmail(email);
        if (u == null) {
            log.error("Usuário não encontrado para o email: {}", email);
            throw new RecursoNaoEncontradoException("Usuário não encontrado: " + email);
        }

        Integer idOng = usuarioRepository.findIdOngByUserId(u.getId())
                .orElseThrow(() -> {
                    log.error("ID da ONG não encontrado para o ID do usuário: {}", u.getId());
                    return new RecursoNaoEncontradoException("idOng não encontrado: " + u.getId());
                });
        log.debug("ID da ONG encontrado: {}", idOng);
        return idOng;
    }

    /**
     * Busca o histórico de voluntários aprovados para uma determinada
     * atividade.
     *
     * @param request DTO contendo o ID da atividade
     * @return DTO de resposta com a quantidade e a lista de voluntários
     * aprovados
     */
    @Override
    public HistoricoAtividadeResponseDTO buscarHistorico(HistoricoAtividadeRequestDTO request) {

        // Extrai o ID da atividade do DTO de request
        Integer atividadeId = request.getAtividadeId();
        Integer idOng = getIdUsuarioLogado(); // Garante que a ONG logada só acesse os próprios dados

        atividadeRepository
                .findByIdAndOngId(atividadeId, idOng)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Atividade não encontrada ou não pertence à ONG."));

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
     * Retorna todos os voluntários vinculados à atividade, independente do
     * status.
     */
    @Override
    public HistoricoAtividadeTodosResponseDTO listaTodosVoluntariosInscritos(Integer atividadeId) {

        List<HistoricoAtividade> historicos = repo.findVoluntariosByAtividadeId(atividadeId);

        Integer idOng = getIdUsuarioLogado(); // Garante que a ONG logada só acesse os próprios dados

        atividadeRepository
                .findByIdAndOngId(atividadeId, idOng)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Atividade não encontrada ou não pertence à ONG."));

        List<VoluntarioHistoricoResponseDTO> dtos = historicos.stream()
                .map(ha -> {
                    var v = ha.getVoluntario();
                    String cidade = v.getEndereco() != null
                            ? v.getEndereco().getCidade()
                            : null;
                    List<String> tags = v.getTags().stream()
                            .map(Tag::getNome)
                            .distinct()
                            .toList();

                    return new VoluntarioHistoricoResponseDTO(
                            v.getId(), // id do voluntário
                            v.getNomeCompleto(),
                            ha.getStatusCandidatura().name(),
                            cidade,
                            tags,
                            ha.getId(), // <-- historicoId
                            ha.getAvaliacao() != null ? ha.getAvaliacao().getId() : null // <-- avaliacaoId
                    );
                })
                .toList();

        return new HistoricoAtividadeTodosResponseDTO(dtos.size(), dtos);
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

    @Override
    @Transactional
    public void atualizarStatusCandidatura(HistoricoAtividadeRequestDTO request, StatusCandidatura status) {
        Integer atvId = request.getAtividadeId();
        Integer volId = request.getVoluntarioId();

        Integer idOng = getIdUsuarioLogado(); // Garante que a ONG logada só acesse os próprios dados

        atividadeRepository
                .findByIdAndOngId(atvId, idOng)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Atividade não encontrada ou não pertence à ONG."));

        HistoricoAtividade ha = repo.findByAtividadeIdAndVoluntarioId(atvId, volId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                "Voluntário de ID " + volId
                + " não encontrado na atividade " + atvId));

        ha.setStatusCandidatura(status);

    }

}

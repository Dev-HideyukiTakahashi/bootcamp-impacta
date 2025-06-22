// src/main/java/br/com/impacta/boacao/service/AtividadeServiceImpl.java
package br.com.impacta.boacao.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.com.impacta.boacao.dto.request.AtividadeRequestDTO;
import br.com.impacta.boacao.dto.response.AtividadeResponseDTO;
import br.com.impacta.boacao.dto.response.AtividadeStatusResponseDTO;
import br.com.impacta.boacao.entity.Atividade;
import br.com.impacta.boacao.entity.Ong;
import br.com.impacta.boacao.entity.Tag;
import br.com.impacta.boacao.entity.Usuario;
import br.com.impacta.boacao.entity.enums.StatusAtividade;
import br.com.impacta.boacao.exception.RecursoNaoEncontradoException;
import br.com.impacta.boacao.mapper.AtividadeMapper;
import br.com.impacta.boacao.repository.AtividadeRepository;
import br.com.impacta.boacao.repository.OngRepository;
import br.com.impacta.boacao.repository.TagRepository;
import br.com.impacta.boacao.repository.UsuarioRepository;

@Service
public class AtividadeServiceImpl implements br.com.impacta.boacao.service.AtividadeService {

    private static final Logger log = LoggerFactory.getLogger(AtividadeServiceImpl.class);

    private final AtividadeRepository atividadeRepository;
    private final OngRepository ongRepository;
    private final TagRepository tagRepository;
    private final UsuarioRepository usuarioRepository;

    public AtividadeServiceImpl(AtividadeRepository atividadeRepository,
            OngRepository ongRepository,
            TagRepository tagRepository,
            UsuarioRepository usuarioRepository) {
        this.atividadeRepository = atividadeRepository;
        this.ongRepository = ongRepository;
        this.tagRepository = tagRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Extrai o email do Authentication atual, carrega o Usuario e retorna seu
     * idOng.
     */
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

    @Override
    @Transactional
    public AtividadeResponseDTO cadastrar(AtividadeRequestDTO dto
    ) {
        log.debug("Iniciando método 'cadastrar'...");
        Integer idOng = getIdUsuarioLogado();

        Atividade entidade = AtividadeMapper.toEntity(dto);
        entidade.setCriadoEm(LocalDateTime.now());
        log.debug("DTO mapeado para entidade: {}", entidade);

        Ong ong = ongRepository.findById(idOng)
                .orElseThrow(() -> {
                    log.error("ONG não encontrada para o ID: {}", idOng);
                    return new RecursoNaoEncontradoException("ONG não encontrada: " + idOng);
                });
        entidade.setOng(ong);

        if (dto.getIdTag() == null) {
            log.error("ID da Tag não informado no DTO.");
            throw new RecursoNaoEncontradoException("Tag não informada.");
        }
        Tag tag = tagRepository.findById(dto.getIdTag())
                .orElseThrow(() -> {
                    log.error("Tag não encontrada para o ID: {}", dto.getIdTag());
                    return new RecursoNaoEncontradoException("Tag não encontrada: " + dto.getIdTag());
                });
        entidade.setTag(tag);
        entidade.setTitulo(tag.getNome());

        Atividade saved = atividadeRepository.save(entidade);
        log.debug("Entidade salva: {}", saved);

        AtividadeResponseDTO resp = AtividadeMapper.toDTO(saved);
        resp.setIdOng(idOng);
        log.debug("Entidade mapeada para DTO de resposta: {}", resp);
        return resp;
    }

    @Override
    @Transactional(readOnly = true)
    public AtividadeResponseDTO getDadosAtividadePorId(Integer id) {
        Integer idOng = getIdUsuarioLogado(); // Garante que a ONG logada só acesse os próprios dados

        Atividade atividade = atividadeRepository
                .findByIdAndOngId(id, idOng)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Atividade não encontrada ou não pertence à ONG."
        ));

        AtividadeResponseDTO dto = AtividadeMapper.toDTO(atividade);
        dto.setIdOng(idOng);
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AtividadeResponseDTO> buscarTodas() {
        System.out.println("Iniciando método 'buscarTodos'...");

        // 1) Pega o ID da ONG a partir do token
        Integer idOng = getIdUsuarioLogado();
        System.out.println("ID da ONG logada: " + idOng);

        // 2) Busca todas as atividades daquela ONG
        System.out.println("Buscando atividades para a ONG com ID: " + idOng);
        List<Atividade> atividades = atividadeRepository.findAll_ByOngId(idOng);
        if (atividades.isEmpty()) {
            System.out.println("Nenhuma atividade encontrada para a ONG com ID: " + idOng);
        }

        // 3) Converte cada entidade em DTO e injeta o idOng no DTO
        System.out.println("Convertendo entidades para DTO...");
        return atividades.stream() // Itera sobre a lista de atividades
                .map(atividade -> {
                    // Mapeia cada atividade para um DTO
                    AtividadeResponseDTO dto = AtividadeMapper.toDTO(atividade);
                    dto.setIdOng(idOng); // Adiciona o ID da ONG ao DTO
                    System.out.println("DTO criado para atividade com ID: " + atividade.getId());
                    return dto; // Retorna o DTO
                })
                .collect(Collectors.toList()); // Coleta os DTOs em uma lista
    }

    @Override
    @Transactional
    public AtividadeResponseDTO atualizar(Integer id, AtividadeRequestDTO dto
    ) {
        log.info("Iniciando método 'atualizar' para o ID: {}", id);
        Integer idOng = getIdUsuarioLogado();
        log.info("ID da ONG logada: {}", idOng);

        // Verifica se a atividade existe e pertence à ONG logada
        Atividade existing = atividadeRepository
                .findByIdAndOngId(id, idOng)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Atividade não encontrada."
        ));

        log.info("Atividade existente encontrada: {}", existing);
        // Atualiza os campos da entidade com os dados do DTO
        Atividade entidade = AtividadeMapper.toEntity(dto);
        entidade.setId(existing.getId());
        entidade.setCriadoEm(existing.getCriadoEm());
        entidade.setStatusAtividade(existing.getStatusAtividade());
        entidade.setOng(existing.getOng());
        log.info("DTO mapeado para entidade para atualização: {}", entidade);

        if (dto.getIdTag() == null) {
            log.error("ID da Tag não informado no DTO.");
            throw new RecursoNaoEncontradoException("Tag não informada.");
        }
        Tag tag = tagRepository.findById(dto.getIdTag())
                .orElseThrow(() -> {
                    log.error("Tag não encontrada para o ID: {}", dto.getIdTag());
                    return new RecursoNaoEncontradoException("Tag não encontrada: " + dto.getIdTag());
                });
        entidade.setTag(tag);
        entidade.setTitulo(tag.getNome());

        Atividade updated = atividadeRepository.save(entidade);
        log.debug("Entidade atualizada: {}", updated);

        AtividadeResponseDTO resp = AtividadeMapper.toDTO(updated);
        resp.setIdOng(idOng);
        log.debug("Entidade atualizada mapeada para DTO de resposta: {}", resp);
        return resp;
    }

    /**
     * Atualiza somente o campo statusAtividade de uma Atividade. Permite apenas
     * ANDAMENTO → CONGELADA ou CONGELADA → ANDAMENTO. Caso contrário, lança
     * IllegalStateException.
     */
    @Override
    @Transactional
    public AtividadeStatusResponseDTO atualizarStatus(Integer id, StatusAtividade novoStatus) {
        // 1) Busca a atividade pelo ID
        Optional<Atividade> opt = atividadeRepository.findById(id);
        if (opt.isEmpty()) {
            throw new IllegalArgumentException("Atividade não encontrada com id: " + id);
        }

        Integer idOng = getIdUsuarioLogado();
        log.info("ID da ONG logada: {}", idOng);

        // Verifica se a atividade existe e pertence à ONG logada
        Atividade e = atividadeRepository
                .findByIdAndOngId(id, idOng)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Atividade não encontrada."
        ));

        Atividade atividade = opt.get();
        StatusAtividade atual = atividade.getStatusAtividade();

        // 2) Valida apenas ANDAMENTO→CONGELADA e CONGELADA→ANDAMENTO
        if ((atual == StatusAtividade.ANDAMENTO && novoStatus == StatusAtividade.CONGELADA)
                || (atual == StatusAtividade.CONGELADA && novoStatus == StatusAtividade.ANDAMENTO)
                || atual == StatusAtividade.ANDAMENTO && novoStatus == StatusAtividade.ENCERRADA) {
            atividade.setStatusAtividade(novoStatus);
        } else {
            throw new IllegalStateException(
                    "Transição de status não permitida: "
                    + atual + " → " + novoStatus
            );
        }

        // 3) Salva a mudança no repositório
        Atividade atualizado = atividadeRepository.save(atividade);

        // 4) Converte a entidade salva para um DTO que contenha apenas {id, statusAtividade}
        return AtividadeMapper.toStatusResponseDTO(atualizado);
    }
}

package br.com.impacta.boacao.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.impacta.boacao.dto.response.HistoricoAtividadeDTO;
import br.com.impacta.boacao.entity.Usuario;
import br.com.impacta.boacao.repository.HistoricoAtividadeRepository;

@Service
public class HistoricoAtividadeService {

    private final HistoricoAtividadeRepository historicoAtividadeRepository;
    private final UsuarioService usuarioService;

    public HistoricoAtividadeService(HistoricoAtividadeRepository historicoAtividadeRepository,
            UsuarioService usuarioService) {
        this.historicoAtividadeRepository = historicoAtividadeRepository;
        this.usuarioService = usuarioService;
    }

    @Transactional(readOnly = true)
    public Page<HistoricoAtividadeDTO> buscarTodos(Pageable pageable) {
        Usuario usuario = usuarioService.getUsuarioAutenticado();

        return historicoAtividadeRepository.buscarTodosPorId(usuario.getId(), pageable);
    }

    @Transactional(readOnly = true)
    public Page<HistoricoAtividadeDTO> buscarTodosPorData(String encerradoEm, Pageable pageable) {
        Usuario usuario = usuarioService.getUsuarioAutenticado();
        LocalDate data = LocalDate.parse(encerradoEm);
        Integer ano = data.getYear();
        Integer mes = data.getMonthValue();

        return historicoAtividadeRepository.buscarTodosPorIdEData(usuario.getId(), ano, mes, pageable);
    }
}

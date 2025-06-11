package br.com.impacta.boacao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.impacta.boacao.dto.response.HistoricoAtividadeDTO;
import br.com.impacta.boacao.entity.Usuario;
import br.com.impacta.boacao.repository.HistoricoAtividadeRepository;

@ExtendWith(MockitoExtension.class)
class HistoricoAtividadeServiceTest {

    @Mock
    private HistoricoAtividadeRepository historicoAtividadeRepository;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private HistoricoAtividadeService historicoAtividadeService;

    private Usuario usuario;
    private Pageable pageable;
    private Page<HistoricoAtividadeDTO> page;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1);
        pageable = mock(Pageable.class);
        page = mock(Page.class);
    }

    @Test
    void buscarTodos_DeveRetornarPageDeHistoricoAtividadeDTO_QuandoSucesso() {
        when(usuarioService.getUsuarioAutenticado()).thenReturn(usuario);
        when(historicoAtividadeRepository.buscarTodosPorId(usuario.getId(), pageable)).thenReturn(page);

        Page<HistoricoAtividadeDTO> result = historicoAtividadeService.buscarTodos(pageable);

        assertEquals(page, result);
        verify(usuarioService, times(1)).getUsuarioAutenticado();
        verify(historicoAtividadeRepository, times(1)).buscarTodosPorId(usuario.getId(), pageable);
    }

    @Test
    void buscarTodosPorData_DeveRetornarPageDeHistoricoAtividadeDTO_QuandoSucesso() {
        String encerradoEm = "2024-06-01";
        LocalDate data = LocalDate.parse(encerradoEm);
        Integer ano = data.getYear();
        Integer mes = data.getMonthValue();

        when(usuarioService.getUsuarioAutenticado()).thenReturn(usuario);
        when(historicoAtividadeRepository.buscarTodosPorIdEData(usuario.getId(), ano, mes, pageable)).thenReturn(page);

        Page<HistoricoAtividadeDTO> result = historicoAtividadeService.buscarTodosPorData(encerradoEm, pageable);

        assertEquals(page, result);
        verify(usuarioService, times(1)).getUsuarioAutenticado();
        verify(historicoAtividadeRepository, times(1)).buscarTodosPorIdEData(usuario.getId(), ano, mes, pageable);
    }
}
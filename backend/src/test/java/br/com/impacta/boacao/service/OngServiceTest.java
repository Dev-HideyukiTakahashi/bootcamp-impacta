package br.com.impacta.boacao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.impacta.boacao.dto.response.ListaOngResponse;
import br.com.impacta.boacao.entity.Ong;
import br.com.impacta.boacao.entity.Usuario;
import br.com.impacta.boacao.entity.Voluntario;
import br.com.impacta.boacao.repository.OngRepository;
import br.com.impacta.boacao.repository.VoluntarioRepository;

@ExtendWith(MockitoExtension.class)
class OngServiceTest {

    @Mock
    private OngRepository ongRepository;

    @Mock
    private VoluntarioRepository voluntarioRepository;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private OngService ongService;

    private Usuario usuario;
    private Voluntario voluntario;
    private Pageable pageable;
    private Page<Ong> pageOngs;
    private Page<ListaOngResponse> pageListaOngResponse;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1);

        voluntario = new Voluntario();
        voluntario.setId(1);

        pageable = mock(Pageable.class);

        pageOngs = (Page<Ong>) mock(Page.class);

        pageListaOngResponse = (Page<ListaOngResponse>) mock(Page.class);
    }

    @Test
    void buscarPorTag_DeveRetornarPageDeListaOngResponse_QuandoSucesso() {
        String tag = "Educação";

        when(usuarioService.getUsuarioAutenticado()).thenReturn(usuario);
        when(voluntarioRepository.getReferenceById(usuario.getId())).thenReturn(voluntario);
        when(ongRepository.buscarPorTag(tag, pageable)).thenReturn(pageOngs);
        when(pageOngs.<ListaOngResponse>map(any())).thenReturn(pageListaOngResponse);

        Page<ListaOngResponse> result = ongService.buscarPorTag(tag, pageable);

        assertEquals(pageListaOngResponse, result);
        verify(usuarioService, times(1)).getUsuarioAutenticado();
        verify(voluntarioRepository, times(1)).getReferenceById(usuario.getId());
        verify(ongRepository, times(1)).buscarPorTag(tag, pageable);
        verify(pageOngs, times(1)).map(any());
    }

    @Test
    void buscarPorEstado_DeveRetornarPageDeListaOngResponse_QuandoSucesso() {
        String estado = "SP";

        when(usuarioService.getUsuarioAutenticado()).thenReturn(usuario);
        when(voluntarioRepository.getReferenceById(usuario.getId())).thenReturn(voluntario);
        when(ongRepository.buscarPorEstado(estado, pageable)).thenReturn(pageOngs);
        when(pageOngs.<ListaOngResponse>map(any())).thenReturn(pageListaOngResponse);

        Page<ListaOngResponse> result = ongService.buscarPorEstado(estado, pageable);

        assertEquals(pageListaOngResponse, result);
        verify(usuarioService, times(1)).getUsuarioAutenticado();
        verify(voluntarioRepository, times(1)).getReferenceById(usuario.getId());
        verify(ongRepository, times(1)).buscarPorEstado(estado, pageable);
        verify(pageOngs, times(1)).map(any());
    }
}

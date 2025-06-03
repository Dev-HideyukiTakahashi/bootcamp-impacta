package br.com.impacta.boacao.service;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import br.com.impacta.boacao.dto.request.AtividadeRequestDTO;
import br.com.impacta.boacao.dto.request.AtividadeStatusRequestDTO;
import br.com.impacta.boacao.dto.response.AtividadeOngResponseDTO;
import br.com.impacta.boacao.dto.response.AtividadeResponseDTO;
import br.com.impacta.boacao.dto.response.AtividadeStatusResponseDTO;
import br.com.impacta.boacao.entity.Atividade;
import br.com.impacta.boacao.entity.Ong;
import br.com.impacta.boacao.entity.Tag;
import br.com.impacta.boacao.entity.enums.StatusAtividade;
import br.com.impacta.boacao.exception.RecursoNaoEncontradoException;
import br.com.impacta.boacao.factory.AtividadeFactory;
import br.com.impacta.boacao.mapper.AtividadeMapper;
import br.com.impacta.boacao.repository.AtividadeRepository;
import br.com.impacta.boacao.repository.OngRepository;
import br.com.impacta.boacao.repository.TagRepository;
import br.com.impacta.boacao.service.impl.AtividadeServiceImpl;
import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class AtividadeServiceTest {

    @Mock
    private AtividadeRepository atividadeRepository;
    @Mock
    private OngRepository ongRepository;
    @Mock
    private TagRepository tagRepository;
    @InjectMocks
    private AtividadeServiceImpl atividadeServiceImpl;

    private Atividade atividade;
    private AtividadeRequestDTO atividadeRequestDTO;
    private AtividadeResponseDTO atividadeResponseDTO;
    private AtividadeStatusRequestDTO atividadeStatusRequestDTO;
    private AtividadeStatusResponseDTO atividadeStatusResponseDTO;
    private Page<AtividadeOngResponseDTO> atividadePage;

    @BeforeEach
    void setUp() {
        atividade = AtividadeFactory.createAtividade();
        atividadeRequestDTO = AtividadeFactory.createAtividadeRequestDTO();
        atividadeResponseDTO = AtividadeFactory.createAtividadeResponseDTO();
        atividadeStatusRequestDTO = AtividadeFactory.createAtividadeStatusRequestDTO();
        atividadeStatusResponseDTO = AtividadeFactory.createAtividadeStatusResponseDTO();

        Ong ongMock = new Ong();
        ongMock.setId(atividadeRequestDTO.getIdOng());
        Mockito.lenient().when(ongRepository.findById(atividadeRequestDTO.getIdOng())).thenReturn(Optional.of(ongMock));
    }

    /*@Test
    void buscarTodos_DeveRetornarPageDeAtividades_QuandoSucesso() {
        Pageable pageable = PageRequest.of(0, 10);
        when(atividadeRepository.findAllAtividades(pageable)).thenReturn(atividadePage);

        Page<AtividadeOngResponseDTO> result = atividadeService.buscarTodos(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(atividadeRepository, times(1)).findAllAtividades(pageable);
    }*/

 /*@Test
    void buscarTodos_DeveRetornarPageVazia_QuandoNaoHouverAtividades() {
        Pageable pageable = PageRequest.of(0, 10);
        when(atividadeRepository.findAllAtividades(pageable)).thenReturn(Page.empty());

        Page<AtividadeOngResponseDTO> result = atividadeService.findAllAtividades(pageable);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(atividadeRepository, times(1)).findAllAtividades(pageable);
    }
    @Test
    void atualizarStatus_DeveAtualizarStatus_QuandoSucesso() {
        when(atividadeRepository.getReferenceById(1)).thenReturn(atividade);

        try (MockedStatic<AtividadeMapper> mockedMapper = mockStatic(AtividadeMapper.class)) {
            mockedMapper.when(() -> AtividadeMapper.toStatusResponseDTO(atividade))
                    .thenReturn(atividadeStatusResponseDTO);

            AtividadeStatusResponseDTO result = atividadeServiceImpl.atualizarStatus(1, atividadeStatusRequestDTO);

            assertNotNull(result);
            assertEquals(atividadeStatusResponseDTO.getStatusAtividade(), result.getStatusAtividade());

            verify(atividadeRepository, times(1)).getReferenceById(1);

            verify(atividadeRepository, never()).save(any());
        }
    }
*/
    @Test
    void atualizar_DeveLancarException_QuandoAtividadeNaoExiste() {
        when(atividadeRepository.getReferenceById(99)).thenThrow(RecursoNaoEncontradoException.class);

        assertThrows(RecursoNaoEncontradoException.class,
                () -> atividadeServiceImpl.atualizar(99, atividadeRequestDTO));
        verify(atividadeRepository, times(1)).getReferenceById(99);
        verify(atividadeRepository, never()).save(any());
    }

    @Test
    void cadastrar_DeveSalvarAtividadeComStatusAndamento_QuandoSucesso() {
        atividadeRequestDTO.setStatusAtividade(null);

        Ong ongMock = new Ong();
        ongMock.setId(atividadeRequestDTO.getIdOng());
        when(ongRepository.findById(atividadeRequestDTO.getIdOng())).thenReturn(Optional.of(ongMock));

        Tag tagMock = new Tag();
        tagMock.setId(1); // Ensure the ID matches the one used in the test
        tagMock.setNome("Tag Teste");
        when(tagRepository.findById(anyInt())).thenReturn(Optional.of(tagMock));

        when(atividadeRepository.save(any(Atividade.class))).thenReturn(atividade);

        AtividadeResponseDTO result = atividadeServiceImpl.cadastrar(atividadeRequestDTO);

        assertNotNull(result);
        assertEquals(StatusAtividade.ANDAMENTO, atividade.getStatusAtividade());
        verify(ongRepository, times(1)).findById(atividadeRequestDTO.getIdOng());
        verify(tagRepository, times(1)).findById(anyInt());
        verify(atividadeRepository, times(1)).save(any(Atividade.class));
    }

    @Test
    void cadastrar_DeveManterStatus_QuandoInformado() {
        atividadeRequestDTO.setStatusAtividade(StatusAtividade.ANDAMENTO);
        when(atividadeRepository.save(any(Atividade.class))).thenReturn(atividade);

        Tag tagMock = new Tag();
        tagMock.setId(1); // Ensure the ID matches the one used in the test
        tagMock.setNome("Tag Teste");
        when(tagRepository.findById(anyInt())).thenReturn(Optional.of(tagMock));

        AtividadeResponseDTO result = atividadeServiceImpl.cadastrar(atividadeRequestDTO);

        assertNotNull(result);
        assertEquals(StatusAtividade.ANDAMENTO, atividade.getStatusAtividade());
        verify(atividadeRepository, times(1)).save(any(Atividade.class));
    }

    @Test
    void atualizar_DeveAtualizarAtividade_QuandoSucesso() {
        Ong ongMock = new Ong();
        ongMock.setId(atividadeRequestDTO.getIdOng());
        when(ongRepository.findById(atividadeRequestDTO.getIdOng())).thenReturn(Optional.of(ongMock));

        Tag tagMock = new Tag();
        tagMock.setId(1); // Ensure the ID matches the one used in the test
        tagMock.setNome("Tag Teste");
        when(tagRepository.findById(anyInt())).thenReturn(Optional.of(tagMock));

        when(atividadeRepository.getReferenceById(anyInt())).thenReturn(atividade);
        when(atividadeRepository.save(any(Atividade.class))).thenReturn(atividade);

        AtividadeResponseDTO result = atividadeServiceImpl.atualizar(1, atividadeRequestDTO);

        assertNotNull(result);
        assertEquals(atividadeResponseDTO.getId(), result.getId());
        verify(ongRepository, times(1)).findById(atividadeRequestDTO.getIdOng());
        verify(atividadeRepository, times(1)).getReferenceById(1);
        verify(atividadeRepository, times(1)).save(atividade);
    }

    @Test
    void atualizar_DeveManterCriadoEm_QuandoAtualizar() {
        LocalDateTime dataOriginal = LocalDateTime.now().minusDays(1);
        atividade.setCriadoEm(dataOriginal);

        Ong ongMock = new Ong();
        ongMock.setId(atividadeRequestDTO.getIdOng());
        when(ongRepository.findById(atividadeRequestDTO.getIdOng())).thenReturn(Optional.of(ongMock));

        Tag tagMock = new Tag();
        tagMock.setId(1); // Ensure the ID matches the one used in the test
        tagMock.setNome("Tag Teste");
        when(tagRepository.findById(anyInt())).thenReturn(Optional.of(tagMock));

        when(atividadeRepository.getReferenceById(anyInt())).thenReturn(atividade);
        when(atividadeRepository.save(any(Atividade.class))).thenReturn(atividade);

        AtividadeResponseDTO result = atividadeServiceImpl.atualizar(1, atividadeRequestDTO);

        assertNotNull(result);
        assertEquals(dataOriginal, atividade.getCriadoEm());
        verify(ongRepository, times(1)).findById(atividadeRequestDTO.getIdOng());
        verify(tagRepository, times(1)).findById(anyInt());
        verify(atividadeRepository, times(1)).getReferenceById(1);
        verify(atividadeRepository, times(1)).save(atividade);
    }

    @Test
    void atualizar_DeveLancarRecursoNaoEncontradoException_ComMensagemCorreta() {
        int idInexistente = 999;
        when(atividadeRepository.getReferenceById(idInexistente)).thenThrow(EntityNotFoundException.class);

        Exception exception = assertThrows(RecursoNaoEncontradoException.class,
                () -> atividadeServiceImpl.atualizar(idInexistente, atividadeRequestDTO));

        assertEquals("Atividade não existe id: " + idInexistente, exception.getMessage());
        verify(atividadeRepository).getReferenceById(idInexistente);
    }
/* 
    @Test
    void atualizarStatus_DeveLancarException_QuandoAtividadeNaoExiste() {
        when(atividadeRepository.getReferenceById(99)).thenThrow(RecursoNaoEncontradoException.class);

        assertThrows(RecursoNaoEncontradoException.class,
                () -> atividadeServiceImpl.atualizarStatus(99, atividadeStatusRequestDTO));
        verify(atividadeRepository, times(1)).getReferenceById(99);
        verify(atividadeRepository, never()).save(any());
    }*/
    /*
    @Test
    void deletar_DeveDeletarAtividade_QuandoSucesso() {
        when(atividadeRepository.findAllAtividades(1)).thenReturn(true);
        doNothing().when(atividadeRepository).deleteById(1);

        assertDoesNotThrow(() -> atividadeService.deletar(1));
        verify(atividadeRepository, times(1)).findAllAtividades(1);
        verify(atividadeRepository, times(1)).deleteById(1);
    }

    @Test
    void deletar_DeveLancarRecursoNaoEncontradoException_QuandoAtividadeNaoExiste() {
        when(atividadeRepository.findAllAtividades(99)).thenReturn(false);

        assertThrows(RecursoNaoEncontradoException.class, () -> atividadeService.deletar(99));
        verify(atividadeRepository, times(1)).findAllAtividades(99);
        verify(atividadeRepository, never()).deleteById(anyInt());
    }

    @Test
    void deletar_DeveLancarDatabaseException_QuandoViolacaoIntegridade() {
        when(atividadeRepository.findAllAtividades(1)).thenReturn(true);
        doThrow(DataIntegrityViolationException.class).when(atividadeRepository).deleteById(1);

        assertThrows(DatabaseException.class, () -> atividadeService.deletar(1));
        verify(atividadeRepository, times(1)).findAllAtividades(1);
        verify(atividadeRepository, times(1)).deleteById(1);
    }*/

}

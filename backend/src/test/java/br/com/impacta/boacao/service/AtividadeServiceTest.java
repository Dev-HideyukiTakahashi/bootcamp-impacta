package br.com.impacta.boacao.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AtividadeServiceTest {

    // @Mock
    // private AtividadeRepository atividadeRepository;

    // @InjectMocks
    // private AtividadeService atividadeService;

    // private Atividade atividade;
    // private AtividadeRequestDTO atividadeRequestDTO;
    // private AtividadeResponseDTO atividadeResponseDTO;
    // private AtividadeStatusRequestDTO atividadeStatusRequestDTO;
    // private AtividadeStatusResponseDTO atividadeStatusResponseDTO;
    // private Page<AtividadeOngResponseDTO> atividadePage;

    // @BeforeEach
    // void setUp() {
    // atividade = AtividadeFactory.createAtividade();
    // atividadeRequestDTO = AtividadeFactory.createAtividadeRequestDTO();
    // atividadeResponseDTO = AtividadeFactory.createAtividadeResponseDTO();
    // atividadeStatusRequestDTO =
    // AtividadeFactory.createAtividadeStatusRequestDTO();
    // atividadeStatusResponseDTO =
    // AtividadeFactory.createAtividadeStatusResponseDTO();

    // List<AtividadeOngResponseDTO> atividades = List.of(
    // AtividadeFactory.createAtividadeOngResponseDTO()
    // );
    // atividadePage = new PageImpl<>(atividades);
    // }

    // @Test
    // void buscarTodos_DeveRetornarPageDeAtividades_QuandoSucesso() {
    // Pageable pageable = PageRequest.of(0, 10);
    // when(atividadeRepository.buscarTodosPage(pageable)).thenReturn(atividadePage);

    // Page<AtividadeOngResponseDTO> result =
    // atividadeService.buscarTodos(pageable);

    // assertNotNull(result);
    // assertEquals(1, result.getTotalElements());
    // verify(atividadeRepository, times(1)).buscarTodosPage(pageable);
    // }

    // @Test
    // void buscarTodos_DeveRetornarPageVazia_QuandoNaoHouverAtividades() {
    // Pageable pageable = PageRequest.of(0, 10);
    // when(atividadeRepository.buscarTodosPage(pageable)).thenReturn(Page.empty());

    // Page<AtividadeOngResponseDTO> result =
    // atividadeService.buscarTodos(pageable);

    // assertNotNull(result);
    // assertTrue(result.isEmpty());
    // verify(atividadeRepository, times(1)).buscarTodosPage(pageable);
    // }

    // @Test
    // void atualizarStatus_DeveAtualizarStatus_QuandoSucesso() {
    // when(atividadeRepository.getReferenceById(1)).thenReturn(atividade);

    // try (MockedStatic<AtividadeMapper> mockedMapper =
    // mockStatic(AtividadeMapper.class)) {
    // mockedMapper.when(() -> AtividadeMapper.toOngResponseDTO(atividade))
    // .thenReturn(atividadeStatusResponseDTO);

    // AtividadeStatusResponseDTO result = atividadeService.atualizarStatus(1,
    // atividadeStatusRequestDTO);

    // assertNotNull(result);
    // assertEquals(atividadeStatusResponseDTO.getStatusAtividade(),
    // result.getStatusAtividade());

    // verify(atividadeRepository, times(1)).getReferenceById(1);

    // verify(atividadeRepository, never()).save(any());
    // }
    // }

    // @Test
    // void atualizar_DeveLancarException_QuandoAtividadeNaoExiste() {
    // when(atividadeRepository.getReferenceById(99)).thenThrow(RecursoNaoEncontradoException.class);

    // assertThrows(RecursoNaoEncontradoException.class,
    // () -> atividadeService.atualizar(99, atividadeRequestDTO));
    // verify(atividadeRepository, times(1)).getReferenceById(99);
    // verify(atividadeRepository, never()).save(any());
    // }

    // @Test
    // void cadastrar_DeveSalvarAtividadeComStatusAndamento_QuandoSucesso() {
    // atividadeRequestDTO.setStatusAtividade(null);
    // when(atividadeRepository.save(any(Atividade.class))).thenReturn(atividade);

    // AtividadeResponseDTO result =
    // atividadeService.cadastrar(atividadeRequestDTO);

    // assertNotNull(result);
    // assertEquals(StatusAtividade.ANDAMENTO, atividade.getStatusAtividade());
    // verify(atividadeRepository, times(1)).save(any(Atividade.class));
    // }

    // @Test
    // void cadastrar_DeveManterStatus_QuandoInformado() {
    // atividadeRequestDTO.setStatusAtividade(StatusAtividade.ANDAMENTO);
    // when(atividadeRepository.save(any(Atividade.class))).thenReturn(atividade);

    // AtividadeResponseDTO result =
    // atividadeService.cadastrar(atividadeRequestDTO);

    // assertNotNull(result);
    // assertEquals(StatusAtividade.ANDAMENTO, atividade.getStatusAtividade());
    // verify(atividadeRepository, times(1)).save(any(Atividade.class));
    // }

    // @Test
    // void atualizar_DeveAtualizarAtividade_QuandoSucesso() {
    // when(atividadeRepository.getReferenceById(anyInt())).thenReturn(atividade);
    // when(atividadeRepository.save(any(Atividade.class))).thenReturn(atividade);

    // AtividadeResponseDTO result = atividadeService.atualizar(1,
    // atividadeRequestDTO);

    // assertNotNull(result);
    // assertEquals(atividadeResponseDTO.getId(), result.getId());
    // verify(atividadeRepository, times(1)).getReferenceById(1);
    // verify(atividadeRepository, times(1)).save(atividade);
    // }

    // @Test
    // void atualizar_DeveManterCriadoEm_QuandoAtualizar() {
    // LocalDateTime dataOriginal = LocalDateTime.now().minusDays(1);
    // atividade.setCriadoEm(dataOriginal);

    // when(atividadeRepository.getReferenceById(anyInt())).thenReturn(atividade);
    // when(atividadeRepository.save(any(Atividade.class))).thenReturn(atividade);

    // atividadeRequestDTO.setCriadoEm(LocalDateTime.now()); // Tentativa de alterar
    // a data
    // AtividadeResponseDTO result = atividadeService.atualizar(1,
    // atividadeRequestDTO);

    // assertNotNull(result);
    // assertEquals(dataOriginal, atividade.getCriadoEm());
    // }

    // @Test
    // void atualizar_DeveLancarRecursoNaoEncontradoException_ComMensagemCorreta() {
    // int idInexistente = 999;
    // when(atividadeRepository.getReferenceById(idInexistente)).thenThrow(EntityNotFoundException.class);

    // Exception exception = assertThrows(RecursoNaoEncontradoException.class,
    // () -> atividadeService.atualizar(idInexistente, atividadeRequestDTO));

    // assertEquals("Atividade não existe id: " + idInexistente,
    // exception.getMessage());
    // verify(atividadeRepository).getReferenceById(idInexistente);
    // }

    // @Test
    // void atualizarStatus_DeveLancarException_QuandoAtividadeNaoExiste() {
    // when(atividadeRepository.getReferenceById(99)).thenThrow(RecursoNaoEncontradoException.class);

    // assertThrows(RecursoNaoEncontradoException.class,
    // () -> atividadeService.atualizarStatus(99, atividadeStatusRequestDTO));
    // verify(atividadeRepository, times(1)).getReferenceById(99);
    // verify(atividadeRepository, never()).save(any());
    // }

    // @Test
    // void deletar_DeveDeletarAtividade_QuandoSucesso() {
    // when(atividadeRepository.existsById(1)).thenReturn(true);
    // doNothing().when(atividadeRepository).deleteById(1);

    // assertDoesNotThrow(() -> atividadeService.deletar(1));
    // verify(atividadeRepository, times(1)).existsById(1);
    // verify(atividadeRepository, times(1)).deleteById(1);
    // }

    // @Test
    // void
    // deletar_DeveLancarRecursoNaoEncontradoException_QuandoAtividadeNaoExiste() {
    // when(atividadeRepository.existsById(99)).thenReturn(false);

    // assertThrows(RecursoNaoEncontradoException.class, () ->
    // atividadeService.deletar(99));
    // verify(atividadeRepository, times(1)).existsById(99);
    // verify(atividadeRepository, never()).deleteById(anyInt());
    // }

    // @Test
    // void deletar_DeveLancarDatabaseException_QuandoViolacaoIntegridade() {
    // when(atividadeRepository.existsById(1)).thenReturn(true);
    // doThrow(DataIntegrityViolationException.class).when(atividadeRepository).deleteById(1);

    // assertThrows(DatabaseException.class, () -> atividadeService.deletar(1));
    // verify(atividadeRepository, times(1)).existsById(1);
    // verify(atividadeRepository, times(1)).deleteById(1);
    // }

}
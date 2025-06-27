package br.com.impacta.boacao.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.impacta.boacao.dto.request.TagRequestDTO;
import br.com.impacta.boacao.entity.Tag;
import br.com.impacta.boacao.entity.Voluntario;
import br.com.impacta.boacao.exception.RecursoNaoEncontradoException;
import br.com.impacta.boacao.mapper.TagMapper;
import br.com.impacta.boacao.repository.TagRepository;
import br.com.impacta.boacao.repository.VoluntarioRepository;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final UsuarioService usuarioService;
    private final VoluntarioRepository voluntarioRepository;
    private final Logger log = LoggerFactory.getLogger(TagService.class);

    public TagService(TagRepository tagRepository, UsuarioService usuarioService,
            VoluntarioRepository voluntarioRepository) {
        this.tagRepository = tagRepository;
        this.usuarioService = usuarioService;
        this.voluntarioRepository = voluntarioRepository;
    }

    @Transactional(readOnly = true)
    public List<TagRequestDTO> buscarTodos() {
        List<Tag> lista = tagRepository.findAll();
        log.info("Busca de todas as tags concluída com sucesso.");

        return lista.stream()
                .map(tag -> TagMapper.paraDTO(tag))
                .toList();
    }

    @Transactional(readOnly = true)
    public TagRequestDTO buscarPorId(Integer id) {
        Tag entidade = tagRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tag não encontrada! Id: " + id));
        log.info("Busca de tag realizada com sucesso! Id: {}", id);

        return TagMapper.paraDTO(entidade);
    }

    @Transactional
    public List<TagRequestDTO> adicionaTags(List<Integer> request) {

        Integer idLogado = usuarioService.getUsuarioAutenticado().getId();
        Voluntario voluntario = voluntarioRepository.getReferenceById(idLogado);

        voluntario.getTags().clear();

        atualizaTags(voluntario, request);

        Set<Tag> tags = voluntario.getTags();

        return tags
                .stream()
                .map(TagMapper::paraDTO)
                .collect(Collectors.toList());
    }

    private void atualizaTags(Voluntario voluntario, List<Integer> request) {
        for (Integer id : request) {
            Tag tag = tagRepository.getReferenceById(id);
            voluntario.addTag(tag);
        }
    }

}

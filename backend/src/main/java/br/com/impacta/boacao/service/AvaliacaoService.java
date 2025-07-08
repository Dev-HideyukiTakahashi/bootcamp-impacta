package br.com.impacta.boacao.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.impacta.boacao.dto.request.AvaliacaoRequestDTO;
import br.com.impacta.boacao.dto.response.AvaliacaoResponseDTO;
import br.com.impacta.boacao.entity.Avaliacao;
import br.com.impacta.boacao.entity.HistoricoAtividade;
import br.com.impacta.boacao.entity.Ong;
import br.com.impacta.boacao.entity.Usuario;
import br.com.impacta.boacao.exception.DomainException;
import br.com.impacta.boacao.mapper.AvaliacaoMapper;
import br.com.impacta.boacao.repository.AvaliacaoRepository;
import br.com.impacta.boacao.repository.HistoricoAtividadeRepository;
import br.com.impacta.boacao.repository.OngRepository;

@Service
public class AvaliacaoService {

    private final Logger log = LoggerFactory.getLogger(AvaliacaoService.class);

    private final AvaliacaoRepository avaliacaoRepository;
    private final UsuarioService usuarioService;
    private final OngRepository ongRepository;
    private final HistoricoAtividadeRepository historicoAtividadeRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, UsuarioService usuarioService,
            OngRepository ongRepository, HistoricoAtividadeRepository historicoAtividadeRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.usuarioService = usuarioService;
        this.ongRepository = ongRepository;
        this.historicoAtividadeRepository = historicoAtividadeRepository;
    }

    @Transactional
    public AvaliacaoResponseDTO avaliarVoluntario(AvaliacaoRequestDTO request) {
        // Busca ong logada
        Usuario usuario = usuarioService.getUsuarioAutenticado();
        Ong ong = ongRepository.findByUsuarioEmail(usuario.getEmail()).get();

        // Busca histórico de atividade
        HistoricoAtividade historicoAtividade = historicoAtividadeRepository
                .getReferenceById(request.getHistoricoAtividadeId());

        // Verifica se a atividade é da ong que está avaliando
        if (historicoAtividade.getAtividade().getOng().getId() != ong.getId()) {
            throw new DomainException("Essa atividade não percente a esta ong.");
        }

        // Verifica se já foi avaliado
        if (historicoAtividade.getAvaliacao() != null && historicoAtividade.getAvaliacao().isAvaliado()) {
            throw new DomainException("Voluntário já avaliado nessa atividade.");
        }

        // Cria a avaliação e salva
        Avaliacao avaliacao = buildAvaliacao(request, ong);
        avaliacao = avaliacaoRepository.save(avaliacao);

        // Atualiza o histórico de atividade
        historicoAtividade.setAvaliacao(avaliacao);

        log.info("Avaliação bem sucedida");
        return AvaliacaoMapper.toDTO(avaliacao, historicoAtividade);
    }

    public Avaliacao buildAvaliacao(AvaliacaoRequestDTO request, Ong ong) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setEstrelas(request.getEstrelas());
        avaliacao.setAvaliado(true);
        avaliacao.setOng(ong);
        avaliacao.setFeedback(request.getFeedback());

        return avaliacao;
    }

    public int getMediaAvaliacao(List<HistoricoAtividade> historicoAtividades) {
        double soma = 0;
        for (HistoricoAtividade atividade : historicoAtividades) {
            if(atividade.getAvaliacao() == null){
                return 0;
            }
            soma += atividade.getAvaliacao().getEstrelas();
        }

        int media = (int) Math.ceil(soma / historicoAtividades.size());

        return media;
    }

}

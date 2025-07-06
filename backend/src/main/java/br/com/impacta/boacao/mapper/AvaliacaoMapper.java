package br.com.impacta.boacao.mapper;

import br.com.impacta.boacao.dto.response.AvaliacaoResponseDTO;
import br.com.impacta.boacao.entity.Avaliacao;
import br.com.impacta.boacao.entity.HistoricoAtividade;

public class AvaliacaoMapper {

    public static AvaliacaoResponseDTO toDTO(Avaliacao avaliacao, HistoricoAtividade historicoAtividade) {
        AvaliacaoResponseDTO dto = new AvaliacaoResponseDTO();
        dto.setAtividadeNome(historicoAtividade.getAtividade().getNome());
        dto.setEstrelas(avaliacao.getEstrelas());
        dto.setFeedback(avaliacao.getFeedback());
        dto.setId(avaliacao.getId());
        dto.setVoluntarioNome(historicoAtividade.getVoluntario().getNomeCompleto());

        return dto;
    }

}

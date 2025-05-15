package br.com.impacta.boacao.mapper;

import br.com.impacta.boacao.dto.response.AtividadeOngResponseDTO;
import br.com.impacta.boacao.dto.response.AtividadeStatusResponseDTO;
import br.com.impacta.boacao.entity.Atividade;
import br.com.impacta.boacao.entity.enums.StatusAtividade;

import java.time.LocalDateTime;

public class AtividadeMapper {

    public static AtividadeStatusResponseDTO toOngResponseDTO(Atividade entidade) {

        return new AtividadeStatusResponseDTO(
                entidade.getId(),
                entidade.getStatusAtividade()
        );
    }
}

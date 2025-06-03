package br.com.impacta.boacao.mapper;

import br.com.impacta.boacao.dto.request.AtividadeRequestDTO;
import br.com.impacta.boacao.dto.response.AtividadeResponseDTO;
import br.com.impacta.boacao.dto.response.AtividadeStatusResponseDTO;
import br.com.impacta.boacao.entity.Atividade;

public class AtividadeMapper {

    public static AtividadeStatusResponseDTO toStatusResponseDTO(Atividade entidade) {
        return new AtividadeStatusResponseDTO(
            entidade.getId(),
            entidade.getStatusAtividade()
        );
    }

    public static AtividadeResponseDTO toDTO(Atividade ent) {
    AtividadeResponseDTO dto = new AtividadeResponseDTO();
    dto.setId(ent.getId());
    dto.setNome(ent.getNome());
    dto.setDescricao(ent.getDescricao());
    dto.setPeriodo(ent.getPeriodo());
    dto.setCargaHorariaDiaria(ent.getCargaHorariaDiaria());
    dto.setEnderecoCompleto(ent.getEnderecoCompleto());
    dto.setPossuiCertificacao(ent.getPossuiCertificacao());
    dto.setStatusAtividade(ent.getStatusAtividade());
    dto.setDataAtividade(ent.getDataAtividade());
    dto.setIdOng(ent.getOng().getId());



    // ID da Tag e Título
    dto.setIdTag(ent.getTag() != null ? ent.getTag().getId() : null);
    dto.setTitulo(ent.getTitulo());

    return dto;
}

    public static Atividade toEntity(AtividadeRequestDTO dto) {
    Atividade ent = new Atividade();
    ent.setId(dto.getId());
    ent.setNome(dto.getNome());
    ent.setDescricao(dto.getDescricao());
    ent.setPeriodo(dto.getPeriodo());
    ent.setCargaHorariaDiaria(dto.getCargaHorariaDiaria());
    ent.setEnderecoCompleto(dto.getEnderecoCompleto());
    ent.setPossuiCertificacao(dto.getPossuiCertificacao());
    ent.setStatusAtividade(dto.getStatusAtividade());
    ent.setDataAtividade(dto.getDataAtividade());
    
    // ONG e Tag serão associadas no Service, usando getReferenceById(...)
    return ent;
}
}

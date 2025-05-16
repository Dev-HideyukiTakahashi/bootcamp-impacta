package br.com.impacta.boacao.mapper;

import br.com.impacta.boacao.dto.request.NovaAtividadeRequestDTO;
import br.com.impacta.boacao.dto.response.AtividadeStatusResponseDTO;
import br.com.impacta.boacao.entity.Atividade;

public class AtividadeMapper {

    public static AtividadeStatusResponseDTO toOngResponseDTO(Atividade entidade) {

        return new AtividadeStatusResponseDTO(
                entidade.getId(),
                entidade.getStatusAtividade()
        );
    }

    public static Atividade toEntity(NovaAtividadeRequestDTO dto) {
        Atividade entidade = new Atividade();
        entidade.setId(dto.getId() == null ? null : dto.getId());
        entidade.setNome(dto.getNome());
        entidade.setDescricao(dto.getDescricao());
        entidade.setPeriodo(dto.getPeriodo());
        entidade.setCargaHorariaDiaria(dto.getCargaHorariaDiaria());
        entidade.setEnderecoCompleto(dto.getEnderecoCompleto());
        entidade.setPossuiCertificacao(dto.getPossuiCertificacao());
        entidade.setStatusAtividade(dto.getStatusAtividade());

        // DTO ONG TODO
        return entidade;
    }

    public static NovaAtividadeRequestDTO toNovaAtividadeDTO(Atividade entidade) {
        NovaAtividadeRequestDTO dto = new NovaAtividadeRequestDTO();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setDescricao(entidade.getDescricao());
        dto.setPeriodo(entidade.getPeriodo());
        dto.setCargaHorariaDiaria(entidade.getCargaHorariaDiaria());
        dto.setEnderecoCompleto(entidade.getEnderecoCompleto());
        dto.setPossuiCertificacao(entidade.getPossuiCertificacao());
        dto.setStatusAtividade(entidade.getStatusAtividade());
        dto.setCriadoEm(entidade.getCriadoEm());

        // DTO ONG TODO
        return dto;
    }
}

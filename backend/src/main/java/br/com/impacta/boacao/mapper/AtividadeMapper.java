package br.com.impacta.boacao.mapper;

import br.com.impacta.boacao.dto.request.AtividadeRequestDTO;
import br.com.impacta.boacao.dto.response.AtividadeResponseDTO;
import br.com.impacta.boacao.dto.response.AtividadeStatusResponseDTO;
import br.com.impacta.boacao.entity.Atividade;

public class AtividadeMapper {

    public static AtividadeStatusResponseDTO toOngResponseDTO(Atividade entidade) {

        return new AtividadeStatusResponseDTO(
                entidade.getId(),
                entidade.getStatusAtividade()
        );
    }

    public static Atividade toEntity(AtividadeRequestDTO dto) {
        Atividade entidade = new Atividade();
        entidade.setId(dto.getId() == null ? null : dto.getId());
        entidade.setNome(dto.getNome());
        entidade.setDescricao(dto.getDescricao());
        entidade.setPeriodo(dto.getPeriodo());
        entidade.setCargaHorariaDiaria(dto.getCargaHorariaDiaria());
        entidade.setEnderecoCompleto(dto.getEnderecoCompleto());
        entidade.setPossuiCertificacao(dto.getPossuiCertificacao());
        entidade.setStatusAtividade(dto.getStatusAtividade());
        entidade.setCriadoEm(dto.getCriadoEm() == null ? null : dto.getCriadoEm());
        // DTO ONG TODO
        return entidade;
    }

    public static AtividadeResponseDTO toDTO(Atividade entidade) {
        AtividadeResponseDTO dto = new AtividadeResponseDTO();
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

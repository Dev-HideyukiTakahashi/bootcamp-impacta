package br.com.impacta.boacao.mapper;

import br.com.impacta.boacao.dto.request.AtividadeRequestDTO;
import br.com.impacta.boacao.dto.response.AtividadeMinDTO;
import br.com.impacta.boacao.dto.response.AtividadeResponseDTO;
import br.com.impacta.boacao.dto.response.AtividadeStatusResponseDTO;
import br.com.impacta.boacao.entity.Atividade;
import br.com.impacta.boacao.entity.HistoricoAtividade;
import br.com.impacta.boacao.entity.Voluntario;
import br.com.impacta.boacao.entity.enums.StatusCandidatura;

public class AtividadeMapper {

    public static AtividadeStatusResponseDTO toOngResponseDTO(Atividade entidade) {

        return new AtividadeStatusResponseDTO(
                entidade.getId(),
                entidade.getStatusAtividade());
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

    public static AtividadeMinDTO toMinDTO(Atividade entidade, Voluntario voluntario) {

        StatusCandidatura status = buscarStatusCandidatura(entidade, voluntario);

        return new AtividadeMinDTO(
                entidade.getId(),
                entidade.getPeriodo(),
                entidade.getCargaHorariaDiaria(),
                entidade.getPossuiCertificacao(),
                entidade.getDescricao(),
                status);
    }

    private static StatusCandidatura buscarStatusCandidatura(Atividade atividade, Voluntario voluntario) {
        return atividade.getHistoricoAtividades().stream()
                .filter(h -> h.getVoluntario().equals(voluntario))
                .findFirst()
                .map(HistoricoAtividade::getStatusCandidatura)
                .orElse(null);
    }

}

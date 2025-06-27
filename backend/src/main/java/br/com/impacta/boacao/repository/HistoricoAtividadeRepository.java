package br.com.impacta.boacao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.impacta.boacao.dto.response.HistoricoAtividadeDTO;
import br.com.impacta.boacao.entity.HistoricoAtividade;
import br.com.impacta.boacao.entity.enums.StatusCandidatura;

public interface HistoricoAtividadeRepository extends JpaRepository<HistoricoAtividade, Integer> {

    @Query("""
            SELECT new br.com.impacta.boacao.dto.response.HistoricoAtividadeDTO(
                ha.id,
                atividade.descricao,
                atividade.cargaHorariaDiaria,
                atividade.periodo,
                ha.dataInscricao,
                avaliacao.estrelas,
                ha.certificado,
                ong.nomeEntidade,
                avaliacao.feedback,
                ha.encerradoEm
            )
            FROM HistoricoAtividade ha
            JOIN ha.atividade atividade
            JOIN ha.avaliacao avaliacao
            JOIN ha.voluntario voluntario
            JOIN voluntario.usuario usuario
            JOIN atividade.ong ong
            WHERE usuario.id = :usuarioId
            """)
    Page<HistoricoAtividadeDTO> buscarTodosPorId(Integer usuarioId, Pageable pageable);

    @Query("""
            SELECT new br.com.impacta.boacao.dto.response.HistoricoAtividadeDTO(
                ha.id,
                atividade.descricao,
                atividade.cargaHorariaDiaria,
                atividade.periodo,
                ha.dataInscricao,
                avaliacao.estrelas,
                ha.certificado,
                ong.nomeEntidade,
                avaliacao.feedback,
                ha.encerradoEm
            )
            FROM HistoricoAtividade ha
            JOIN ha.atividade atividade
            JOIN ha.avaliacao avaliacao
            JOIN ha.voluntario voluntario
            JOIN voluntario.usuario usuario
            JOIN atividade.ong ong
            WHERE YEAR(ha.encerradoEm) = :ano
            AND MONTH(ha.encerradoEm) = :mes
            AND usuario.id = :usuarioId
            """)
    Page<HistoricoAtividadeDTO> buscarTodosPorIdEData(Integer usuarioId, Integer ano, Integer mes, Pageable pageable);

    // devolve todas as linhas do histórico para uma atividade + status
    List<HistoricoAtividade> findByAtividade_IdAndStatusCandidatura(
            Integer atividadeId,
            StatusCandidatura statusCandidatura);

    // devolve a quantidade de voluntários aprovados para uma atividade
    long countByAtividadeIdAndStatusCandidatura(
            Integer atividadeId,
            StatusCandidatura statusCandidatura);

    // devolve todas os voluntarios da atividade
    List<HistoricoAtividade> findByAtividade_Id(Integer atividadeId);
}

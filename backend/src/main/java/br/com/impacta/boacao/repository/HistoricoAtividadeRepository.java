package br.com.impacta.boacao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.impacta.boacao.dto.response.HistoricoAtividadeDTO;
import br.com.impacta.boacao.entity.HistoricoAtividade;

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

}

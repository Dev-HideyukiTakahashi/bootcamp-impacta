package br.com.impacta.boacao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.impacta.boacao.entity.Atividade;
import br.com.impacta.boacao.entity.enums.StatusAtividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Integer> {

    Page<Atividade> findAllByOngId(Integer ongId, Pageable p);

    Optional<Atividade> findByIdAndOngId(Integer id, Integer ongId);

    List<Atividade> findAll_ByOngId(Integer ongId);

    // PARA ACHAR UMA ATIVIDADE PELO ID
    Optional<Atividade> findById(Integer id);

    List<Atividade> findByStatusAtividade(StatusAtividade status);

    @Query("SELECT a FROM Atividade a WHERE a.ong.endereco.estado = :estado")
    Page<Atividade> buscarAtividadePorEstadoOng(String estado, Pageable pageable);

    @Query("SELECT a FROM Atividade a WHERE a.tag.nome LIKE LOWER(CONCAT('%', :tag, '%'))")
    Page<Atividade> buscarAtividadePorTag(String tag, Pageable pageable);

}
/*
 * // Consulta customizada com JPQL
 * 
 * @Query("""
 * SELECT new br.com.impacta.boacao.dto.response.AtividadeOngResponseDTO(
 * obj.id,
 * obj.nome,
 * COUNT(obj.id),
 * obj.cargaHorariaDiaria,
 * obj.periodo,
 * obj.criadoEm,
 * obj.statusAtividade,
 * obj.descricao,
 * obj.dataAtividade,
 * obj.ong.id
 * )
 * FROM Atividade obj
 * GROUP BY obj.id, obj.nome, obj.cargaHorariaDiaria, obj.periodo, obj.criadoEm,
 * obj.statusAtividade, obj.descricao, obj.dataAtividade,
 * obj.ong.id
 * """)
 */

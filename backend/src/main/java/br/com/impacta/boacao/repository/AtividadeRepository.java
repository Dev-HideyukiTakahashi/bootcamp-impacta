package br.com.impacta.boacao.repository;

import br.com.impacta.boacao.dto.response.AtividadesOngResponseDTO;
import br.com.impacta.boacao.entity.Atividade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AtividadeRepository extends JpaRepository<Atividade, Integer> {

    // Consulta customizada com JPQL
    @Query("""
    SELECT new br.com.impacta.boacao.dto.response.AtividadesOngResponseDTO(
        obj.id,
        obj.nome,
        COUNT(ha.id),
        obj.cargaHorariaDiaria,
        obj.periodo,
        obj.criadoEm,
        obj.statusAtividade,
        obj.descricao
    )
    FROM Atividade obj
    LEFT JOIN obj.historicoAtividades ha
        ON ha.statusCandidatura = br.com.impacta.boacao.entity.enums.StatusCandidatura.APROVADO
    GROUP BY obj.id, obj.nome, obj.cargaHorariaDiaria, obj.periodo, obj.criadoEm, obj.statusAtividade, obj.descricao
""")
    Page<AtividadesOngResponseDTO> buscarTodosPage (Pageable pageable);
}

package br.com.impacta.boacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.impacta.boacao.entity.HistoricoAtividade;
import br.com.impacta.boacao.entity.enums.StatusCandidatura;

public interface HistoricoAtividadeRepository
        extends JpaRepository<HistoricoAtividade, Integer> {

    // devolve todas as linhas do histórico para uma atividade + status
    List<HistoricoAtividade> 
        findByAtividade_IdAndStatusCandidatura(
            Integer atividadeId,
            StatusCandidatura statusCandidatura);
        // devolve a quantidade de voluntários aprovados para uma atividade     
        long countByAtividadeIdAndStatusCandidatura(
                Integer atividadeId,
                StatusCandidatura statusCandidatura);
        // devolve todas os voluntarios da atividade
        List<HistoricoAtividade> findByAtividade_Id(Integer atividadeId);
        
}

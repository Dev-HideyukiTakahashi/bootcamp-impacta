package br.com.impacta.boacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.impacta.boacao.entity.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer>{
    // busca a avaliação associada a um historico de atividade
    Optional<Avaliacao> findByHistoricoAtividade_Id(Integer historicoAtividadeId);
}

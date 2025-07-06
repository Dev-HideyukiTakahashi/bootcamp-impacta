package br.com.impacta.boacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.impacta.boacao.entity.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer>{
    
}

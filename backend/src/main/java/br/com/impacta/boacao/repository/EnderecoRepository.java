package br.com.impacta.boacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.impacta.boacao.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {}

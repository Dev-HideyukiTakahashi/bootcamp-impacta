package br.com.impacta.boacao.repository;

import br.com.impacta.boacao.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
  }
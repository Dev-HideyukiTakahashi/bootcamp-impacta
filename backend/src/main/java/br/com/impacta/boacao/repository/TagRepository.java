package br.com.impacta.boacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.impacta.boacao.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {
}
package br.com.impacta.boacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.impacta.boacao.entity.Voluntario;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Integer> {
  boolean existsByCpf(String cpf);
}
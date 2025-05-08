package br.com.impacta.boacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.impacta.boacao.entity.Voluntario;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Integer> {
  boolean existsByEmail(String email);
  boolean existsByCpf(String cpf);
}
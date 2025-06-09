package br.com.impacta.boacao.repository;

import br.com.impacta.boacao.entity.Ong;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OngRepository extends JpaRepository<Ong, Integer> {
    boolean existsByCnpj(String cnpj);
    Optional<Ong> findByUsuarioEmail(String email);
}

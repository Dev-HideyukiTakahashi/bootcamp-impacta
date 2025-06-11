package br.com.impacta.boacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.impacta.boacao.entity.Ong;

public interface OngRepository extends JpaRepository<Ong, Integer> {
    Optional<Ong> findByUsuarioEmail(String email);
    boolean existsByCnpj(String cnpj);
    Integer findById(int id);
}


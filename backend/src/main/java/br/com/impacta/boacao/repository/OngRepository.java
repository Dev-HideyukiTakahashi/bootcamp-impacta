package br.com.impacta.boacao.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.impacta.boacao.entity.Ong;

public interface OngRepository extends JpaRepository<Ong, Integer> {

    Optional<Ong> findByUsuarioEmail(String email);

    boolean existsByCnpj(String cnpj);

    Integer findById(int id);

    // Busca todas as ongs filtradas por TAG
    @Query("SELECT o FROM Ong o JOIN o.tags t WHERE t.nome LIKE %:tag%")
    Page<Ong> buscarPorTag(String tag, Pageable pageable);

    // Busca todas as ongs filtradas por ESTADO
    @Query("SELECT o FROM Ong o JOIN o.endereco e WHERE e.estado = :estado")
    Page<Ong> buscarPorEstado(String estado, Pageable pageable);
}

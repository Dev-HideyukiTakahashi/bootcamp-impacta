package br.com.impacta.boacao.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.impacta.boacao.entity.Ong;

public interface OngRepository extends JpaRepository<Ong, Integer> {
    boolean existsByCnpj(String cnpj);

    Optional<Ong> findByUsuarioEmail(String email);

    // Busca todas as ongs filtradas por TAG
    @Query("SELECT o FROM Ong o JOIN o.tags t WHERE t.nome LIKE %:tag%")
    Page<Ong> buscarPorTag(String tag, Pageable pageable);

}

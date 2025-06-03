package br.com.impacta.boacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.impacta.boacao.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByEmail(String Email);

    Usuario findByEmail(String email);

    @Query(
            value = "SELECT o.id AS idOng "
            + "FROM ong o "
            + "JOIN usuario u ON u.id = o.usuario_id "
            + "WHERE u.id = :userId",
            nativeQuery = true
    )
    Optional<Integer> findIdOngByUserId(@Param("userId") Integer userId);

}

package br.com.impacta.boacao.repository;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.impacta.boacao.entity.PasswordRecover;

public interface PasswordRecoverRepository extends JpaRepository<PasswordRecover, Long> {

    // Consulta JPQL, checar se já passou mais de 30 minutos do envio do token UUID
    @Query("SELECT obj FROM PasswordRecover obj WHERE obj.token = :token AND obj.expiration > :now")
    Optional<PasswordRecover> searchValidToken(String token, Instant now);

}

package br.com.impacta.boacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.impacta.boacao.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByEmail(String Email);
    Usuario findByEmail(String email);

}
    
package br.com.impacta.boacao.dto;

import java.sql.Timestamp;
import java.time.Instant;

import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO utilizado para requisições de criação e atualização (POST, PUT) de
 * usuários.
 * Esta classe serve como superclasse para as requisições de voluntários e ONGs,
 * encapsulando os campos comuns entre eles.
 */
public class UsuarioRequestDTO {

    @NotNull(message = "O email não pode ser nulo.")
    @Email(message = "Email inválido.")
    private String email;

    @NotNull(message = "A senha não pode ser nula.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String senha;

    private Timestamp criadoEm;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Timestamp getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Timestamp criadoEm) {
        this.criadoEm = criadoEm;
    }

    @PrePersist
    public void prePersist() {
        // Atribui o momento atual (em UTC) para o campo criadoEm
        // quando a entidade for persistida no banco de dados
        this.criadoEm = Timestamp.from(Instant.now());
    }
}

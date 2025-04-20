package br.com.impacta.boacao.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entidade base para os usuários do sistema.
 * Esta classe será utilizada como superclasse para entidades específicas como
 * Voluntário e ONG, compartilhando atributos comuns.
 * Além disso, será utilizada no processo de autenticação com OAuth2,
 * integrando-se à configuração do Spring Security.
 */
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String senha;

    @Column(columnDefinition = "DATETIME") // definindo tipo para SQL server
    private Timestamp criadoEm;

    public Usuario() {

    }

    public Usuario(int id, String email, String senha, Timestamp criadoEm) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.criadoEm = criadoEm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
package br.com.impacta.boacao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entidade que representa um papel (role) no sistema.
 * 
 * Esta classe é utilizada para atribuir diferentes perfis de acesso
 * aos usuários, como por exemplo "ROLE_VOLUNTARIO" ou "ROLE_ONG".
 * 
 * Ela será usada durante o processo de autenticação e autorização
 * com OAuth2, permitindo o controle de permissões no sistema.
 */
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String authority;

    public Role() {
    }

    public Role(Integer id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Role other = (Role) obj;
        if (id == null) {
            return other.id == null;
        } else
            return id.equals(other.id);
    }
}
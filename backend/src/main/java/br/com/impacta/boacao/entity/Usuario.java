package br.com.impacta.boacao.entity;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * Entidade base para os usuários do sistema.
 * Esta classe será utilizada como superclasse para entidades específicas como
 * Voluntário e ONG, compartilhando atributos comuns.
 * Além disso, será utilizada no processo de autenticação com OAuth2,
 * integrando-se à configuração do Spring Security.
 */
@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String senha;

    @Column(columnDefinition = "DATETIME") // definindo tipo para SQL server
    private Timestamp criadoEm;

    @ManyToOne
    private Role role;

    public Usuario() {

    }

    public Usuario(int id, String email, String senha, Timestamp criadoEm) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.criadoEm = criadoEm;
    }

    // user
    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Verifica se o usuário possui o papel (role) especificado.
     * Este método verifica se o papel do usuário corresponde ao papel fornecido
     * como parâmetro.
     * 
     * @param roleName o nome do papel a ser verificado (ex: "ROLE_VOLUNTARIO")
     * @return true se o usuário possui o papel informado, ou false se o papel não
     *         for encontrado
     */
    public boolean hasRole(String roleName) {
        if (role != null && roleName.equals(role.getAuthority())) {
            return true;
        }
        return false;
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
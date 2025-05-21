package br.com.impacta.boacao.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 * Entidade base para as ONGs do sistema.
 */
@Entity
public class Ong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomeOng;
    private String cnpj;
    private String telefone;
    private String endereco;
   // private String cep;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public Ong() {
    }

    public Ong(int id, String nomeOng, String cnpj, String telefone, String endereco/* , String cep*/) {
        this.id = id;
        this.nomeOng = nomeOng;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.endereco = endereco;
        //this.cep = cep;
    }

    public Ong(int id, String nomeOng, String cnpj) {
        this.id = id;
        this.nomeOng = nomeOng;
        this.cnpj = cnpj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeOng() {
        return nomeOng;
    }

    public void setNomeOng(String nomeOng) {
        this.nomeOng = nomeOng;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

   /*  public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }*/

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

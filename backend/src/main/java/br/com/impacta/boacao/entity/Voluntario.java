package br.com.impacta.boacao.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;




/**
 * Entidade base para os voluntarios do sistema.
 */
@Entity
public class Voluntario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int    id;
    private String nomeCompleto;
    private String cpf;
    private String telefone;
    private Date   dataNascimento;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public Voluntario(){

    }
    public Voluntario(int id, String nomeCompleto, String cpf, String telefone, Date dataNascimento) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;    }
    
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    
    public String getNomeCompleto() {
      return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
      this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
      return cpf;
    }

    public void setCpf(String cpf) {
      this.cpf = cpf;
    }

    public String getTelefone() {
      return telefone;
    }

    public void setTelefone(String telefone) {
      this.telefone = telefone;
    }

    public Date getDataNascimento() {
      return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
      this.dataNascimento = dataNascimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}

package br.com.impacta.boacao.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;




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
    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String telefone;
    private Date   dataNascimento;



    /*@ManyToOne
    private Role role;

    public Voluntario(){

    }*/
    public Voluntario(){

    }
    public Voluntario(int id, String nomeCompleto, String cpf, String cep, String rua, String numero, 
    String complemento, String cidade, String estado, String telefone, Date dataNascimento) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
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


    public String getCep() {
      return cep;
    }

    public void setCep(String cep) {
      this.cep = cep;
    }

    public String getRua() {
      return rua;
    }

    public void setRua(String rua) {
      this.rua = rua;
    }

    public String getNumero() {
      return numero;
    }

    public void setNumero(String numero) {
      this.numero = numero;
    }

    public String getComplemento() {
      return complemento;
    }

    public void setComplemento(String complemento) {
      this.complemento = complemento;
    }

    public String getCidade() {
      return cidade;
    }

    public void setCidade(String cidade) {
      this.cidade = cidade;
    }

    public String getEstado() {
      return estado;
    }

    public void setEstado(String estado) {
      this.estado = estado;
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

    /*public Timestamp getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Timestamp criadoEm) {
        this.criadoEm = criadoEm;
    }*/
/*
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }*/
    /* dados de login , vou isnerit des */
}

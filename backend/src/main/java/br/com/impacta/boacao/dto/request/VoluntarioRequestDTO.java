package br.com.impacta.boacao.dto.request;

import java.util.Date;

public class VoluntarioRequestDTO {

  private String nomeCompleto;
  private String cpf;
  private String email;
  private String senha;
  private String cep;
  private String rua;
  private String numero;
  private String complemento;
  private String cidade;
  private String estado;
  private String telefone;
  private Date dataNascimento;

  
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

  // Construtor completo
  public VoluntarioRequestDTO(
      String nomeCompleto,
      String cpf,
      String email,
      String senha,
      String cep,
      String rua,
      String numero,
      String complemento,
      String cidade,
      String estado,
      String telefone,
      Date dataNascimento
  ) {
    this.nomeCompleto = nomeCompleto;
    this.cpf = cpf;
    this.email = email;
    this.senha = senha;
    this.cep = cep;
    this.rua = rua;
    this.numero = numero;
    this.complemento = complemento;
    this.cidade = cidade;
    this.estado = estado;
    this.telefone = telefone;
    this.dataNascimento = dataNascimento;
  }

}

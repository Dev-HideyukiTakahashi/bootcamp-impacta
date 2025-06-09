package br.com.impacta.boacao.dto.request;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;


public class VoluntarioRequestDTO {
  
  @NotBlank
  private String nomeCompleto;

  @NotBlank
  private String cpf;

  @NotBlank
  private String telefone;

  @NotNull
  private LocalDate dataNascimento;

  @NotBlank
  private String email;

  @NotBlank
  private String senha;

  @NotNull
  private EnderecoRequestDTO endereco;
  
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

  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
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
  
    public EnderecoRequestDTO getEndereco() {
    return endereco;
  }

  public void setEndereco(EnderecoRequestDTO endereco) {
    this.endereco = endereco;
  }

  // Construtor completo
  public VoluntarioRequestDTO(
      String nomeCompleto,
      String cpf,
      String telefone,
      LocalDate dataNascimento,
      String email,
      String senha,
      EnderecoRequestDTO endereco) {
    this.nomeCompleto = nomeCompleto;
    this.cpf = cpf;
    this.telefone = telefone;
    this.dataNascimento = dataNascimento;
    this.email = email;
    this.senha = senha;
    this.endereco = endereco;
  }

}

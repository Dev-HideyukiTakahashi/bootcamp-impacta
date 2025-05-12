package br.com.impacta.boacao.dto.request;
import java.util.Date;

//import jakarta.validation.constraints.NotBlank;
public class VoluntarioRequestDTO {
  
  private String nomeCompleto;
  private String cpf;
  private String telefone;
  private Date dataNascimento;
  private String email;
  private String senha;


  
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
  
  // Construtor completo
  public VoluntarioRequestDTO(
      String nomeCompleto,
      String cpf,
      String telefone,
      Date dataNascimento,
      String email,
      String senha
  ) {
    this.nomeCompleto = nomeCompleto;
    this.cpf = cpf;
    this.telefone = telefone;
    this.dataNascimento = dataNascimento;
    this.email = email;
    this.senha = senha;
  }


}

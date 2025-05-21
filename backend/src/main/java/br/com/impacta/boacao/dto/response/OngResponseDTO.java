package br.com.impacta.boacao.dto.response;

public class OngResponseDTO {
  private int id;
  private String nomeOng;
  private String cnpj;
  private String telefone;
  private String endereco;
 // private String cep;
  private String email;

 

  public OngResponseDTO(int id, String nomeOng, String cnpj) {
    this.id = id;
    this.nomeOng = nomeOng;
    this.cnpj = cnpj;
  }
  // Construtor
  public OngResponseDTO(String nomeOng, String cnpj, String telefone, String endereco,  String email) {
    this.nomeOng = nomeOng;
    this.cnpj = cnpj;
    this.telefone = telefone;
    this.endereco = endereco;
   // this.cep = cep;
    this.email = email;
  }

  
  // Getters e Setters

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
/* 
  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }
*/
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}

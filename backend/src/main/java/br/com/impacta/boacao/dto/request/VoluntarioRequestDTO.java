package br.com.impacta.boacao.dto.request;
import java.util.Date;

//import jakarta.validation.constraints.NotBlank;
public class VoluntarioRequestDTO {
  
  //@NotBlank(message = "O nome completo é obrigatório")
  private String nomeCompleto;

 // @NotBlank(message = "O numero do documento (cpf) é obrigatório")
  private String cpf;
  
 //@NotBlank(message = "O preenchimento do CEP é obrigatório")
  private String cep;

//  @NotBlank(message = "O preenchimento do seu endereço é obrigatório")
  private String rua;

//  @NotBlank(message = "O preenchimento do número é obrigatório")
  private String numero;
  
//  @NotBlank(message = "O preenchimento da Cidade é obrigatório")
  private String cidade;
  
//  @NotBlank(message = "O preenchimento do estado é obrigatório")
  private String estado;
  
//  @NotBlank(message = "O preenchimento do telefone é obrigatório")
  private String telefone;
  
 // @NotBlank(message = "O preenchimento da data de nascimento é obrigatório")
  private Date dataNascimento;
  // nao obrigatorio
  private String complemento;
  
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

  // Construtor completo
  public VoluntarioRequestDTO(
      String nomeCompleto,
      String cpf,
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

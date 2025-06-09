package br.com.impacta.boacao.dto.response;

/**
 * DTO utilizado para respostas (responses) relacionadas ao voluntario.
 * Aplicado em requisições do tipo GET, este objeto
 * contém apenas os dados essenciais que devem ser retornados ao cliente,
 * sendo eles o  nomeCompleto e email
 */
public class VoluntarioResponseDTO {
  private int id;
  private String nomeCompleto;
  
  
  // Construtor vazio
  public VoluntarioResponseDTO() {
  }

  // Construtor com todos os campos
  public VoluntarioResponseDTO(int id, String nomeCompleto) {
    this.id = id;
    this.nomeCompleto = nomeCompleto;
  }

  public VoluntarioResponseDTO(String nomeCompleto){
    this.nomeCompleto = nomeCompleto;
  }

  //GS
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String  getNomeCompleto(){
    return nomeCompleto;
  }

  public void setNomeCompleto(String nomeCompleto ){
    this.nomeCompleto = nomeCompleto;
  }


}

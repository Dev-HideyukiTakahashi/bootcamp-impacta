package br.com.impacta.boacao.entity;

import jakarta.persistence.*;

@Entity
public class Endereco {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String pais;
  private String estado;
  private String cidade;
  private String cep;
  private String rua;
  private String numero;
  private String bairro;

  @OneToOne(mappedBy = "endereco")
  private Voluntario voluntario;

  @OneToOne(mappedBy = "endereco")
  private Ong ong;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPais() {
    return pais;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
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

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public Voluntario getVoluntario() {
    return voluntario;
  }

  public void setVoluntario(Voluntario voluntario) {
    this.voluntario = voluntario;
  }

    public Ong getong() {
    return ong;
  }

  public void setOng(Ong ong) {
    this.ong = ong;
  }
}

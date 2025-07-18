package br.com.impacta.boacao.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

/**
 * Entidade base para os voluntarios do sistema.
 */
@Entity
public class Voluntario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String nomeCompleto;
  private String cpf;
  private String telefone;
  @Column(columnDefinition = "DATE")
  private LocalDate dataNascimento;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  private Usuario usuario;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "endereco_id")
  private Endereco endereco;

  @ManyToMany
  @JoinTable(name = "voluntarios_tags", joinColumns = @JoinColumn(name = "voluntario_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private final Set<Tag> tags = new HashSet<>();

  @OneToMany(mappedBy = "voluntario")
  private final List<HistoricoAtividade> historicoAtividades = new ArrayList<>();

  public Voluntario() {

  }

  public Voluntario(Integer id, String nomeCompleto, String cpf, String telefone, LocalDate dataNascimento) {
    this.id = id;
    this.nomeCompleto = nomeCompleto;
    this.cpf = cpf;
    this.telefone = telefone;
    this.dataNascimento = dataNascimento;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Endereco getEndereco() {
    return endereco;
  }

  public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
  }

  public Set<Tag> getTags() {
    return tags;
  }

  public void addTag(Tag tag) {
    tags.add(tag);
  }

  public List<HistoricoAtividade> getHistoricoAtividades() {
    return historicoAtividades;
  }

  public void addHistoricoAtividade(HistoricoAtividade historicoAtividade) {
    historicoAtividades.add(historicoAtividade);
  }

}

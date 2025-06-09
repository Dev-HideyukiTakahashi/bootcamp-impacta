package br.com.impacta.boacao.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OngRequestDTO {

    @NotBlank
    private String nomeEntidade;

    @NotBlank
    private String cnpj;

     @NotBlank
    private String telefone;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @NotNull
    private EnderecoRequestDTO endereco;

    public String getNomeEntidade() {
        return nomeEntidade;
    }

    public void setNomeEntidade(String nomeEntidade) {
        this.nomeEntidade = nomeEntidade;
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
    public OngRequestDTO(
            String nomeEntidade,
            String cnpj,
            String email,
            String senha,
            EnderecoRequestDTO endereco) {
        this.nomeEntidade = nomeEntidade;
        this.cnpj = cnpj;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }


}

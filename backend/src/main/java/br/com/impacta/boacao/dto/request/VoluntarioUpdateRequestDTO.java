package br.com.impacta.boacao.dto.request;

public class VoluntarioUpdateRequestDTO {

    private String telefone;

    private EnderecoUpdateRequestDTO endereco;

    private String senha;

    public VoluntarioUpdateRequestDTO() {

    }

    public VoluntarioUpdateRequestDTO(String telefone, EnderecoUpdateRequestDTO endereco, String senha) {
        this.telefone = telefone;
        this.endereco = endereco;
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EnderecoUpdateRequestDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoUpdateRequestDTO endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
package br.com.impacta.boacao.dto.response;

public class OngResponseDTO {

    private int id;
    private String nomeEntidade;

    // Construtor vazio
    public OngResponseDTO() {
    }

    // Construtor com todos os campos
    public OngResponseDTO(int id, String nomeEntidade) {
        this.id = id;
        this.nomeEntidade = nomeEntidade;
    }

    // Construtor apenas com nomeEntidade
    public OngResponseDTO(String nomeEntidade) {
        this.nomeEntidade = nomeEntidade;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEntidade() {
        return nomeEntidade;
    }

    public void setNomeEntidade(String nomeEntidade) {
        this.nomeEntidade = nomeEntidade;
    }
}

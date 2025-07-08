package br.com.impacta.boacao.dto.response;

public class AvaliacaoResponseDTO {

    private Integer id;
    private String feedback;
    private int estrelas;
    private String voluntarioNome;
    private String atividadeNome;

    public AvaliacaoResponseDTO() {
    }

    public AvaliacaoResponseDTO(Integer id, String feedback, int estrelas, String voluntarioNome,
            String atividadeNome) {
        this.id = id;
        this.feedback = feedback;
        this.estrelas = estrelas;
        this.voluntarioNome = voluntarioNome;
        this.atividadeNome = atividadeNome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public String getVoluntarioNome() {
        return voluntarioNome;
    }

    public void setVoluntarioNome(String voluntarioNome) {
        this.voluntarioNome = voluntarioNome;
    }

    public String getAtividadeNome() {
        return atividadeNome;
    }

    public void setAtividadeNome(String atividadeNome) {
        this.atividadeNome = atividadeNome;
    }

}

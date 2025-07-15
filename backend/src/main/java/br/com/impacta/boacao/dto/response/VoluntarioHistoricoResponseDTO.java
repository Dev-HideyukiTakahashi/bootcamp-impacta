package br.com.impacta.boacao.dto.response;

import java.util.List;

public class VoluntarioHistoricoResponseDTO {

    private Integer id;             // continua sendo o voluntário
    private Integer historicoId;    // novo campo
    private String nomeCompleto;
    private String statusCandidatura;
    private String cidade;
    private List<String> tags;

    private Integer avaliacaoId;

    public VoluntarioHistoricoResponseDTO() {
    }

    // construtor ampliado: reparou que agora tem 6 parâmetros?
    public VoluntarioHistoricoResponseDTO(Integer id,
            String nomeCompleto,
            String statusCandidatura,
            String cidade,
            List<String> tags,
            Integer historicoId,
            Integer avaliacaoId) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.statusCandidatura = statusCandidatura;
        this.cidade = cidade;
        this.tags = tags;
        this.historicoId = historicoId;
        this.avaliacaoId = avaliacaoId;
    }

    // getters e setters existentes...
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHistoricoId() {
        return historicoId;
    }

    public void setHistoricoId(Integer historicoId) {
        this.historicoId = historicoId;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getStatusCandidatura() {
        return statusCandidatura;
    }

    public void setStatusCandidatura(String statusCandidatura) {
        this.statusCandidatura = statusCandidatura;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getAvaliacaoId() {
        return avaliacaoId;
    }

    public void setAvaliacaoId(Integer avaliacaoId) {
        this.avaliacaoId = avaliacaoId;
    }

}

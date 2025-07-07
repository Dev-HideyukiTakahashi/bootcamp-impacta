package br.com.impacta.boacao.dto.response;
// Importações necessárias

import java.util.List;
//import br.com.impacta.boacao.entity.enums.StatusCandidatura;

public class VoluntarioHistoricoResponseDTO {

    private Integer id;
    private String nomeCompleto;
    //private StatusCandidatura statusCandidatura;
    private String statusCandidatura; // Alterado para String para compatibilidade com o construtor
    private String cidade;
    private List<String> tags;

    /**
     * DTO (Data Transfer Object) utilizado para representar um voluntário no
     * histórico de atividades. * Este DTO contém as informações básicas de um
     * voluntário, como seu ID, nome completo e status da candidatura.
     *
     * @param id O ID único do voluntário.
     * @param nomeCompleto O nome completo do voluntário.
     * @param statusCandidatura O status da candidatura do voluntário (por
     * exemplo, "Aprovado", "Rejeitado", etc.).
     */
    //construtor completo necessário para o Spring converter o JSON em DTO
    public VoluntarioHistoricoResponseDTO() {
    }

    public VoluntarioHistoricoResponseDTO(Integer id, String nomeCompleto, String statusCandidatura, String cidade, List<String> tags) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.statusCandidatura = statusCandidatura;
        this.cidade = cidade;
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getStatusCandidatura() {
        return statusCandidatura;
    }

    //setters 
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
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


}

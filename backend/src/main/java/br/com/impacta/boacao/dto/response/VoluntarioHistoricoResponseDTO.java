package br.com.impacta.boacao.dto.response;
// Importações necessárias

public class VoluntarioHistoricoResponseDTO {

    private Integer id;
    private String nomeCompleto;
    private String statusCandidatura;

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
    //construtor necessário para o Spring converter o JSON em DTO
    public VoluntarioHistoricoResponseDTO(Integer id, String nomeCompleto, String statusCandidatura) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.statusCandidatura = statusCandidatura;
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

    //construtor completo necessário para o Spring converter o JSON em DTO
    public VoluntarioHistoricoResponseDTO() {
    }
}

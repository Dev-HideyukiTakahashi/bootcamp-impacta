package br.com.impacta.boacao.dto.response;

/**
 * DTO (Data Transfer Object) utilizado para representar um voluntário aprovado em uma atividade.
 * Este DTO contém as informações básicas de um voluntário que foi aprovado,
 * como seu ID e nome.
 */
public class VoluntarioAprovadoResponseDTO {
    private Integer idVoluntario;
    private String nomeVoluntario;

    /**
     * Construtor para criar uma instância de VoluntarioAprovadoResponseDTO.
     * @param idVoluntario O ID único do voluntário.
     * @param nomeVoluntario O nome completo do voluntário.
     */
    public VoluntarioAprovadoResponseDTO(Integer idVoluntario, String nomeVoluntario) {
        this.idVoluntario = idVoluntario;
        this.nomeVoluntario = nomeVoluntario;
    }

    // Getters
    /**
     * Retorna o ID do voluntário.
     * @return O ID do voluntário.
     */
    public Integer getIdVoluntario() {
        return idVoluntario;
    }

    /**
     * Retorna o nome do voluntário.
     * @return O nome do voluntário.
     */
    public String getNomeVoluntario() {
        return nomeVoluntario;
    }

    /**
     * Define o ID do voluntário.
     * @param idVoluntario O novo ID do voluntário.
     */
    public void setIdVoluntario(Integer idVoluntario) {
        this.idVoluntario = idVoluntario;
    }

    /**
     * Define o nome do voluntário.
     * @param nomeVoluntario O novo nome do voluntário.
     */
    public void setNomeVoluntario(String nomeVoluntario) {
        this.nomeVoluntario = nomeVoluntario;
    }
}
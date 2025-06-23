package br.com.impacta.boacao.dto.response;

import java.util.List;

/**
 * DTO (Data Transfer Object) utilizado para representar a resposta do histórico de atividades.
 * <p>
 * Este DTO é utilizado para transferir dados relacionados ao histórico de atividades,
 * contendo a quantidade de voluntários aprovados em uma determinada atividade e a lista
 * desses voluntários.
 * </p>
 * <p>
 * Passo a passo do que este DTO faz:
 * <ul>
 * <li>Armazena a quantidade total de voluntários aprovados em uma atividade específica.</li>
 * <li>Armazena uma lista de objetos {@link VoluntarioAprovadoResponseDTO}, representando os voluntários aprovados.</li>
 * <li>Fornece métodos getters e setters para acessar e modificar os atributos.</li>
 * <li>Sobrescreve os métodos {@code toString}, {@code equals} e {@code hashCode} para facilitar a manipulação e comparação dos objetos.</li>
 * </ul>
 */
public class HistoricoAtividadeResponseDTO {

    private long quantidadeVoluntarios; // Alterado para corresponder a "QuantidadeVoluntarios" no JSON
    private List<VoluntarioAprovadoResponseDTO> voluntarios; // Alterado para corresponder a "Voluntarios" no JSON

    /**
     * Construtor para criar uma instância de HistoricoAtividadeResponseDTO.
     * @param quantidadeVoluntarios A contagem total de voluntários aprovados.
     * @param voluntarios A lista de voluntários aprovados.
     */
    public HistoricoAtividadeResponseDTO(long quantidadeVoluntarios, List<VoluntarioAprovadoResponseDTO> voluntarios) {
        this.quantidadeVoluntarios = quantidadeVoluntarios;
        this.voluntarios = voluntarios;
    }

    // Getters
    /**
     * Retorna a quantidade total de voluntários aprovados.
     * @return A quantidade de voluntários.
     */
    public long getQuantidadeVoluntarios() {
        return quantidadeVoluntarios;
    }

    /**
     * Retorna a lista de voluntários aprovados.
     * @return A lista de VoluntarioAprovadoResponseDTO.
     */
    public List<VoluntarioAprovadoResponseDTO> getVoluntarios() {
        return voluntarios;
    }

    // Setters
    /**
     * Define a quantidade total de voluntários aprovados.
     * @param quantidadeVoluntarios A nova quantidade de voluntários.
     */
    public void setQuantidadeVoluntarios(long quantidadeVoluntarios) {
        this.quantidadeVoluntarios = quantidadeVoluntarios;
    }

    /**
     * Define a lista de voluntários aprovados.
     * @param voluntarios A nova lista de VoluntarioAprovadoResponseDTO.
     */
    public void setVoluntarios(List<VoluntarioAprovadoResponseDTO> voluntarios) {
        this.voluntarios = voluntarios;
    }

    @Override
    public String toString() {
        return "HistoricoAtividadeResponseDTO{" +
               "quantidadeVoluntarios=" + quantidadeVoluntarios +
               ", voluntarios=" + voluntarios +
               '}';
    }

}
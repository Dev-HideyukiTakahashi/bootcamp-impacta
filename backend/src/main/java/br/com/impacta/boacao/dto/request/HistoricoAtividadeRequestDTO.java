package br.com.impacta.boacao.dto.request;

public class HistoricoAtividadeRequestDTO {
    private Integer atividadeId;


    /**
     * Construtor padrão necessário para o Spring converter o JSON em DTO.
     */
    public HistoricoAtividadeRequestDTO() {}
     
    public HistoricoAtividadeRequestDTO(Integer atividadeId) {
        this.atividadeId = atividadeId;
    }
    public Integer getAtividadeId() { return atividadeId; }
    public void setAtividadeId(Integer atividadeId) {
        this.atividadeId = atividadeId;
    }


    //criar getters e setters
    //getters e setters são necessários para o Spring converter o JSON em DTO
    //e para o Lombok gerar os métodos automaticamente  


}
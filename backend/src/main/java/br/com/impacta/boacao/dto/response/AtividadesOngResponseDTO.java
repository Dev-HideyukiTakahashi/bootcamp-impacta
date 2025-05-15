package br.com.impacta.boacao.dto.response;

import br.com.impacta.boacao.entity.enums.StatusAtividade;

import java.time.LocalDateTime;

/**
 * DTO utilizado para respostas (responses) relacionadas a gestão de atividades da ong.
 * contém apenas os dados essenciais que devem ser retornados ao cliente,
 */
public class AtividadesOngResponseDTO {

    private Integer id;
    private String nome;
    private Long voluntarios;
    private String cargaHorariaDiaria;
    private String periodo;
    private LocalDateTime criadoEm;
    private StatusAtividade statusAtvidade;
    private String descricao;


    public AtividadesOngResponseDTO() {
    }
    public AtividadesOngResponseDTO(Integer id, String nome, Long voluntarios, String cargaHorariaDiaria,
                                    String periodo, LocalDateTime criadoEm, StatusAtividade statusAtvidade, String descricao) {
        this.id = id;
        this.nome = nome;
        this.voluntarios = voluntarios;
        this.cargaHorariaDiaria = cargaHorariaDiaria;
        this.periodo = periodo;
        this.criadoEm = criadoEm;
        this.statusAtvidade = statusAtvidade;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getVoluntarios() {
        return voluntarios;
    }

    public String getCargaHorariaDiaria() {
        return cargaHorariaDiaria;
    }

    public String getPeriodo() {
        return periodo;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public StatusAtividade getStatusAtvidade() {
        return statusAtvidade;
    }

    public String getDescricao() {
        return descricao;
    }
}

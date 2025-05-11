package br.com.impacta.boacao.dto.response;

/**
 * DTO utilizado para respostas (responses) relacionadas ao usuário.
 * Aplicado em requisições do tipo GET, este objeto
 * contém apenas os dados essenciais que devem ser retornados ao cliente,
 * como o identificador e o email do usuário.
 */
public class UsuarioResponseDTO {

    private int id;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
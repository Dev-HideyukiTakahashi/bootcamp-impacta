package br.com.impacta.boacao.exception.handler;

public class StatusCandidaturaInvalidoException extends RuntimeException {
    public StatusCandidaturaInvalidoException(String status) {
        super("Status inválido '" + status +
              "'. Valores válidos: PENDENTE, REALIZADO, APROVADO, REJEITADO.");
    }
}
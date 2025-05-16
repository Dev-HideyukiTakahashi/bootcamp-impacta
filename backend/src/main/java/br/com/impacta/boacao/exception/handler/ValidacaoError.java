package br.com.impacta.boacao.exception.handler;

import org.springframework.validation.FieldError;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidacaoError extends RespostaErroHttp{

    public ValidacaoError() {
    }

    public ValidacaoError(Instant timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }
    private final List<MensagemErro> campos = new ArrayList<>();

    public void addError(FieldError error){
        campos.add(new MensagemErro(error.getField(),error.getDefaultMessage()));
    }

    public List<MensagemErro> getCampos() {
        return campos;
    }
}

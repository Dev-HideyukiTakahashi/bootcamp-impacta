package br.com.impacta.boacao.exception.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.impacta.boacao.exception.RecursoNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<RespostaErroHttp> recursoNaoEncontradoHandler(RecursoNaoEncontradoException e,
            HttpServletRequest request) {
        RespostaErroHttp respostaErroHttp = new RespostaErroHttp();
        respostaErroHttp.setTimestamp(Instant.now());
        respostaErroHttp.setStatus(HttpStatus.NOT_FOUND.value());
        respostaErroHttp.setError("Not Found");
        respostaErroHttp.setMessage(e.getMessage());
        respostaErroHttp.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respostaErroHttp);
    }

}

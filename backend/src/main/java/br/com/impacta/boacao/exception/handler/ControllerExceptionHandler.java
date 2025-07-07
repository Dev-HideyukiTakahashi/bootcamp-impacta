package br.com.impacta.boacao.exception.handler;

import java.time.Instant;
import java.util.Arrays;

import br.com.impacta.boacao.exception.DatabaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RespostaErroHttp> illegalArgumentHandler(IllegalArgumentException e, HttpServletRequest request) {
        RespostaErroHttp respostaErroHttp = new RespostaErroHttp();
        respostaErroHttp.setTimestamp(Instant.now());
        respostaErroHttp.setStatus(HttpStatus.BAD_REQUEST.value());
        respostaErroHttp.setError("Bad Request");
        respostaErroHttp.setMessage(e.getMessage());
        respostaErroHttp.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaErroHttp);
    }

    // Erro personalizado para enums
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RespostaErroHttp> httpMessageNotReadableHandler(HttpMessageNotReadableException e, HttpServletRequest request) {

        String mensagemPersonalizada = "Erro de leitura no corpo da requisição.";

        Throwable causa = e.getCause();

        if (causa instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException ex) {
            Class<?> targetType = ex.getTargetType();

            if (targetType.isEnum()) {
                Object[] valoresValidos = targetType.getEnumConstants();
                mensagemPersonalizada += " Valor inválido fornecido: \"" + ex.getValue() + "\". Valores válidos: " + Arrays.toString(valoresValidos) + ".";
            }
        }

        RespostaErroHttp respostaErroHttp = new RespostaErroHttp();
        respostaErroHttp.setTimestamp(Instant.now());
        respostaErroHttp.setStatus(HttpStatus.BAD_REQUEST.value());
        respostaErroHttp.setError("Erro de leitura no corpo da requisição.");
        respostaErroHttp.setMessage(mensagemPersonalizada);
        respostaErroHttp.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaErroHttp);
    }

    // Erro personalizado para integridade referencial no banco de dados
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<RespostaErroHttp> dataIntegrityViolationHandler(DatabaseException e, HttpServletRequest request) {
        RespostaErroHttp respostaErroHttp = new RespostaErroHttp();
        respostaErroHttp.setTimestamp(Instant.now());
        respostaErroHttp.setStatus(HttpStatus.CONFLICT.value());
        respostaErroHttp.setError("Erro no banco de dados");
        respostaErroHttp.setMessage(e.getMessage());
        respostaErroHttp.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(respostaErroHttp);
    }

    // Erro personalizado para validation @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidacaoError> methodArgumentNotValidHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidacaoError validacaoError = new ValidacaoError();
        validacaoError.setTimestamp(Instant.now());
        validacaoError.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        validacaoError.setError("Erro de validação");
        validacaoError.setMessage(e.getDetailMessageCode());
        validacaoError.setPath(request.getRequestURI());

        e.getFieldErrors().forEach(error -> validacaoError.addError(error));

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validacaoError);
    }

    @ExceptionHandler(StatusCandidaturaInvalidoException.class)
    public ResponseEntity<RespostaErroHttp> handleInvalidStatus(
            StatusCandidaturaInvalidoException ex,
            HttpServletRequest req) {

        RespostaErroHttp erro = new RespostaErroHttp();
        erro.setTimestamp(Instant.now());
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        erro.setError("Bad Request");
        erro.setMessage(ex.getMessage());
        erro.setPath(req.getRequestURI());
        return ResponseEntity.badRequest().body(erro);
    }

}

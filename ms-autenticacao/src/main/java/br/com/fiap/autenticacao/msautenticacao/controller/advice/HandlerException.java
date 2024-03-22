package br.com.fiap.autenticacao.msautenticacao.controller.advice;

import br.com.fiap.autenticacao.msautenticacao.exception.UsuarioExistenteException;
import br.com.fiap.autenticacao.msautenticacao.exception.UsuarioNaoEncontradoException;
import br.com.fiap.autenticacao.msautenticacao.response.ErrorResponseDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class HandlerException {

    private static final Logger logger = LoggerFactory.getLogger(HandlerException.class);


    @ExceptionHandler(UsuarioExistenteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> usuarioExistenteException(UsuarioExistenteException ex) {
        logger.error("Erro ao lidar com UsuarioExistenteException", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDetails(ex.getMessage()));
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> usuarioNaoEncontradoException(UsuarioNaoEncontradoException ex) {
        logger.error("Erro ao lidar com UsuarioNaoEncontradoException", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDetails(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.error("Erro ao lidar com MethodArgumentNotValidException", ex);

        List<String> fieldsError = new ArrayList<>();
        ex.getFieldErrors().forEach(f -> fieldsError.add("PARAMETRO: [ " + f.getField() + " ] Mensagem: [ " + f.getDefaultMessage() + " ]"));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDetails(
                "Por favor, verifique se todos os campos foram preenchidos corretamente!",
                HttpStatus.BAD_REQUEST.value(),
                fieldsError,
                new Date().getTime()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> genericException(Exception ex) {
        logger.error("Erro ao lidar com uma exceção genérica", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseDetails("Algo inesperado aconteceu!!"));
    }
}

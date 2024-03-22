package br.com.fiap.msCarrinho.msCarrinho.controller.advice;

import br.com.fiap.msCarrinho.msCarrinho.exception.CarrinhoInvalidoException;
import br.com.fiap.msCarrinho.msCarrinho.exception.CarrinhoJaAbertoException;
import br.com.fiap.msCarrinho.msCarrinho.exception.CarrinhoNotFoundException;
import br.com.fiap.msCarrinho.msCarrinho.exception.ItemNotFoundException;
import br.com.fiap.msCarrinho.msCarrinho.response.ErrorResponseDetails;
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

    @ExceptionHandler(CarrinhoInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> carrinhoInvalidoException(CarrinhoInvalidoException ex) {
        logger.error("Erro ao lidar com carrinhoInvalidoException", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDetails(ex.getMessage()));
    }

    @ExceptionHandler(CarrinhoJaAbertoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> carrinhoJaAbertoException(CarrinhoJaAbertoException ex) {
        logger.error("Erro ao lidar com CarrinhoJaAbertoException", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDetails(ex.getMessage()));
    }

    @ExceptionHandler(CarrinhoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> carrinhoNotFoundException(CarrinhoNotFoundException ex) {
        logger.error("Erro ao lidar com CarrinhoNotFoundException", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDetails(ex.getMessage()));
    }

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> itemNotFoundException(ItemNotFoundException ex) {
        logger.error("Erro ao lidar com ItemNotFoundException", ex);
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

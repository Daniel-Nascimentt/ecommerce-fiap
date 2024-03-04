package br.com.fiap.msusuarios.exception;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(String message) {
        super(message);
    }
}

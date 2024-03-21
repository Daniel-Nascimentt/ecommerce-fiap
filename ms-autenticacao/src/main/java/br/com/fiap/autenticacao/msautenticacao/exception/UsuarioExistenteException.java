package br.com.fiap.autenticacao.msautenticacao.exception;

public class UsuarioExistenteException extends Exception {
    public UsuarioExistenteException() {
        super("Usuario já cadastrado na base de dados!!");
    }
}

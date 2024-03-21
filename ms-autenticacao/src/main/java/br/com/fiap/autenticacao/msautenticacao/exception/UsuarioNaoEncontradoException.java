package br.com.fiap.autenticacao.msautenticacao.exception;

public class UsuarioNaoEncontradoException extends Exception {
    public UsuarioNaoEncontradoException() {
        super("Usuário não encontrado na base de dados!!");
    }
}

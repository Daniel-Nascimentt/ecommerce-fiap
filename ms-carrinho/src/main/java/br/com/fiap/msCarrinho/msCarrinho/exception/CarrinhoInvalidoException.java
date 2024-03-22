package br.com.fiap.msCarrinho.msCarrinho.exception;

public class CarrinhoInvalidoException extends Exception {
    public CarrinhoInvalidoException() {
        super("Esse carrinho é inválido para você!!");
    }
}

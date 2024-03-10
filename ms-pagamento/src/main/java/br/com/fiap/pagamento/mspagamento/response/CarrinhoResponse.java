package br.com.fiap.pagamento.mspagamento.response;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CarrinhoResponse {

    private String codigoCarrinho;
    private BigDecimal valorTotal;

}

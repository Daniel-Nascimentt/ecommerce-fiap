package br.com.fiap.msCarrinho.msCarrinho.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ItemCarrinhoResponse {

    private String idItem;
    private int quantidade;

}



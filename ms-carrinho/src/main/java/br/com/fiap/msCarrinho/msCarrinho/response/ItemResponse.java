package br.com.fiap.msCarrinho.msCarrinho.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ItemResponse {

    private String id;
    private String nome;
    private String descricao;
    private BigDecimal preco;

}



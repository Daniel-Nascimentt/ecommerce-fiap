package br.com.fiap.msGtItens.gtItens.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class ItemUpdateRequest {

    private String nome;
    private String descricao;
    private BigDecimal preco;

}

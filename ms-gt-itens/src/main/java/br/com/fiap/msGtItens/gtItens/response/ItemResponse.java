package br.com.fiap.msGtItens.gtItens.response;

import br.com.fiap.msGtItens.gtItens.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {

    private String id;
    private String nome;
    private String descricao;
    private BigDecimal preco;

    public ItemResponse(Item itemSalvo) {
        this.id = itemSalvo.getId().toString();
        this.nome = itemSalvo.getNome();
        this.descricao = itemSalvo.getDescricao();
        this.preco = itemSalvo.getPreco();
    }
}

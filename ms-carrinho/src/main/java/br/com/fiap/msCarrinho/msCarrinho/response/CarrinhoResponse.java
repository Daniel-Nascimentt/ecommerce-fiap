package br.com.fiap.msCarrinho.msCarrinho.response;

import br.com.fiap.msCarrinho.msCarrinho.domain.Carrinho;
import br.com.fiap.msCarrinho.msCarrinho.domain.Item;
import br.com.fiap.msCarrinho.msCarrinho.domain.StatusCarrinho;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CarrinhoResponse {

    private String codigoCarrinho;
    private StatusCarrinho statusCarrinho;
    private BigDecimal valorTotal;
    private List<ItemCarrinhoResponse> itens = new ArrayList<>();

    public CarrinhoResponse(Carrinho carrinho) {
        this.codigoCarrinho = carrinho.getId().toString();
        this.statusCarrinho = carrinho.getStatus();
        carrinho.getItens().forEach(i -> this.itens.add(new ItemCarrinhoResponse(i.getIdItem().toString(), i.getQuantidade())));
        this.valorTotal = carrinho.getValorTotal();
    }
}

package br.com.fiap.msCarrinho.msCarrinho.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CARRINHO_ITENS")
@Getter
@Setter
@NoArgsConstructor
public class Item {

    @Id
    private String idItem;

    @NotNull
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    private Carrinho carrinho;

    public Item(String idItem, Carrinho carrinho, Integer quantidade) {
        this.idItem = idItem;
        this.carrinho = carrinho;
        this.quantidade = quantidade;
    }

    public void addQuantidade(int quantidade) {
        this.quantidade = this.quantidade + quantidade;
    }

    public void removerQuantidade(int quantidade) {
        this.quantidade = this.quantidade - quantidade;
    }
}

package br.com.fiap.msCarrinho.msCarrinho.domain;

import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Carrinho carrinho;

    public Item(String idItem, Carrinho carrinho) {
        this.idItem = idItem;
        this.carrinho = carrinho;
    }
}

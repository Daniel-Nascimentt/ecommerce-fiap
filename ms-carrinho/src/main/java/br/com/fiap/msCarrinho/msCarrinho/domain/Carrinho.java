package br.com.fiap.msCarrinho.msCarrinho.domain;

import br.com.fiap.msCarrinho.msCarrinho.exception.ItemNotFoundException;
import br.com.fiap.msCarrinho.msCarrinho.request.CarrinhoRequest;
import br.com.fiap.msCarrinho.msCarrinho.request.FecharCarrinhoRequest;
import br.com.fiap.msCarrinho.msCarrinho.response.ItemResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "CARRINHO")
@Getter
@Setter
@NoArgsConstructor
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carrinho", orphanRemoval = true)
    private List<Item> itens = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusCarrinho status;

    @NotNull
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @NotBlank
    private String idUsuario;

    private String codigoPagamento;


    public Carrinho(String idUsuario) {
        this.status = StatusCarrinho.ABERTO;
        this.idUsuario = idUsuario;
    }


    public void addItem(@NotNull int quantidadeASerAdicionada, String idItem, BigDecimal valorItem) {

        Optional<Item> possivelItem = this.itens.stream().filter(it -> it.getIdItem().equals(idItem)).findFirst();

            if (possivelItem.isEmpty()){
                this.itens.add(new Item(idItem, this, quantidadeASerAdicionada));
            }else {
                possivelItem.get().addQuantidade(quantidadeASerAdicionada);

        }

        this.valorTotal = this.valorTotal.add(valorItem.multiply(new BigDecimal(quantidadeASerAdicionada)));
    }

    public void removerItem(ItemResponse itemResponse, int quantidadeASerRemovida) throws ItemNotFoundException {

        Item itemDomain = this.itens.stream().filter(it -> it.getIdItem().equals(itemResponse.getId())).findFirst().orElseThrow(() -> new ItemNotFoundException("Item não encontrado no carrinho!!"));

            if(itemDomain.getQuantidade() < quantidadeASerRemovida){
                this.itens.remove(itemDomain);
                this.valorTotal = this.valorTotal.subtract(itemResponse.getPreco().multiply(new BigDecimal(itemDomain.getQuantidade())));
            }
            else {
                itemDomain.removerQuantidade(quantidadeASerRemovida);
                this.valorTotal = this.valorTotal.subtract(itemResponse.getPreco().multiply(new BigDecimal(quantidadeASerRemovida)));

        }


        this.itens.removeIf(item -> item.getQuantidade() == 0);

    }

    public void finalizarCarrinho(FecharCarrinhoRequest request) {
        this.status = StatusCarrinho.PAGO;
        this.codigoPagamento = request.getCodigoPagamento();
    }


}

package br.com.fiap.msCarrinho.msCarrinho.domain;

import br.com.fiap.msCarrinho.msCarrinho.exception.ItemNotFoundException;
import br.com.fiap.msCarrinho.msCarrinho.request.CarrinhoRequest;
import br.com.fiap.msCarrinho.msCarrinho.request.FecharCarrinhoRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
    private BigDecimal valorTotal;

    @NotBlank
    private String idUsuario;

    private String codigoPagamento;

    public Carrinho(String idItem, String idUsuario, BigDecimal valorItem) {
        this.status = StatusCarrinho.ABERTO;
        this.itens.add(new Item(idItem, this));
        this.idUsuario = idUsuario;
        this.valorTotal = valorItem;
    }

    public void addItem(CarrinhoRequest request) {
        this.itens.add(new Item(request.getIdItem(), this));
        this.valorTotal = this.valorTotal.add(request.getValorItem());
    }

    public void removerItem(CarrinhoRequest request) throws ItemNotFoundException {
        Item item = this.itens.stream().filter(i -> i.getIdItem().equals(request.getIdItem())).findFirst().orElseThrow(() -> new ItemNotFoundException("Item não encontrado!!"));
        this.itens.remove(item);
        this.valorTotal = this.valorTotal.subtract(request.getValorItem());
    }

    public void finalizarCarrinho(FecharCarrinhoRequest request) {
        this.status = StatusCarrinho.PAGO;
        this.codigoPagamento = request.getCodigoPagamento();
    }
}

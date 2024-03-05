package br.com.fiap.msGtItens.gtItens.domain;

import br.com.fiap.msGtItens.gtItens.request.ItemUpdateRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "ITENS")
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    private String nome;
    @NotNull
    private BigDecimal preco;
    @NotBlank
    private String descricao;

    public Item(String nome, String descricao, BigDecimal preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public void atualizar(ItemUpdateRequest request) {
        this.nome = request.getNome() != null ? request.getNome() : this.nome;
        this.descricao = request.getDescricao() != null ? request.getDescricao() : this.descricao;
        this.preco = request.getPreco() != null ? request.getPreco() : this.preco;
    }
}

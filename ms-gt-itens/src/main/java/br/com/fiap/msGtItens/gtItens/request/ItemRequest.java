package br.com.fiap.msGtItens.gtItens.request;

import br.com.fiap.msGtItens.gtItens.domain.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class ItemRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotNull
    private BigDecimal preco;

    public Item toDomain(){
        return new Item(
                this.nome,
                this.descricao,
                this.preco
        );
    }
}

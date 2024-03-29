package br.com.fiap.msCarrinho.msCarrinho.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CarrinhoRequest {

    @NotBlank
    private String idUsuario;
    @NotBlank
    private String idItem;
    @NotNull
    private int quantidade;

}

package br.com.fiap.msCarrinho.msCarrinho.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FecharCarrinhoRequest {

    @NotBlank
    private String idUsuario;
    @NotBlank
    private String codigoPagamento;
}

package br.com.fiap.msGtItens.gtItens.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItenParaCarrinhoRequest {

    @NotBlank
    private String idItem;
    @NotNull
    private Integer quantidade;
    @NotBlank
    private String idUsuario;

}

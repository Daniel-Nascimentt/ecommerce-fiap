package br.com.fiap.pagamento.mspagamento.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FecharCarrinhoRequest {

    @NotBlank
    private String codigoPagamento;
    @NotBlank
    private String idUsuario;

}

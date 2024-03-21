package br.com.fiap.autenticacao.msautenticacao.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {

    @NotBlank
    private String id;

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String cpf;

}

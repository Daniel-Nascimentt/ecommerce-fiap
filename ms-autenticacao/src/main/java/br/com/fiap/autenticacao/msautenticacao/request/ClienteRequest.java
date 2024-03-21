package br.com.fiap.autenticacao.msautenticacao.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    @CPF
    private String cpf;

}

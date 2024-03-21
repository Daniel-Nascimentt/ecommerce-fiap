package br.com.fiap.autenticacao.msautenticacao.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotBlank
    private String email;
    @NotBlank
    private String password;

}

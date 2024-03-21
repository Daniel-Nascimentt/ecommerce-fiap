package br.com.fiap.msusuarios.request;

import br.com.fiap.msusuarios.domain.Cliente;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String cpf;

    public Cliente toDomain() {
        return new Cliente(this.nome, this.email, this.cpf);
    }
}

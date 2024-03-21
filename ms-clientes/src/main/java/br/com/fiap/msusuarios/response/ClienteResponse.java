package br.com.fiap.msusuarios.response;

import br.com.fiap.msusuarios.domain.Cliente;
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

    public ClienteResponse(Cliente clienteSaved) {
        this.id = clienteSaved.getId().toString();
        this.nome = clienteSaved.getNome();
        this.email = clienteSaved.getEmail();
        this.cpf = clienteSaved.getCpf();
    }
}

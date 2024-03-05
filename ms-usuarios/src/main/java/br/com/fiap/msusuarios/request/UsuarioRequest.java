package br.com.fiap.msusuarios.request;

import br.com.fiap.msusuarios.domain.Usuario;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    public Usuario toDomain() {
        return new Usuario(this.nome, this.email, this.senha);
    }
}

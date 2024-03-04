package br.com.fiap.msusuarios.response;

import br.com.fiap.msusuarios.domain.Usuario;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    @NotBlank
    private String id;

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    public UsuarioResponse(Usuario usuarioSaved) {
        this.id = usuarioSaved.getId().toString();
        this.nome = usuarioSaved.getNome();
        this.email = usuarioSaved.getEmail();
    }
}

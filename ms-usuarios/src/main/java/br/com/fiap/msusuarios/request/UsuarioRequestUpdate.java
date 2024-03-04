package br.com.fiap.msusuarios.request;

import br.com.fiap.msusuarios.domain.Usuario;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestUpdate {

    private String nome;
    private String email;
    private String senha;

}

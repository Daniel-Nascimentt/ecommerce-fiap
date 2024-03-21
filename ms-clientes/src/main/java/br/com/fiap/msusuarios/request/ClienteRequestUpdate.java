package br.com.fiap.msusuarios.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestUpdate {

    private String nome;
    private String email;
    private String cpf;

}

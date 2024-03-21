package br.com.fiap.msusuarios.domain;

import br.com.fiap.msusuarios.request.ClienteRequestUpdate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "CLIENTES")
@Getter
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String cpf;

    public Cliente(String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public void update(ClienteRequestUpdate request) {
        this.nome = request.getNome() != null ? request.getNome() : this.nome;
        this.email = request.getEmail() != null ? request.getEmail() : this.email;
        this.cpf = request.getCpf() != null ? request.getCpf() : this.cpf;
    }
}

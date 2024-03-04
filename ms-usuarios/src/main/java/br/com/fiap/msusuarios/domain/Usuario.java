package br.com.fiap.msusuarios.domain;

import br.com.fiap.msusuarios.request.UsuarioRequestUpdate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "USUARIOS")
@Getter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void update(UsuarioRequestUpdate request) {
        this.nome = request.getNome() != null ? request.getNome() : this.nome;
        this.email = request.getEmail() != null ? request.getEmail() : this.email;
        this.senha = request.getSenha() != null ? request.getSenha() : this.senha;
    }
}

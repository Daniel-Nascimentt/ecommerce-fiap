package br.com.fiap.autenticacao.msautenticacao.repository;

import br.com.fiap.autenticacao.msautenticacao.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRpository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);
}

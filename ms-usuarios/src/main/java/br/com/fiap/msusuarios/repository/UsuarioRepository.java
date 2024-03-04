package br.com.fiap.msusuarios.repository;

import br.com.fiap.msusuarios.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}

package br.com.fiap.msusuarios.repository;

import br.com.fiap.msusuarios.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}

package br.com.fiap.msGtItens.gtItens.repository;

import br.com.fiap.msGtItens.gtItens.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
}

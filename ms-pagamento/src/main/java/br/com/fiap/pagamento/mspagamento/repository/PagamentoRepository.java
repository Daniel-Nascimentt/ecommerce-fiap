package br.com.fiap.pagamento.mspagamento.repository;

import br.com.fiap.pagamento.mspagamento.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {
    Optional<Pagamento> findByIdUsuario(String id);
}

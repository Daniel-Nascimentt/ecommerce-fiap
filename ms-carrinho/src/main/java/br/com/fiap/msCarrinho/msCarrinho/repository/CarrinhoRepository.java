package br.com.fiap.msCarrinho.msCarrinho.repository;

import br.com.fiap.msCarrinho.msCarrinho.domain.Carrinho;
import br.com.fiap.msCarrinho.msCarrinho.domain.StatusCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CarrinhoRepository extends JpaRepository<Carrinho, UUID> {

    Optional<Carrinho> findByIdUsuarioAndStatus(String idUsuario, StatusCarrinho status);

    default Optional<Carrinho> findByIdUsuarioAndStatusIsAberto(String idUsuario){
        return findByIdUsuarioAndStatus(idUsuario, StatusCarrinho.ABERTO);
    }

}

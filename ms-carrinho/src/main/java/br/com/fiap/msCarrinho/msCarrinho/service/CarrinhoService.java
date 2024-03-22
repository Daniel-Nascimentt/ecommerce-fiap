package br.com.fiap.msCarrinho.msCarrinho.service;

import br.com.fiap.msCarrinho.msCarrinho.client.ItemClient;
import br.com.fiap.msCarrinho.msCarrinho.client.MsClientesClient;
import br.com.fiap.msCarrinho.msCarrinho.domain.Carrinho;
import br.com.fiap.msCarrinho.msCarrinho.exception.*;
import br.com.fiap.msCarrinho.msCarrinho.repository.CarrinhoRepository;
import br.com.fiap.msCarrinho.msCarrinho.request.CarrinhoRequest;
import br.com.fiap.msCarrinho.msCarrinho.request.FecharCarrinhoRequest;
import br.com.fiap.msCarrinho.msCarrinho.response.CarrinhoResponse;
import br.com.fiap.msCarrinho.msCarrinho.response.ClienteResponse;
import br.com.fiap.msCarrinho.msCarrinho.response.ItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ItemClient itemClient;

    @Autowired
    private MsClientesClient msClientesClient;


    public CarrinhoResponse abrirCarrinho(String token, String idUsuario) throws CarrinhoJaAbertoException {

        ClienteResponse cliente = msClientesClient.buscarCliente(token, idUsuario);

        if(cliente == null) {
            throw new ClienteNotFoundException("Cliente não encontrado!!");
        }

        Optional<Carrinho> possivelCarrinho = carrinhoRepository.findByIdUsuarioAndStatusIsAberto(idUsuario);

        if(possivelCarrinho.isPresent()){
            throw new CarrinhoJaAbertoException("Seu carrinho já está aberto!!");
        }

        Carrinho carrinho = new Carrinho(idUsuario);
        return new CarrinhoResponse(carrinhoRepository.save(carrinho));
    }

    public CarrinhoResponse visualizarCarrinho(String idUsuario, String token) throws CarrinhoNotFoundException, CarrinhoInvalidoException {

        Carrinho carrinho = buscarCarrinho(token, idUsuario);

        return new CarrinhoResponse(carrinho);
    }

    public CarrinhoResponse addItemCarrinho(CarrinhoRequest request, String token) throws CarrinhoNotFoundException, CarrinhoInvalidoException {

        Carrinho carrinho = buscarCarrinho(token, request.getIdUsuario());

        ItemResponse itemResponse = itemClient.buscarItemPorId(request.getIdItem());
        carrinho.addItem(request.getQuantidade(), itemResponse.getId(), itemResponse.getPreco());

        return new CarrinhoResponse(carrinhoRepository.save(carrinho));
    }

    public CarrinhoResponse removerItemCarrinho(CarrinhoRequest request, String token) throws CarrinhoNotFoundException, ItemNotFoundException, CarrinhoInvalidoException {

        Carrinho carrinho = buscarCarrinho(token, request.getIdUsuario());

        ItemResponse item = itemClient.buscarItemPorId(request.getIdItem());
        carrinho.removerItem(item, request.getQuantidade());

        return new CarrinhoResponse(carrinhoRepository.save(carrinho));
    }


    public void fecharCarrinho(FecharCarrinhoRequest request, String token) throws CarrinhoNotFoundException, CarrinhoInvalidoException {

        Carrinho carrinho = buscarCarrinho(token, request.getIdUsuario());

        carrinho.finalizarCarrinho(request);

        carrinhoRepository.save(carrinho);
    }

    private Carrinho buscarCarrinho(String token, String idUsuario) throws CarrinhoNotFoundException, CarrinhoInvalidoException {

        Carrinho carrinho = carrinhoRepository
                .findByIdUsuarioAndStatusIsAberto(idUsuario)
                .orElseThrow(() -> new CarrinhoNotFoundException("Carrinho não encontrado!!"));

        validarTitularidadeCarrinho(token, idUsuario, carrinho);

        return carrinho;
    }

    private void validarTitularidadeCarrinho(String token, String idUsuario, Carrinho carrinho) throws CarrinhoInvalidoException {
        ClienteResponse cliente = msClientesClient.buscarCliente(token, idUsuario);

        if (cliente != null && (!cliente.getId().equals(carrinho.getIdUsuario()))) {
            throw new CarrinhoInvalidoException();
        }
    }

}

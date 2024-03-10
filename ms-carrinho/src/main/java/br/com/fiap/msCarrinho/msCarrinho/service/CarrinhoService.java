package br.com.fiap.msCarrinho.msCarrinho.service;

import br.com.fiap.msCarrinho.msCarrinho.client.ItemClient;
import br.com.fiap.msCarrinho.msCarrinho.domain.Carrinho;
import br.com.fiap.msCarrinho.msCarrinho.domain.Item;
import br.com.fiap.msCarrinho.msCarrinho.exception.CarrinhoJaAbertoException;
import br.com.fiap.msCarrinho.msCarrinho.exception.CarrinhoNotFoundException;
import br.com.fiap.msCarrinho.msCarrinho.exception.ItemNotFoundException;
import br.com.fiap.msCarrinho.msCarrinho.repository.CarrinhoRepository;
import br.com.fiap.msCarrinho.msCarrinho.request.CarrinhoRequest;
import br.com.fiap.msCarrinho.msCarrinho.request.FecharCarrinhoRequest;
import br.com.fiap.msCarrinho.msCarrinho.response.CarrinhoResponse;
import br.com.fiap.msCarrinho.msCarrinho.response.ItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private ItemClient itemClient;


    public CarrinhoResponse abrirCarrinho(String idUsuario) throws CarrinhoJaAbertoException {

        Optional<Carrinho> possivelCarrinho = carrinhoRepository.findByIdUsuarioAndStatusIsAberto(idUsuario);

        if(possivelCarrinho.isPresent()){
            throw new CarrinhoJaAbertoException("Seu carrinho já está aberto!!");
        }

        Carrinho carrinho = new Carrinho(idUsuario);
        return new CarrinhoResponse(carrinhoRepository.save(carrinho));
    }

    public CarrinhoResponse visualizarCarrinho(String idUsuario) throws CarrinhoNotFoundException {
        Carrinho carrinho = carrinhoRepository
                .findByIdUsuarioAndStatusIsAberto(idUsuario)
                .orElseThrow(() -> new CarrinhoNotFoundException("Carrinho não encontrado!!"));

        return new CarrinhoResponse(carrinho);
    }

    public CarrinhoResponse addItemCarrinho(CarrinhoRequest request) throws CarrinhoNotFoundException {

        ItemResponse itemResponse = itemClient.buscarItemPorId(request.getIdItem());

        Carrinho carrinho = carrinhoRepository
                .findByIdUsuarioAndStatusIsAberto(request.getIdUsuario())
                .orElseThrow(() -> new CarrinhoNotFoundException("Carrinho não encontrado!!"));


        carrinho.addItem(request.getQuantidade(), itemResponse.getId(), itemResponse.getPreco());

        return new CarrinhoResponse(carrinhoRepository.save(carrinho));
    }

    public CarrinhoResponse removerItemCarrinho(CarrinhoRequest request) throws CarrinhoNotFoundException, ItemNotFoundException {

        ItemResponse item = itemClient.buscarItemPorId(request.getIdItem());

        Carrinho carrinho = carrinhoRepository
                .findByIdUsuarioAndStatusIsAberto(request.getIdUsuario())
                .orElseThrow(() -> new CarrinhoNotFoundException("Carrinho não encontrado!!"));

        carrinho.removerItem(item, request.getQuantidade());

        return new CarrinhoResponse(carrinhoRepository.save(carrinho));
    }
    

    public void fecharCarrinho(FecharCarrinhoRequest request) throws CarrinhoNotFoundException {
        Carrinho carrinho = carrinhoRepository
                .findByIdUsuarioAndStatusIsAberto(request.getIdUsuario())
                .orElseThrow(() -> new CarrinhoNotFoundException("Carrinho não encontrado!!"));

        carrinho.finalizarCarrinho(request);

        carrinhoRepository.save(carrinho);
    }


}

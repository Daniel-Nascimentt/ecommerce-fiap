package br.com.fiap.msCarrinho.msCarrinho.service;

import br.com.fiap.msCarrinho.msCarrinho.domain.Carrinho;
import br.com.fiap.msCarrinho.msCarrinho.exception.CarrinhoNotFoundException;
import br.com.fiap.msCarrinho.msCarrinho.exception.ItemNotFoundException;
import br.com.fiap.msCarrinho.msCarrinho.repository.CarrinhoRepository;
import br.com.fiap.msCarrinho.msCarrinho.request.CarrinhoRequest;
import br.com.fiap.msCarrinho.msCarrinho.request.FecharCarrinhoRequest;
import br.com.fiap.msCarrinho.msCarrinho.response.CarrinhoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    public CarrinhoResponse visualizarCarrinho(String idUsuario) throws CarrinhoNotFoundException {
        Carrinho carrinho = carrinhoRepository
                .findByIdUsuarioAndStatusIsAberto(idUsuario)
                .orElseThrow(() -> new CarrinhoNotFoundException("Carrinho não encontrado!!"));

        return new CarrinhoResponse(carrinho);
    }

    public CarrinhoResponse addItemCarrinho(CarrinhoRequest request){

        Optional<Carrinho> possivelCarrinho = carrinhoRepository.findByIdUsuarioAndStatusIsAberto(request.getIdUsuario());

        Carrinho carrinho = null;

        if(possivelCarrinho.isEmpty()){
            carrinho = new Carrinho(request.getIdItem(), request.getIdUsuario(), request.getValorItem());
        }else {
            carrinho = possivelCarrinho.get();
            carrinho.addItem(request);
        }

        Carrinho carrinhoAtualizado = carrinhoRepository.save(carrinho);

        return new CarrinhoResponse(carrinhoAtualizado);
    }

    public CarrinhoResponse removerItemCarrinho(CarrinhoRequest request) throws CarrinhoNotFoundException, ItemNotFoundException {

        Carrinho carrinho = carrinhoRepository
                .findByIdUsuarioAndStatusIsAberto(request.getIdUsuario())
                .orElseThrow(() -> new CarrinhoNotFoundException("Carrinho não encontrado!!"));

        carrinho.removerItem(request);

        Carrinho carrinhoAtualizado = carrinhoRepository.save(carrinho);

        return new CarrinhoResponse(carrinhoAtualizado);
    }

    public void fecharCarrinho(FecharCarrinhoRequest request) throws CarrinhoNotFoundException {
        Carrinho carrinho = carrinhoRepository
                .findByIdUsuarioAndStatusIsAberto(request.getIdUsuario())
                .orElseThrow(() -> new CarrinhoNotFoundException("Carrinho não encontrado!!"));

        carrinho.finalizarCarrinho(request);

        carrinhoRepository.save(carrinho);
    }


}

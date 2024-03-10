package br.com.fiap.msCarrinho.msCarrinho.controller;

import br.com.fiap.msCarrinho.msCarrinho.exception.CarrinhoJaAbertoException;
import br.com.fiap.msCarrinho.msCarrinho.exception.CarrinhoNotFoundException;
import br.com.fiap.msCarrinho.msCarrinho.exception.ItemNotFoundException;
import br.com.fiap.msCarrinho.msCarrinho.request.CarrinhoRequest;
import br.com.fiap.msCarrinho.msCarrinho.request.FecharCarrinhoRequest;
import br.com.fiap.msCarrinho.msCarrinho.response.CarrinhoResponse;
import br.com.fiap.msCarrinho.msCarrinho.service.CarrinhoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<CarrinhoResponse> visualizarCarrinho(@PathVariable String idUsuario) throws CarrinhoNotFoundException {
        return ResponseEntity.ok(carrinhoService.visualizarCarrinho(idUsuario));
    }

    @PostMapping("/abrirCarrinho/{idUsuario}")
    public ResponseEntity<CarrinhoResponse> abrirCarrinho(@PathVariable String idUsuario) throws CarrinhoJaAbertoException {
        return ResponseEntity.ok(carrinhoService.abrirCarrinho(idUsuario));
    }

    @PostMapping("/addItemCarrinho")
    public ResponseEntity<CarrinhoResponse> addItemCarrinho(@Valid @RequestBody CarrinhoRequest request) throws CarrinhoNotFoundException {
        return ResponseEntity.ok(carrinhoService.addItemCarrinho(request));
    }

    @PostMapping("/removerItemCarrinho")
    public ResponseEntity<CarrinhoResponse> removerItemCarrinho(@Valid @RequestBody CarrinhoRequest request) throws CarrinhoNotFoundException, ItemNotFoundException {

        return ResponseEntity.ok(carrinhoService.removerItemCarrinho(request));
    }

    @PostMapping("/fechar")
    public ResponseEntity<?> fecharCarrinho(@Valid @RequestBody FecharCarrinhoRequest request) throws CarrinhoNotFoundException {
        carrinhoService.fecharCarrinho(request);
        return ResponseEntity.ok().build();
    }

}

package br.com.fiap.msCarrinho.msCarrinho.controller;

import br.com.fiap.msCarrinho.msCarrinho.exception.CarrinhoInvalidoException;
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
    public ResponseEntity<CarrinhoResponse> visualizarCarrinho(@RequestHeader("Authorization") String token, @PathVariable String idUsuario) throws CarrinhoNotFoundException, CarrinhoInvalidoException {
        return ResponseEntity.ok(carrinhoService.visualizarCarrinho(idUsuario, token));
    }

    @PostMapping("/abrirCarrinho/{idUsuario}")
    public ResponseEntity<CarrinhoResponse> abrirCarrinho(@RequestHeader("Authorization") String token, @PathVariable String idUsuario) throws CarrinhoJaAbertoException {
        return ResponseEntity.ok(carrinhoService.abrirCarrinho(token, idUsuario));
    }

    @PostMapping("/addItemCarrinho")
    public ResponseEntity<CarrinhoResponse> addItemCarrinho(@RequestHeader("Authorization") String token, @Valid @RequestBody CarrinhoRequest request) throws CarrinhoNotFoundException, CarrinhoInvalidoException {
        return ResponseEntity.ok(carrinhoService.addItemCarrinho(request, token));
    }

    @PostMapping("/removerItemCarrinho")
    public ResponseEntity<CarrinhoResponse> removerItemCarrinho(@RequestHeader("Authorization") String token, @Valid @RequestBody CarrinhoRequest request) throws CarrinhoNotFoundException, ItemNotFoundException, CarrinhoInvalidoException {

        return ResponseEntity.ok(carrinhoService.removerItemCarrinho(request, token));
    }

    @PostMapping("/fechar")
    public ResponseEntity<?> fecharCarrinho(@RequestHeader("Authorization") String token, @Valid @RequestBody FecharCarrinhoRequest request) throws CarrinhoNotFoundException, CarrinhoInvalidoException {
        carrinhoService.fecharCarrinho(request, token);
        return ResponseEntity.ok().build();
    }

}

package br.com.fiap.pagamento.mspagamento.client;

import br.com.fiap.pagamento.mspagamento.request.FecharCarrinhoRequest;
import br.com.fiap.pagamento.mspagamento.response.CarrinhoResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "Carrinho", url = "localhost:8083/ms-carrinho/carrinho")
public interface CarrinhoClient {


    @GetMapping(value = "/{idUsuario}")
    CarrinhoResponse obterCarrinho(@PathVariable String idUsuario);


    @PostMapping("/fechar")
    void fecharCarrinho(@RequestBody @Valid FecharCarrinhoRequest request);
}

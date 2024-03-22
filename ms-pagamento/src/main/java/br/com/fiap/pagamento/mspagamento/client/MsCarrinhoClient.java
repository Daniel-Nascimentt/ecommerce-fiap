package br.com.fiap.pagamento.mspagamento.client;

import br.com.fiap.pagamento.mspagamento.request.FecharCarrinhoRequest;
import br.com.fiap.pagamento.mspagamento.response.CarrinhoResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "Carrinho", url = "${ms.carrinho.url}")
public interface MsCarrinhoClient {


    @GetMapping(value = "/{idUsuario}")
    CarrinhoResponse obterCarrinho(@RequestHeader("Authorization") String token, @PathVariable String idUsuario);


    @PostMapping("/fechar")
    void fecharCarrinho(@RequestHeader("Authorization") String token, @RequestBody @Valid FecharCarrinhoRequest request);
}

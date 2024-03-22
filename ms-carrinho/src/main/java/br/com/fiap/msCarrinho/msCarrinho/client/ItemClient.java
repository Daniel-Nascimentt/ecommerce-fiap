package br.com.fiap.msCarrinho.msCarrinho.client;

import br.com.fiap.msCarrinho.msCarrinho.response.ItemResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msItem", url = "${ms.itens.url}")
public interface ItemClient {

    @GetMapping("/{id}")
    ItemResponse buscarItemPorId(@PathVariable String id);

}

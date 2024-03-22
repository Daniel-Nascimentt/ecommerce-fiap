package br.com.fiap.pagamento.mspagamento.client;

import br.com.fiap.pagamento.mspagamento.response.ClienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "MsClientes", url = "${ms.clientes.url}")
public interface MsClientesClient {

    @GetMapping(value = "/{id}")
    ClienteResponse buscarCliente(@RequestHeader("Authorization") String token, @PathVariable String id);

}

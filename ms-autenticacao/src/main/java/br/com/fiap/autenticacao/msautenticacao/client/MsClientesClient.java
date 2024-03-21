package br.com.fiap.autenticacao.msautenticacao.client;

import br.com.fiap.autenticacao.msautenticacao.request.ClienteRequest;
import br.com.fiap.autenticacao.msautenticacao.response.ClienteResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MsClientes", url = "${ms.clientes.url}")
public interface MsClientesClient {

    @PostMapping
    ClienteResponse criarCliente(@Valid @RequestBody ClienteRequest request);

}

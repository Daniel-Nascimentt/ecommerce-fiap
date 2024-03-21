package br.com.fiap.msusuarios.controller;

import br.com.fiap.msusuarios.request.ClienteRequest;
import br.com.fiap.msusuarios.request.ClienteRequestUpdate;
import br.com.fiap.msusuarios.response.ClienteResponse;
import br.com.fiap.msusuarios.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponse> criarCliente(@Valid @RequestBody ClienteRequest request){
        return ResponseEntity.ok(clienteService.criarCliente(request));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteResponse> atualizarCliente(@Valid @RequestBody ClienteRequestUpdate request, @PathVariable String id){
        return ResponseEntity.ok(clienteService.atualizarCliente(request, id));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable String id){
        return ResponseEntity.ok(clienteService.buscarPorId(id, ""));
    }

}

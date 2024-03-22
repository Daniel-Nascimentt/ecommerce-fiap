package br.com.fiap.msusuarios.service;

import br.com.fiap.msusuarios.domain.Cliente;
import br.com.fiap.msusuarios.exception.ClienteNotFoundException;
import br.com.fiap.msusuarios.repository.ClienteRepository;
import br.com.fiap.msusuarios.request.ClienteRequest;
import br.com.fiap.msusuarios.request.ClienteRequestUpdate;
import br.com.fiap.msusuarios.response.ClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteResponse criarCliente(ClienteRequest request) {
        Cliente clienteSalvo = clienteRepository.save(request.toDomain());
        return new ClienteResponse(clienteSalvo);
    }

    public ClienteResponse atualizarCliente(ClienteRequestUpdate request, String id) {
        Cliente clienteEncontrado = clienteRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado!!"));

        clienteEncontrado.update(request);
        clienteRepository.save(clienteEncontrado);

        return new ClienteResponse(clienteEncontrado);

    }

    public ClienteResponse buscarPorId(String id) {
        Cliente clienteEncontrado = clienteRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado!!"));
        return new ClienteResponse(clienteEncontrado);
    }

    public void deletarPorId(String id) {
        Cliente clienteEncontrado = clienteRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado!!"));
        clienteRepository.delete(clienteEncontrado);
    }
}

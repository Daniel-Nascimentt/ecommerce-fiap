package br.com.fiap.msusuarios.service;

import br.com.fiap.msusuarios.domain.Usuario;
import br.com.fiap.msusuarios.exception.UsuarioNotFoundException;
import br.com.fiap.msusuarios.repository.UsuarioRepository;
import br.com.fiap.msusuarios.request.UsuarioRequest;
import br.com.fiap.msusuarios.request.UsuarioRequestUpdate;
import br.com.fiap.msusuarios.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponse criarUsuario(UsuarioRequest request) {
        Usuario usuarioSalvo = usuarioRepository.save(request.toModel());
        return new UsuarioResponse(usuarioSalvo);
    }

    public UsuarioResponse atualizarUsuario(UsuarioRequestUpdate request, String id) {
        Usuario usuarioEncontrado = usuarioRepository.findById(UUID.fromString(id)).orElseThrow(() -> new UsuarioNotFoundException("Usuario não encontrado!!"));

        usuarioEncontrado.update(request);
        usuarioRepository.save(usuarioEncontrado);

        return new UsuarioResponse(usuarioEncontrado);

    }

    public UsuarioResponse BuscarPorId(String id) {
        Usuario usuarioEncontrado = usuarioRepository.findById(UUID.fromString(id)).orElseThrow(() -> new UsuarioNotFoundException("Usuario não encontrado!!"));
        return new UsuarioResponse(usuarioEncontrado);
    }
}

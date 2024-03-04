package br.com.fiap.msusuarios.controller;

import br.com.fiap.msusuarios.request.UsuarioRequest;
import br.com.fiap.msusuarios.request.UsuarioRequestUpdate;
import br.com.fiap.msusuarios.response.UsuarioResponse;
import br.com.fiap.msusuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@Valid @RequestBody UsuarioRequest request){
        return ResponseEntity.ok(usuarioService.criarUsuario(request));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@Valid @RequestBody UsuarioRequestUpdate request, @PathVariable String id){
        return ResponseEntity.ok(usuarioService.atualizarUsuario(request, id));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable String id){
        return ResponseEntity.ok(usuarioService.BuscarPorId(id));
    }

}

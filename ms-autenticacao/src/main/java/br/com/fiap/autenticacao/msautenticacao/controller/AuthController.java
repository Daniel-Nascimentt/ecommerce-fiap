package br.com.fiap.autenticacao.msautenticacao.controller;

import br.com.fiap.autenticacao.msautenticacao.exception.UsuarioExistenteException;
import br.com.fiap.autenticacao.msautenticacao.exception.UsuarioNaoEncontradoException;
import br.com.fiap.autenticacao.msautenticacao.request.AuthRequest;
import br.com.fiap.autenticacao.msautenticacao.request.RegisterRequest;
import br.com.fiap.autenticacao.msautenticacao.response.AuthResponse;
import br.com.fiap.autenticacao.msautenticacao.response.ClienteResponse;
import br.com.fiap.autenticacao.msautenticacao.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) throws UsuarioNaoEncontradoException {
        return ResponseEntity.ok(usuarioService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<ClienteResponse> register(@RequestBody @Valid RegisterRequest request) throws UsuarioExistenteException {
        return ResponseEntity.ok(usuarioService.registerNewUser(request));
    }

}

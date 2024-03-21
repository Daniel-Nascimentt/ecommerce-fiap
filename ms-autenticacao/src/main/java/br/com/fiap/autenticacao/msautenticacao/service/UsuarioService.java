package br.com.fiap.autenticacao.msautenticacao.service;

import br.com.fiap.autenticacao.msautenticacao.client.MsClientesClient;
import br.com.fiap.autenticacao.msautenticacao.entity.Usuario;
import br.com.fiap.autenticacao.msautenticacao.exception.UsuarioExistenteException;
import br.com.fiap.autenticacao.msautenticacao.exception.UsuarioNaoEncontradoException;
import br.com.fiap.autenticacao.msautenticacao.repository.UsuarioRpository;
import br.com.fiap.autenticacao.msautenticacao.request.AuthRequest;
import br.com.fiap.autenticacao.msautenticacao.request.ClienteRequest;
import br.com.fiap.autenticacao.msautenticacao.request.RegisterRequest;
import br.com.fiap.autenticacao.msautenticacao.response.AuthResponse;
import br.com.fiap.autenticacao.msautenticacao.response.ClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRpository usuarioRpository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MsClientesClient msClientesClient;

    public AuthResponse login(AuthRequest request) throws UsuarioNaoEncontradoException {

        UserDetails possivelUsuario = usuarioRpository.findByEmail(request.getEmail());
        if(possivelUsuario == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);
        return tokenService.generateToken((Usuario) auth.getPrincipal());
    }

    public ClienteResponse registerNewUser(RegisterRequest request) throws UsuarioExistenteException {
        if(usuarioRpository.findByEmail(request.getEmail()) != null) throw new UsuarioExistenteException();

        ClienteResponse clienteResponse = msClientesClient.criarCliente(new ClienteRequest(request.getNome(), request.getEmail(), request.getCpf()));

        String encryptedPassword = new BCryptPasswordEncoder().encode(request.getPassword());
        Usuario newUser = new Usuario(UUID.fromString(clienteResponse.getId()), request.getEmail(), encryptedPassword);

        usuarioRpository.save(newUser);

        return clienteResponse;
    }

}

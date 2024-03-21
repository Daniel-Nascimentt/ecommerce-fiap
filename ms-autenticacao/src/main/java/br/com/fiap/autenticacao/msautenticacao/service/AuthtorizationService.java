package br.com.fiap.autenticacao.msautenticacao.service;

import br.com.fiap.autenticacao.msautenticacao.repository.UsuarioRpository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthtorizationService implements UserDetailsService {

    @Autowired
    UsuarioRpository usuarioRpository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRpository.findByEmail(email);
    }
}

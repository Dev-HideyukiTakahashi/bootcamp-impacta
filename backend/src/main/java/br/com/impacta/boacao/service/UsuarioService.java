package br.com.impacta.boacao.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import br.com.impacta.boacao.entity.Usuario;
import br.com.impacta.boacao.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(username);

        if (usuario != null)
            return usuario;
        else
            throw new UsernameNotFoundException("Username not found");
    }

    /*
     * metodo que retorna o usuario ja autenticado
     * ao fazer a request, busca o username(email) por dados do jwt
     */
    protected Usuario getUsuarioAutenticado() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");

            return usuarioRepository.findByEmail(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Email não encontrado");
        }
    }
}

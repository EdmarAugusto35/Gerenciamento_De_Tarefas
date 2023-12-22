package com.example.backend.Model.Service;

import com.example.backend.Model.entidade.Usuario;
import com.example.backend.Model.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null){
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        return User.withUsername(username)
                .password(usuario.getPassword())
                .roles("USER")
                .build();
    }

    public Usuario salvarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario encontrarPorUsername(String username){
        return usuarioRepository.findByUsername(username);
    }
}

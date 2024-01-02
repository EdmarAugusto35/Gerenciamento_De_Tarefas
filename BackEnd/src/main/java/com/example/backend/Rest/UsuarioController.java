package com.example.backend.Rest;

import com.example.backend.Config.JWT.JwtTokenProvider;
import com.example.backend.Model.Service.UsuarioService;
import com.example.backend.Model.entidade.Usuario;
import com.example.backend.Model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UsuarioController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/home")
    public String home() {
        return "Bem-vindo à página inicial!";
    }

    // Este endpoint estará protegido por autenticação
    @GetMapping("/secured")
    public String secured() {
        return "Esta é uma página protegida. Somente usuários autenticados têm acesso.";
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity salvarLogin(@RequestBody Map<String, String> data){
        try {
            String username = data.get("username");
            String password = data.get("password");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (username != null){
               throw  new UsernameNotFoundException("Username " + username + " not found");
            }
            String token = jwtTokenProvider.createToken(username, this.usuarioRepository.findByUsername(username).getRoles());
            Map<Object , Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ResponseEntity.ok(model);
        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid usename/password supplied");
        }
    }
}

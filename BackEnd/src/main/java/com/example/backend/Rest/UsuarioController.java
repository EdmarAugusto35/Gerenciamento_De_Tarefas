package com.example.backend.Rest;

import com.example.backend.Model.Service.UsuarioService;
import com.example.backend.Model.entidade.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvarLogin(@RequestBody @Valid Usuario usuario){
        return usuarioService.salvarUsuario(usuario);
    }
}

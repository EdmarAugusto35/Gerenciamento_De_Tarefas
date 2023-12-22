package com.example.backend.Rest.util;

import com.example.backend.Model.Service.UsuarioService;
import com.example.backend.Model.entidade.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(){
        return "registro";
    }

    @PostMapping("/registro")
    public String processarFormularioRegistro(Usuario usuario){
        usuarioService.salvarUsuario(usuario);
        return "redirect:/login";
    }
}

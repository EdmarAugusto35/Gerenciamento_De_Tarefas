package com.example.backend.Model.repository;

import com.example.backend.Model.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}

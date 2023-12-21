package com.example.backend.Model.repository;

import com.example.backend.Model.entidade.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}

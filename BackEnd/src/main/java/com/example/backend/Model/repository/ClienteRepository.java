package com.example.backend.Model.repository;

import com.example.backend.Model.entidade.Cliente;
import com.example.backend.Model.entidade.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(" select  tarefa from Tarefa tarefa join tarefa.cliente cliente where  UPPER(cliente.nome) like  UPPER(:nome) and month (tarefa.data) = :mes")
    List<Tarefa> findByNomeClienteAndMes(
            @Param("nome") String nome, @Param("mes") Integer mes);
}

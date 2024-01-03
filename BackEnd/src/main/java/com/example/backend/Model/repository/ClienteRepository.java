package com.example.backend.Model.repository;

import com.example.backend.Model.entidade.Cliente;
import com.example.backend.Model.entidade.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(" select servico from Servico servico join servico.cliente cliente where  UPPER(cliente.nome) like  UPPER(:nome) and month (servico.data) = :mes")
    List<Servico> findByNomeClienteAndMes(
            @Param("nome") String nome, @Param("mes") Integer mes);
}

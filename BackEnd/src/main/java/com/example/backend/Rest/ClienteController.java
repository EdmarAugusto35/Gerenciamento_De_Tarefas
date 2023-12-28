package com.example.backend.Rest;

import com.example.backend.Model.entidade.Cliente;
import com.example.backend.Model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/Clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    //Salvando Cliente no banco de Dados
    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody @Valid Cliente cliente) {
        cliente =  clienteRepository.save(cliente);
        return new ResponseEntity<>(cliente,HttpStatus.CREATED);
    }

    // Buscando todos os clientes Cadastrados
    @GetMapping
    public List<Cliente> obterTodos() {
        return clienteRepository.findAll();
    }

    /**
     * Consultando Clientes por id
     * * @param id
     */
    @GetMapping("{id}")
    public Cliente consultarPorId(@PathVariable Long id) {
        return clienteRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    /**
     * Deletando Clientes por id
     * * @param id
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        clienteRepository
                .findById(id)
                .map(cliente -> {
                    clienteRepository.delete(cliente);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    /**
     * Atualizando Clientes por id
     * * @param id
     */
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        clienteRepository
                .findById(id)
                .map(cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setCpf(clienteAtualizado.getCpf());
                    cliente.setEmail(clienteAtualizado.getEmail());
                    return clienteRepository.save(cliente);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }
}

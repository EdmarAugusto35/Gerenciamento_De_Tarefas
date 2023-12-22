package com.example.backend.Rest;

import com.example.backend.Model.entidade.Cliente;
import com.example.backend.Model.entidade.Tarefa;
import com.example.backend.Model.repository.ClienteRepository;
import com.example.backend.Model.repository.TarefaRepository;
import com.example.backend.Rest.dto.ServicoTarefaDTO;
import com.example.backend.Rest.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final ClienteRepository clienteRepository;
    private final TarefaRepository tarefaRepository;
    private final BigDecimalConverter bigDecimalConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarefa salvar(@RequestBody @Valid ServicoTarefaDTO servicoTarefaDTO){
        LocalDate date = LocalDate.parse(servicoTarefaDTO.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Long idCliente = servicoTarefaDTO.getIdCliente();

        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException( HttpStatus.BAD_REQUEST, "Cliente inexistente." ));

        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(servicoTarefaDTO.getDescricao());
        tarefa.setNome(servicoTarefaDTO.getNome());
        tarefa.setData(date);
        tarefa.setCliente(cliente);
        tarefa.setValor(bigDecimalConverter.converter(servicoTarefaDTO.getPreco()));

        return tarefaRepository.save(tarefa);
    }

    @GetMapping
    public List<Tarefa> pesquisarTarefa(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "mes", required = false) Integer mes){
        return clienteRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
    }
}

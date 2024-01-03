package com.example.backend.Rest;

import com.example.backend.Model.entidade.Cliente;
import com.example.backend.Model.entidade.Servico;
import com.example.backend.Model.repository.ClienteRepository;
import com.example.backend.Model.repository.ServicoRepository;
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
public class ServicoController {

    private final ClienteRepository clienteRepository;
    private final ServicoRepository servicoRepository;
    private final BigDecimalConverter bigDecimalConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Servico salvar(@RequestBody @Valid ServicoTarefaDTO servicoTarefaDTO){
        LocalDate date = LocalDate.parse(servicoTarefaDTO.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Long idCliente = servicoTarefaDTO.getIdCliente();

        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException( HttpStatus.BAD_REQUEST, "Cliente inexistente." ));

        Servico servico = new Servico();
        servico.setDescricao(servicoTarefaDTO.getDescricao());
        servico.setNome(servicoTarefaDTO.getNome());
        servico.setData(date);
        servico.setCliente(cliente);
        servico.setValor(bigDecimalConverter.converter(servicoTarefaDTO.getPreco()));

        return servicoRepository.save(servico);
    }

    @GetMapping
    public List<Servico> pesquisarTarefa(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "mes", required = false) Integer mes){
        return clienteRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
    }
}

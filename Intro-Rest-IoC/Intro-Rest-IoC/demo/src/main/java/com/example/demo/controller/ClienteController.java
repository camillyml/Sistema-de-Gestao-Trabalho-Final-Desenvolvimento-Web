package com.example.demo.controller;

import com.example.demo.dto.ClienteEnderecoDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Produto;
import com.example.demo.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    // Injeção de Dependência: O Spring fornecerá um objeto service pronto ao Controller
    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@Valid @RequestBody ClienteEnderecoDTO dto, UriComponentsBuilder uriBuilder) {

        Cliente clienteSalvo = service.registrarClienteEEndereco(dto);

        // Cria a URI: http://servidor/clientes/{id}
        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(clienteSalvo.getId()).toUri();

        // Retorna o cabeçalho location na resposta HTTP com a URI do novo Cliente criado.
        // Também retorna o objeto criado no corpo da resposta HTTP.
        return ResponseEntity.created(uri).body(clienteSalvo);
    }

    @GetMapping
    public List<Cliente> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        Cliente cliente = service.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Cliente> buscarPorCpf(@PathVariable String cpf) {
        Cliente cliente = service.buscarPorCpf(cpf);
        return ResponseEntity.ok(cliente);
    }
}
package com.example.demo.controller;

import com.example.demo.dto.VendaRequestDTO;
import com.example.demo.entity.Venda;
import com.example.demo.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService service;

    public VendaController(VendaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Venda> criar(@Valid @RequestBody VendaRequestDTO dto, UriComponentsBuilder uriBuilder) {
        Venda vendaSalva = service.criarVenda(dto);
        URI uri = uriBuilder.path("/vendas/{id}").buildAndExpand(vendaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(vendaSalva);
    }

    @GetMapping
    public List<Venda> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable Long id) {
        Venda venda = service.buscarPorId(id);
        return ResponseEntity.ok(venda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
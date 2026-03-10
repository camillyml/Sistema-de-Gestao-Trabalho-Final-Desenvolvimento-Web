package com.example.demo.controller;

import com.example.demo.entity.Produto;
import com.example.demo.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    // Injeção de Dependência: O Spring fornecerá um objeto service pronto ao Controller
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto, UriComponentsBuilder uriBuilder) {
        Produto produtoSalvo = service.salvar(produto);

        // Cria a URI: http://servidor/produtos/{id}
        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produtoSalvo.getId()).toUri();

        // Retorna o cabeçalho location na resposta HTTP com a URI do novo produto criado.
        // Também retorna o objeto criado no corpo da resposta HTTP.
        return ResponseEntity.created(uri).body(produtoSalvo);
    }

    @GetMapping
    public List<Produto> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Produto produto = service.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        Produto produtoAtualizado = service.atualizar(id, produto);
        return ResponseEntity.ok(produtoAtualizado); // Retorna o código de status 200 com o objeto atualizado.
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build(); // Retorna o status 204 No Content.
    }

    @GetMapping("/nome/{nome}")
    public List<Produto> buscarPorNome(@PathVariable String nome) {
        return service.buscarPorNome(nome);
    }

    @GetMapping("/preco/{precoLimite}")
    public List<Produto> buscarPorPrecoLimite(@PathVariable Double precoLimite) {
        return service.buscarPorPrecoLimite(precoLimite);
    }
}
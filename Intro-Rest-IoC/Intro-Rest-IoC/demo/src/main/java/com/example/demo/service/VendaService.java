package com.example.demo.service;

import com.example.demo.dto.VendaRequestDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.ItemVenda;
import com.example.demo.entity.Produto;
import com.example.demo.entity.Venda;
import com.example.demo.repository.VendaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public VendaService(VendaRepository vendaRepository, ClienteService clienteService, ProdutoService produtoService) {
        this.vendaRepository = vendaRepository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    @Transactional
    public Venda criarVenda(VendaRequestDTO dto) {
        Cliente cliente = clienteService.buscarPorId(dto.clienteId());
        Venda venda = new Venda(cliente);

        for (VendaRequestDTO.ItemVendaDTO itemDTO : dto.itens()) {
            Produto produto = produtoService.buscarPorId(itemDTO.produtoId());

            ItemVenda item = new ItemVenda(
                    produto,
                    itemDTO.quantidade(),
                    produto.getPreco(),
                    itemDTO.descontoPercentual() != null ? itemDTO.descontoPercentual() : 0.0
            );
            venda.adicionarItem(item);
        }

        return vendaRepository.save(venda);
    }

    public List<Venda> listarTodas() {
        return vendaRepository.findAll();
    }

    public Venda buscarPorId(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda não encontrada"));
    }

    @Transactional
    public void excluir(Long id) {
        if (!vendaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda não encontrada");
        }
        vendaRepository.deleteById(id);
    }
}
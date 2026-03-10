package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double precoUnitario; // Preço do produto no momento da venda

    private Double descontoPercentual; // Ex: 10.0 para 10% de desconto

    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = false)
    @JsonIgnore // Evita recursão infinita na serialização
    private Venda venda;

    // Construtor padrão (obrigatório para JPA)
    public ItemVenda() {}

    // Construtor
    public ItemVenda(Produto produto, Integer quantidade, Double precoUnitario, Double descontoPercentual) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.descontoPercentual = descontoPercentual;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public Double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(Double precoUnitario) { this.precoUnitario = precoUnitario; }
    public Double getDescontoPercentual() { return descontoPercentual; }
    public void setDescontoPercentual(Double descontoPercentual) { this.descontoPercentual = descontoPercentual; }
    public Venda getVenda() { return venda; }
    public void setVenda(Venda venda) { this.venda = venda; }
}

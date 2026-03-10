package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Define que esta classe é uma entidade e corresponderá a uma tabela no banco de dados (ORM)
public class Produto {

    @Id // Indica que é um campo de identificação única (será chave primária na respectiva tabela do banco)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // A respectiva coluna no MySQL será AUTO_INCREMENT
    private Long id;

    private String nome;
    private Double preco;

    // Construtor padrão (obrigatório para JPA)
    public Produto() {}

    // Construtor
    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
}
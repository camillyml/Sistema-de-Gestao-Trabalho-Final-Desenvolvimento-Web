package com.example.demo.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EnderecoCliente {

    @Id // Indica que é um campo de identificação única (será chave primária na respectiva tabela do banco)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // A respectiva coluna no MySQL será AUTO_INCREMENT
    private Long id;

    private String endereco;

    @ManyToOne // Muitos endereço podem estar vinculados a um único cliente
    @JoinColumn // Instrui o Hibernate a criar uma chave estrangeira na respectiva tabela para conexão com o cliente
    @JsonIgnore // Evita recursão infinita na serialização JSON
    private Cliente cliente; // Cliente ao qual o endereço pertence

    // Construtor padrão (obrigatório para JPA)
    public EnderecoCliente() {}

    // Construtor
    public EnderecoCliente(String endereco) {
        this.endereco = endereco;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}

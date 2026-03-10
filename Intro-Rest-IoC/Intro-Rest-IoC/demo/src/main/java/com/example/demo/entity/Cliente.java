package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // Define que esta classe é uma entidade e corresponderá a uma tabela no banco de dados (ORM)
public class Cliente {

    @Id // Indica que é um campo de identificação única (será chave primária na respectiva tabela do banco)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // A respectiva coluna no MySQL será AUTO_INCREMENT
    private Long id;

    private String nome;
    private String cpf;

    // Um cliente pode ter vários endereços
    // -> mappedBy="cliente" diz ao Hibernate que o relacionamento já é mantido pela chave estrangeira
    // na tabela EnderecoCliente (campo 'cliente' da entidade EnderecoCliente). Sem essa instrução,
    // uma terceira tabela de conexão seria criada, o que não é necessário.
    // -> cascade=CascadeType.ALL diz ao Hibernate para salvar os respectivos endereços automaticamente
    // quando o objeto Cliente for salvo.
    // -> orphanRemoval=true: ao remover um endereço do cliente em memória e salvar no banco, está configuração
    // diz ao Hibernate para que o respectivo endereço na tabela de endereços também seja excluído.
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnderecoCliente> enderecos = new ArrayList<>();

    // Construtor padrão (obrigatório para JPA)
    public Cliente() {}

    // Construtor
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    // Adiciona um novo endereço na lista de endereços do cliente.
    public void adicionarEndereco(EnderecoCliente endereco) {
        enderecos.add(endereco);
        endereco.setCliente(this);
    }

    // Getters e Setters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public List<EnderecoCliente> getEnderecos() { return enderecos; }
    public void setEnderecos(List<EnderecoCliente> enderecos) { this.enderecos = enderecos; }
}

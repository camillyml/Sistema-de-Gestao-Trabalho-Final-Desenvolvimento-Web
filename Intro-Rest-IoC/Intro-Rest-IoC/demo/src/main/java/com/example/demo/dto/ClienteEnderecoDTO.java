package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

// Neste exemplo introdutório, essas mensagens de erro não serão automaticamente retornadas na resposta HTTP,
// pois não definimos uma classe de tratamento de exceção global (@RestControllerAdvice). Será retornado apenas
// o status 400 Bad Request em caso de campo em branco.
// Um record é mais apropriado aqui por ser imutável por padrão, trazer simplificação de acesso, etc.
public record ClienteEnderecoDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O CPF é obrigatório")
        String cpf,

        @NotBlank(message = "O endereço é obrigatório")
        String endereco
) {}
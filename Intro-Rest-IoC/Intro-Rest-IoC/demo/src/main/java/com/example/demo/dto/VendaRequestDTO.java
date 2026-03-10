package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

public record VendaRequestDTO(

        @NotNull(message = "O ID do cliente é obrigatório")
        Long clienteId,

        @NotNull(message = "A lista de itens é obrigatória")
        List<ItemVendaDTO> itens
) {
    public record ItemVendaDTO(
            @NotNull(message = "O ID do produto é obrigatório")
            Long produtoId,

            @NotNull(message = "A quantidade é obrigatória")
            @Positive(message = "A quantidade deve ser maior que zero")
            Integer quantidade,

            Double descontoPercentual
    ) {}
}

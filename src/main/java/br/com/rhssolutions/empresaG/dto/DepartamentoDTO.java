package br.com.rhssolutions.empresaG.dto;

import jakarta.validation.constraints.NotBlank;

public record DepartamentoDTO(
        Long id,
        @NotBlank(message = "O nome do departamento é obrigatório") String nome,
        String descricao,
        Long empresaId
) {
}

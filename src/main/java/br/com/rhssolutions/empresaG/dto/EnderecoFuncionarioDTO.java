package br.com.rhssolutions.empresaG.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoFuncionarioDTO(
        @NotBlank String rua,
        Integer numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,

        @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato 00000-000")
        String cep,
        String pais
) {
}

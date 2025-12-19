package br.com.rhssolutions.empresaG.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record EmpresaDTO(
        Long id,
        @NotBlank(message = "O nome da empresa é obrigatório") String nome,
        @NotBlank(message = "O CNPJ é obrigatório")
        @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", message = "CNPJ inválido")
        @Size(max = 18)
        String cnpj,
        EnderecoEmpresaDTO endereco,
        Set<Long> departamentosId,
        Set<Long> funcionariosId
) {
}



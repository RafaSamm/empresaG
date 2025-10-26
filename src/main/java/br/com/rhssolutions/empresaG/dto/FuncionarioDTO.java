package br.com.rhssolutions.empresaG.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record FuncionarioDTO(
        Long id,

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
        String cpf,

        @Past(message = "A data de nascimento deve ser anterior à data atual")
        LocalDate dataNascimento,

        @Email(message = "E-mail inválido")
        String email,

        @Pattern(regexp = "\\d{10,11}", message = "Telefone inválido")
        String telefone,

        @NotNull(message = "O salário é obrigatório")
        Double salario,

        EnderecoFuncionarioDTO endereco,

        Long empresaId
) {
}

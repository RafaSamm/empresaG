package br.com.rhssolutions.empresaG.dto;

public record ViaCepResponse(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf,
        Boolean erro
) {
}

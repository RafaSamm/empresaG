package br.com.rhssolutions.empresaG.common;

import br.com.rhssolutions.empresaG.dto.ViaCepResponse;

public class ViaCepResponseConstant {

    public static ViaCepResponse enderecoValido() {

        return new ViaCepResponse("30140-070",
                "Rua dos Amoirés",
                "Funcionários",
                "Belo Horizonte",
                "MG",
                true
        );

    }

    public static ViaCepResponse enderecoInvalido() {
        return new ViaCepResponse(null,
                null,
                null,
                null,
                null,
                false);
    }
}

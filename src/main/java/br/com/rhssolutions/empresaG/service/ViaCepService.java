package br.com.rhssolutions.empresaG.service;

import br.com.rhssolutions.empresaG.client.ViaCepClient;
import br.com.rhssolutions.empresaG.exception.CepNotFoundException;
import br.com.rhssolutions.empresaG.dto.ViaCepResponse;
import org.springframework.stereotype.Service;

@Service
public class ViaCepService {

    private final ViaCepClient viaCepClient;

    public ViaCepService(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    public ViaCepResponse buscarCep(String cep) {

        if (!cep.matches("\\d{8}")) {
            throw new CepNotFoundException("CEP inválido");
        }

        ViaCepResponse response = viaCepClient.buscarEndereco(cep);

        if (response == null || Boolean.TRUE.equals(response.erro())) {
            throw new CepNotFoundException("CEP não encontrado");
        }
        return response;
    }


}

package br.com.rhssolutions.empresaG.service;

import br.com.rhssolutions.empresaG.domain.model.empresa.EnderecoEmpresa;
import br.com.rhssolutions.empresaG.domain.model.funcionario.EnderecoFuncionario;
import br.com.rhssolutions.empresaG.dto.EnderecoEmpresaDTO;
import br.com.rhssolutions.empresaG.dto.EnderecoFuncionarioDTO;
import br.com.rhssolutions.empresaG.dto.ViaCepResponse;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServiceClient {

    private final ViaCepService viaCepService;

    public EnderecoServiceClient(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }

    public EnderecoEmpresa montarEnderecoEmpresa(EnderecoEmpresaDTO dto) { //Endereço da empresa com o client ViaCep

        ViaCepResponse viacep = viaCepService.buscarCep(dto.cep());

        EnderecoEmpresa enderecoEmpresa = new EnderecoEmpresa();
        enderecoEmpresa.setCep(dto.cep());
        enderecoEmpresa.setNumero(dto.numero());
        enderecoEmpresa.setRua(viacep.logradouro());
        enderecoEmpresa.setBairro(viacep.bairro());
        enderecoEmpresa.setCidade(viacep.localidade());
        enderecoEmpresa.setEstado(viacep.uf());
        enderecoEmpresa.setPais("Brasil");

        return enderecoEmpresa;
    }

    public EnderecoFuncionario montarEnderecoFuncionario(EnderecoFuncionarioDTO dto) { //Endereço do funcionário com o client ViaCep

        ViaCepResponse viacep = viaCepService.buscarCep(dto.cep());

        EnderecoFuncionario enderecoFuncionario = new EnderecoFuncionario();
        enderecoFuncionario.setCep(dto.cep());
        enderecoFuncionario.setNumero(dto.numero());
        enderecoFuncionario.setComplemento(dto.complemento());
        enderecoFuncionario.setRua(viacep.logradouro());
        enderecoFuncionario.setBairro(viacep.bairro());
        enderecoFuncionario.setCidade(viacep.localidade());
        enderecoFuncionario.setEstado(viacep.uf());
        enderecoFuncionario.setPais("Brasil");

        return enderecoFuncionario;
    }
}

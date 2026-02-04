package br.com.rhssolutions.empresaG.common;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import br.com.rhssolutions.empresaG.domain.model.empresa.EnderecoEmpresa;
import br.com.rhssolutions.empresaG.dto.EnderecoEmpresaDTO;

public class EmpresaConstant {

    public static final String CNPJ_VALIDO = "12.345.678/0001-99";
    public static final String CNPJ_INVALIDO = "";
    public static final String CEP_VALIDO = "20459-009";

    public static Empresa criarEmpresa() {
        var empresa = new Empresa();
        empresa.setNome("Empresa Teste");
        empresa.setCnpj(CNPJ_VALIDO);
        return empresa;
    }

    public static EnderecoEmpresaDTO criarEnderecoEmpresaDTO() {
        return new EnderecoEmpresaDTO(
                "Rua Carlo de Falco",
                123,
                "Bairro Teste",
                "São Paulo",
                "SP",
                CEP_VALIDO,
                "Brasil"
        );
    }

    public static EnderecoEmpresa criarEnderecoEmpresaCompleto() {
        EnderecoEmpresa endereco = new EnderecoEmpresa();
        endereco.setRua("Rua Carlo de Falco");
        endereco.setNumero(123);
        endereco.setBairro("Bairro Teste");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");
        endereco.setCep(CEP_VALIDO);
        endereco.setPais("Brasil");
        return endereco;
    }


}

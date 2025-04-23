package br.com.rhssolutions.empresaG.common;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import br.com.rhssolutions.empresaG.domain.model.empresa.EnderecoEmpresa;

public class EmpresaConstant {

    public static final String CNPJ_VALIDO = "12.345.678/0001-99";
    public static final String CNPJ_INVALIDO = "";
    public static final String CEP_VALIDO = "20459-009";

    public static Empresa criarEmpresa() {
        var empresa = new Empresa();
        empresa.setNome("Empresa Teste");
        empresa.setCnpj(CNPJ_VALIDO);
        var endereco = new EnderecoEmpresa();
        endereco.setRua("Rua Teste");
        endereco.setNumero(123);
        endereco.setBairro("Bairro Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setEstado("Estado Teste");
        endereco.setCep(CEP_VALIDO);
        endereco.setPais("pais");
        empresa.setEndereco(endereco);
        return empresa;
    }

    public static Empresa criarEmpresaInvalido() {
        var empresa = new Empresa();
        empresa.setNome("");
        empresa.setCnpj("");
        var endereco = new EnderecoEmpresa();
        endereco.setRua("");
        endereco.setNumero(0);
        endereco.setBairro("");
        endereco.setCidade("");
        endereco.setEstado("");
        endereco.setCep(CEP_VALIDO);
        endereco.setPais("");
        empresa.setEndereco(endereco);
        return empresa;
    }

}

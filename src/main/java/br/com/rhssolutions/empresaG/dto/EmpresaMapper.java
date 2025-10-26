package br.com.rhssolutions.empresaG.dto;

import br.com.rhssolutions.empresaG.domain.model.departamento.Departamento;
import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import br.com.rhssolutions.empresaG.domain.model.empresa.EnderecoEmpresa;
import br.com.rhssolutions.empresaG.domain.model.funcionario.Funcionario;

import java.util.stream.Collectors;

public class EmpresaMapper {
    public static EmpresaDTO empresaToDTO(Empresa empresa) { // transformar entidade para DTO
        if (empresa == null) return null;

        return new EmpresaDTO(
                empresa.getId(),
                empresa.getNome(),
                empresa.getCnpj(),
                enderecoToDTO(empresa.getEndereco()),
                empresa.getDepartamentos().stream().map(Departamento::getId).collect(Collectors.toSet()),
                empresa.getFuncionarios().stream().map(Funcionario::getId).collect(Collectors.toSet())
        );

    }

    public static EnderecoEmpresaDTO enderecoToDTO(EnderecoEmpresa enderecoEmpresa) {
        if (enderecoEmpresa == null) return null;

        return new EnderecoEmpresaDTO(
                enderecoEmpresa.getRua(),
                enderecoEmpresa.getNumero(),
                enderecoEmpresa.getBairro(),
                enderecoEmpresa.getCidade(),
                enderecoEmpresa.getEstado(),
                enderecoEmpresa.getCep(),
                enderecoEmpresa.getPais()
        );
    }


}

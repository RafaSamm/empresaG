package br.com.rhssolutions.empresaG.dto.mapper;

import br.com.rhssolutions.empresaG.domain.model.departamento.Departamento;
import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import br.com.rhssolutions.empresaG.domain.model.empresa.EnderecoEmpresa;
import br.com.rhssolutions.empresaG.domain.model.funcionario.Funcionario;
import br.com.rhssolutions.empresaG.dto.EmpresaDTO;
import br.com.rhssolutions.empresaG.dto.EnderecoEmpresaDTO;

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

    public static Empresa dtoToEmpresa(EmpresaDTO empresaDTO) { // transformar DTO para entidade
        if (empresaDTO == null) return null;

        Empresa empresa = new Empresa();
        empresa.setId(empresaDTO.id());
        empresa.setNome(empresaDTO.nome());
        empresa.setCnpj(empresaDTO.cnpj());
        empresa.setEndereco(dtoToEndereco(empresaDTO.endereco()));
        return empresa;
    }

    public static EnderecoEmpresa dtoToEndereco(EnderecoEmpresaDTO enderecoEmpresaDTO) {
        if (enderecoEmpresaDTO == null) return null;

        EnderecoEmpresa enderecoEmpresa = new EnderecoEmpresa();
        enderecoEmpresa.setRua(enderecoEmpresaDTO.rua());
        enderecoEmpresa.setNumero(enderecoEmpresaDTO.numero());
        enderecoEmpresa.setBairro(enderecoEmpresaDTO.bairro());
        enderecoEmpresa.setCidade(enderecoEmpresaDTO.cidade());
        enderecoEmpresa.setEstado(enderecoEmpresaDTO.estado());
        enderecoEmpresa.setCep(enderecoEmpresaDTO.cep());
        enderecoEmpresa.setPais(enderecoEmpresaDTO.pais());

        return enderecoEmpresa;
    }

}

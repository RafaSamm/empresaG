package br.com.rhssolutions.empresaG.dto.mapper;

import br.com.rhssolutions.empresaG.domain.model.departamento.Departamento;
import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import br.com.rhssolutions.empresaG.dto.DepartamentoDTO;

public class DepartamentoMapper {

    public static DepartamentoDTO departamentoToDTO(Departamento departamento) {
        if (departamento == null) return null;

        return new DepartamentoDTO(
                departamento.getId(),
                departamento.getNome(),
                departamento.getDescricao(),
                departamento.getEmpresa() != null ? departamento.getEmpresa().getId() : null

        );
    }

    public static Departamento dtoToDepartamento(DepartamentoDTO departamentoDTO, Empresa empresa) {
        if (departamentoDTO == null) return null;

        Departamento departamento = new Departamento();
        departamento.setId(departamentoDTO.id());
        departamento.setNome(departamentoDTO.nome());
        departamento.setDescricao(departamentoDTO.descricao());
        departamento.setEmpresa(empresa);

        return departamento;
    }
}

package br.com.rhssolutions.empresaG.service;

import br.com.rhssolutions.empresaG.domain.model.Departamento;

public interface DepartamentoService {
    //Departamento criarDepartamento(Departamento departamento);

    Departamento buscarDepartamento(Long id);

    Departamento atualizarDepartamento(Long id, Departamento departamento);

    Departamento deletarDepartamento(Long id);

    Departamento adicionarDepartamentoNaEmpresa(Long empresaId, Departamento departamento);
}

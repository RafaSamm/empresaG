package br.com.rhssolutions.empresaG.service;

import br.com.rhssolutions.empresaG.domain.model.departamento.Departamento;

public interface DepartamentoService {
    Departamento criarDepartamento(Long empresaId, Departamento departamento);

    Departamento buscarDepartamentoPorId(Long id);

    Departamento atualizarDepartamento(Long id, Departamento departamento);

    Departamento deletarDepartamento(Long id);


}

package br.com.rhssolutions.empresaG.service;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;

public interface EmpresaService {
    Empresa criarEmpresa(Empresa empresa);

    Empresa buscarEmpresaPorId(Long id);

    Empresa deletarEmpresaPorId(Long id);

    Iterable<Empresa> buscarTodasEmpresas();

}

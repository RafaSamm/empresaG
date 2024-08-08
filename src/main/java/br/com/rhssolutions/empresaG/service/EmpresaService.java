package br.com.rhssolutions.empresaG.service;

import br.com.rhssolutions.empresaG.domain.model.Empresa;

public interface EmpresaService {
    Empresa criarEmpresa(Empresa empresa);

    Empresa buscarEmpresaPorId(Long id);

    Empresa deletarEmpresaPorId(Long id);

}

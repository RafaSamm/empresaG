package br.com.rhssolutions.empresaG.service;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;

import java.util.Optional;

public interface EmpresaService {
    Empresa criarEmpresa(Empresa empresa);

    Optional<Empresa> buscarEmpresaPorId(Long id);

    void deletarEmpresaPorId(Long id);

    Iterable<Empresa> buscarTodasEmpresas();

}

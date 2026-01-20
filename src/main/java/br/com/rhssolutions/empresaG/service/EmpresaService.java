package br.com.rhssolutions.empresaG.service;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import br.com.rhssolutions.empresaG.dto.EnderecoEmpresaDTO;

public interface EmpresaService {
    Empresa criarEmpresa(Empresa empresa);

    Empresa buscarEmpresaPorId(Long id);

    void deletarEmpresaPorId(Long id);

    Iterable<Empresa> buscarTodasEmpresas();

}

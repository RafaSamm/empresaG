package br.com.rhssolutions.empresaG.service;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import br.com.rhssolutions.empresaG.dto.EnderecoEmpresaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmpresaService {
    Empresa criarEmpresa(Empresa empresa, EnderecoEmpresaDTO enderecoEmpresaDTO);

    Empresa buscarEmpresaPorId(Long id);

    void deletarEmpresaPorId(Long id);

    List<Empresa> buscarTodasEmpresasAdmin();

    Page<Empresa> buscarTodasEmpresas(Pageable pageable);

}

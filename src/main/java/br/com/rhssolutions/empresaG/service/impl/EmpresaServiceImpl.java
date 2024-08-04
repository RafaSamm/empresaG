package br.com.rhssolutions.empresaG.service.impl;

import br.com.rhssolutions.empresaG.domain.model.Departamento;
import br.com.rhssolutions.empresaG.domain.model.Empresa;
import br.com.rhssolutions.empresaG.domain.repository.EmpresaRepository;
import br.com.rhssolutions.empresaG.service.EmpresaService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }


    @Override
    public Empresa criarEmpresa(Empresa empresa, Departamento departamento) {
        var empresaExistente = empresaRepository.existsByCnpj((empresa.getCnpj()));
        if (empresaExistente) {
            throw new IllegalArgumentException("Empresa já existe com este CNPJ");
        } else {
            empresa.getDepartamentos().add(departamento);
            return empresaRepository.save(empresa);
        }
    }

    @Override
    public Empresa buscarEmpresaPorId(Long id) {
        return empresaRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Empresa não encontrada"));
    }

    @Override
    public Empresa deletarEmpresaPorId(Long id) {
        if (empresaRepository.existsById(id)) {
            empresaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Empresa não encontrada");
        }
        return null;
    }

}

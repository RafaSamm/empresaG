package br.com.rhssolutions.empresaG.service.impl;

import br.com.rhssolutions.empresaG.domain.model.Empresa;
import br.com.rhssolutions.empresaG.domain.repository.EmpresaRepository;
import br.com.rhssolutions.empresaG.service.EmpresaService;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;


    public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }


    @Override
    public Empresa criarEmpresa(Empresa empresa) {
        var empresaExistente = empresaRepository.existsByCnpj((empresa.getCnpj()));
        if (empresaExistente) {
            throw new IllegalArgumentException("Empresa já existe com este CNPJ");
        } else {
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

package br.com.rhssolutions.empresaG.service.impl;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import br.com.rhssolutions.empresaG.domain.repository.EmpresaRepository;
import br.com.rhssolutions.empresaG.exception.EmpresaNotFoundException;
import br.com.rhssolutions.empresaG.service.EmpresaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;


    public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }


    @Override
    @Transactional
    public Empresa criarEmpresa(Empresa empresa) {
        if (empresaRepository.existsByCnpj((empresa.getCnpj()))) {
            throw new IllegalArgumentException("Empresa já existe com este CNPJ");
        }
        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa buscarEmpresaPorId(Long id) {
        return empresaRepository.findById(id).orElseThrow(() ->
                new EmpresaNotFoundException("Empresa não encontrada"));
    }

    @Override
    @Transactional
    public void deletarEmpresaPorId(Long id) {
        empresaRepository.delete(empresaRepository.findById(id).orElseThrow(() ->
                new EmpresaNotFoundException("Empresa não encontrada")));
    }

    @Override
    public Iterable<Empresa> buscarTodasEmpresas() {
        List<Empresa> empresas = empresaRepository.findAll();
        if (empresas.isEmpty()) {
            throw new EmpresaNotFoundException("Não há empresas cadastradas");
        }
        return empresas;
    }

}

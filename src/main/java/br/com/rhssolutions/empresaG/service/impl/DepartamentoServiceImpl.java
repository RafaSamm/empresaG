package br.com.rhssolutions.empresaG.service.impl;

import br.com.rhssolutions.empresaG.domain.model.Departamento;
import br.com.rhssolutions.empresaG.domain.model.Empresa;
import br.com.rhssolutions.empresaG.domain.repository.DepartamentoRepository;
import br.com.rhssolutions.empresaG.domain.repository.EmpresaRepository;
import br.com.rhssolutions.empresaG.service.DepartamentoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final EmpresaRepository empresaRepository;

    public DepartamentoServiceImpl(DepartamentoRepository departamentoRepository, EmpresaRepository empresaRepository) {
        this.departamentoRepository = departamentoRepository;
        this.empresaRepository = empresaRepository;
    }


//    @Override
//    @Transactional
//    public Departamento criarDepartamento(Departamento departamento) {
//        var departamentoExistente = departamentoRepository.existsByNome(departamento.getNome());
//        if (departamentoExistente) {
//            throw new IllegalArgumentException("Departamento já existe");
//        } else {
//            return departamentoRepository.save(departamento);
//        }
//    }

    @Override
    public Departamento buscarDepartamento(Long id) {
        return departamentoRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Departamento não encontrado"));

    }

    @Override
    @Transactional
    public Departamento atualizarDepartamento(Long id, Departamento departamento) {
        var departamentoExistente = departamentoRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Departamento não encontrado"));
        departamentoExistente.setNome(departamento.getNome());
        departamentoExistente.setDescricao(departamento.getDescricao());
        return departamentoRepository.save(departamentoExistente);
    }

    @Override
    public Departamento deletarDepartamento(Long id) {
        if (departamentoRepository.existsById(id)) {
            departamentoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Departamento não encontrado");
        }
        return null;
    }

    @Override
    @Transactional
    public Departamento adicionarDepartamentoNaEmpresa(Long empresaId, Departamento departamento) {
        var empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        departamento.setNome(departamento.getNome());
        departamento.setDescricao(departamento.getDescricao());
        departamento.setEmpresa(empresa);
        return departamentoRepository.save(departamento);
    }


}

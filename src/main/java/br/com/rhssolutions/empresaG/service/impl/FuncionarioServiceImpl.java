package br.com.rhssolutions.empresaG.service.impl;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import br.com.rhssolutions.empresaG.domain.model.funcionario.Funcionario;
import br.com.rhssolutions.empresaG.domain.repository.EmpresaRepository;
import br.com.rhssolutions.empresaG.domain.repository.FuncionarioRepository;
import br.com.rhssolutions.empresaG.exception.EmpresaNotFoundException;
import br.com.rhssolutions.empresaG.exception.FuncionarioNotFoundException;
import br.com.rhssolutions.empresaG.service.FuncionarioService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final EmpresaRepository empresaRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository, EmpresaRepository empresaRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.empresaRepository = empresaRepository;
    }

    @Override
    @Transactional
    public Funcionario salvarFuncionario(Long empresaId, Funcionario funcionario) {
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(()
                -> new EmpresaNotFoundException("Empresa não encontrada"));
        Boolean funcionarioExistente = funcionarioRepository.existsByCpf(funcionario.getCpf());
        if (funcionarioExistente) {
            throw new FuncionarioNotFoundException("Funcionário já cadastrado com este CPF.");
        } else {
            funcionario.setEmpresa(empresa);
            return funcionarioRepository.save(funcionario);
        }
    }

    @Override
    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(()
                -> new FuncionarioNotFoundException("Funcionário não encontrado"));
    }

    @Override
    @Transactional
    public Funcionario atualizarFuncionario(Long id, Funcionario funcionario) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(id).orElseThrow(()
                -> new FuncionarioNotFoundException("Funcionário não encontrado"));
        funcionarioExistente.setNome(funcionario.getNome());
        funcionarioExistente.setEmail(funcionario.getEmail());
        funcionarioExistente.setTelefone(funcionario.getTelefone());
        funcionarioExistente.setSalario(funcionario.getSalario());
        return funcionarioRepository.save(funcionarioExistente);
    }

    @Override
    @Transactional
    public void deletarFuncionario(Long id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
        } else {
            throw new FuncionarioNotFoundException("Funcionário não encontrado");
        }
    }
}



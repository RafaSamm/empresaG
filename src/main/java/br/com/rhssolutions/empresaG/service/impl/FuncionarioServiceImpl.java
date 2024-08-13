package br.com.rhssolutions.empresaG.service.impl;

import br.com.rhssolutions.empresaG.domain.model.funcionario.Funcionario;
import br.com.rhssolutions.empresaG.domain.repository.EmpresaRepository;
import br.com.rhssolutions.empresaG.domain.repository.FuncionarioRepository;
import br.com.rhssolutions.empresaG.service.FuncionarioService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final EmpresaRepository empresaRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository, EmpresaRepository empresaRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.empresaRepository = empresaRepository;
    }

    @Override
    public Funcionario salvarFuncionario(Long empresaId, Funcionario funcionario) {
        var empresa = empresaRepository.findById(empresaId).orElseThrow(()
                -> new RuntimeException("Empresa não encontrada"));
        var funcionarioExistente = funcionarioRepository.existsByCpf(funcionario.getCpf());
        if (funcionarioExistente) {
            throw new IllegalArgumentException("Funcionário já cadastrado com este CPF.");
        } else {
            funcionario.setEmpresa(empresa);
            return funcionarioRepository.save(funcionario);
        }
    }

    @Override
    public Optional<Funcionario> buscarPorId(Long id) {
        if (funcionarioRepository.existsById(id)) {
            return funcionarioRepository.findById(id);
        } else {
            throw new RuntimeException("Funcionário não encontrado");
        }
    }

    @Override
    public Funcionario atualizarFuncionario(Long id, Funcionario funcionario) {
        var funcionarioExistente = funcionarioRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Funcionário não encontrado"));
        funcionarioExistente.setNome(funcionario.getNome());
        funcionarioExistente.setEmail(funcionario.getEmail());
        funcionarioExistente.setTelefone(funcionario.getTelefone());
        funcionarioExistente.setSalario(funcionario.getSalario());
        return funcionarioRepository.save(funcionarioExistente);
    }

    @Override
    public void deletarFuncionario(Long id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Funcionário não encontrado");
        }
    }
}



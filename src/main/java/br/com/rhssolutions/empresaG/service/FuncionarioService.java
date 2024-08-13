package br.com.rhssolutions.empresaG.service;

import br.com.rhssolutions.empresaG.domain.model.funcionario.Funcionario;

import java.util.Optional;

public interface FuncionarioService {

    Funcionario salvarFuncionario(Long empresaId, Funcionario funcionario);

    Optional<Funcionario> buscarPorId(Long id);

    Funcionario atualizarFuncionario(Long id, Funcionario funcionario);

    void deletarFuncionario(Long id);
}

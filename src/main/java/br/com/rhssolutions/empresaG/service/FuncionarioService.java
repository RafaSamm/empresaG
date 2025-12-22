package br.com.rhssolutions.empresaG.service;

import br.com.rhssolutions.empresaG.domain.model.funcionario.Funcionario;

public interface FuncionarioService {

    Funcionario salvarFuncionario(Long empresaId, Funcionario funcionario);

    Funcionario buscarPorId(Long id);

    Funcionario atualizarFuncionario(Long id, Funcionario funcionario);

    void deletarFuncionario(Long id);
}

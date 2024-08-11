package br.com.rhssolutions.empresaG.domain.repository;

import br.com.rhssolutions.empresaG.domain.model.funcionario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}

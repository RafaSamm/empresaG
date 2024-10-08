package br.com.rhssolutions.empresaG.domain.repository;

import br.com.rhssolutions.empresaG.domain.model.departamento.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    Boolean existsByNome(String nome);
}

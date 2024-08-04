package br.com.rhssolutions.empresaG.domain.repository;

import br.com.rhssolutions.empresaG.domain.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Boolean existsByCnpj(String cnpj);
}

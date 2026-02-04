package br.com.rhssolutions.empresaG.domain.repository;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Boolean existsByCnpj(String cnpj);


    @Query("""
            SELECT e FROM empresas e
            LEFT JOIN FETCH e.departamentos
            LEFT JOIN FETCH e.funcionarios
            WHERE e.id = :id
            """)
    Optional<Empresa> buscarEmpresaCompleta(@Param("id") Long id);
}

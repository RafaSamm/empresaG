package br.com.rhssolutions.empresaG.domain.model.funcionario;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity(name = "funcionarios")
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Funcionario extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double salario;

    //  private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @Column(name = "inicio_data_cadastro", nullable = false, updatable = false)
    private LocalDateTime inicioDataCadastro;

    @Column(name = "fim_data_cadastro", nullable = false, updatable = false)
    private LocalDateTime fimDataCadastro;
}

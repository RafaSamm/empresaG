package br.com.rhssolutions.empresaG.domain.model.funcionario;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity(name = "funcionarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Funcionario extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double salario;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @Column(name = "inicio_data_cadastro", nullable = false, updatable = false)
    private LocalDateTime inicioDataCadastro;

    @Column(name = "fim_data_cadastro", nullable = false)
    private LocalDateTime fimDataCadastro;
}

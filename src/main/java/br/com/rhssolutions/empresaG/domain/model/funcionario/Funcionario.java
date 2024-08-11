package br.com.rhssolutions.empresaG.domain.model.funcionario;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "funcionarios")
@Data

public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Embedded
    private EnderecoFuncionario endereco;


    //  private Departamento departamento;

    //  private Empresa empresa;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "inicio_data_cadastro", nullable = false, updatable = false)
    private String InicioDataCadastro;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fim_data_cadastro", nullable = false, updatable = false)
    private String fimDataCadastro;
}

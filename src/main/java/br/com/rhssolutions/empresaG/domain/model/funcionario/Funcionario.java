package br.com.rhssolutions.empresaG.domain.model.funcionario;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private Double salario;

    @Embedded
    private EnderecoFuncionario endereco;


    //  private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @Column(name = "inicio_data_cadastro", nullable = false, updatable = false)
    private LocalDateTime inicioDataCadastro;

    @Column(name = "fim_data_cadastro", nullable = false, updatable = false)
    private LocalDateTime fimDataCadastro;
}

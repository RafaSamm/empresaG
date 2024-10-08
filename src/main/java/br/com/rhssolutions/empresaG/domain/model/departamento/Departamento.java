package br.com.rhssolutions.empresaG.domain.model.departamento;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;

@Entity(name = "departamentos")
@Data
public class Departamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @CreationTimestamp
    @Column(name = "data_criacao_departamento", nullable = false, updatable = false)
    private String dataCriacaoDepartamento;
}

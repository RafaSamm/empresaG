package br.com.rhssolutions.empresaG.domain.model;

import jakarta.persistence.*;
import lombok.Data;

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
    @JoinColumn(name = "empresa_id", nullable = false, updatable = false)
    private Empresa empresa;
}

package br.com.rhssolutions.empresaG.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "empresas")
@Data
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false, length = 18)
    private String cnpj;

    @JsonIgnore
    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Departamento> departamentos = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private String dataCriacao;
}

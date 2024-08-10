package br.com.rhssolutions.empresaG.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @Embedded
    private EnderecoEmpresa endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Departamento> departamentos = new HashSet<>();

    @CreationTimestamp
    @Column(name = "data_criacao_empresa", nullable = false, updatable = false)
    private String dataCriacaoEmpresa;
}

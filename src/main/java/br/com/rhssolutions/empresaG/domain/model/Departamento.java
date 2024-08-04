package br.com.rhssolutions.empresaG.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
}

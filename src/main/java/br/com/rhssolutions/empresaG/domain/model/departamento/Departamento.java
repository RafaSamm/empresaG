package br.com.rhssolutions.empresaG.domain.model.departamento;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "departamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Departamento {

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
    private LocalDateTime dataCriacaoDepartamento;
}

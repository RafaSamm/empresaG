package br.com.rhssolutions.empresaG.domain.model.empresa;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "rua", column = @Column(name = "rua")),
        @AttributeOverride(name = "numero", column = @Column(name = "número")),
        @AttributeOverride(name = "bairro", column = @Column(name = "bairro")),
        @AttributeOverride(name = "cidade", column = @Column(name = "cidade")),
        @AttributeOverride(name = "estado", column = @Column(name = "estado")),
        @AttributeOverride(name = "cep", column = @Column(name = "cep")),
        @AttributeOverride(name = "pais", column = @Column(name = "país"))
})
public class EnderecoEmpresa {

    private String rua;

    private Integer numero;

    private String bairro;

    private String cidade;

    private String estado;

    private String cep;

    private String pais;

}



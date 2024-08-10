package br.com.rhssolutions.empresaG.domain.model;

import br.com.rhssolutions.empresaG.domain.model.exception.CepNotFoundException;
import jakarta.persistence.*;
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


    public String setCep(String cep) {
        if (cep == null || !cep.matches("\\d{5}-\\d{3}")) {
            throw new CepNotFoundException("CEP inválido");
        } else {
            this.cep = cep;
            return cep;
        }
    }

}



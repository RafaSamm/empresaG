package br.com.rhssolutions.empresaG.domain.model.funcionario;

import br.com.rhssolutions.empresaG.domain.model.exception.CepNotFoundException;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
@AttributeOverrides({
        @AttributeOverride(name = "rua", column = @Column(name = "rua")),
        @AttributeOverride(name = "numero", column = @Column(name = "número")),
        @AttributeOverride(name = "bairro", column = @Column(name = "bairro")),
        @AttributeOverride(name = "cidade", column = @Column(name = "cidade")),
        @AttributeOverride(name = "estado", column = @Column(name = "estado")),
        @AttributeOverride(name = "cep", column = @Column(name = "cep")),
        @AttributeOverride(name = "pais", column = @Column(name = "país"))
})

public class EnderecoFuncionario {

    private String rua;
    private String numero;
    private String complemento;
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

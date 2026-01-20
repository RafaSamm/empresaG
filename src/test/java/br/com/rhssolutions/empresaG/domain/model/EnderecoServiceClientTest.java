package br.com.rhssolutions.empresaG.domain.model;

import br.com.rhssolutions.empresaG.common.ViaCepResponseConstant;
import br.com.rhssolutions.empresaG.domain.model.empresa.EnderecoEmpresa;
import br.com.rhssolutions.empresaG.dto.EnderecoEmpresaDTO;
import br.com.rhssolutions.empresaG.exception.CepNotFoundException;
import br.com.rhssolutions.empresaG.service.EnderecoServiceClient;
import br.com.rhssolutions.empresaG.service.ViaCepService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceClientTest {

    @InjectMocks
    private EnderecoServiceClient enderecoServiceClient;

    @Mock
    private ViaCepService viaCepService;

    @Test
    public void montarEnderecoEmpresa_comCepValido() {
        EnderecoEmpresaDTO dto = new EnderecoEmpresaDTO(
                null, 123, null, null, null, "30140-070",
                null);

        when(viaCepService.buscarCep("30140-070"))
                .thenReturn(ViaCepResponseConstant.enderecoValido());

        EnderecoEmpresa sut = enderecoServiceClient.montarEnderecoEmpresa(dto);

        assertThat(sut.getCep()).isEqualTo("30140-070");
        assertThat(sut.getRua()).isEqualTo("Rua dos Amoirés");
        assertThat(sut.getCidade()).isEqualTo("Belo Horizonte");

        verify(viaCepService).buscarCep("30140-070");
    }

    @Test
    public void montarEnderecoEmpresa_comCepInvalido_lancaExcecao() {
        EnderecoEmpresaDTO dto = new EnderecoEmpresaDTO(
                null, null, null, null, null, "00010-001",
                null);

        when(viaCepService.buscarCep("00010-001")).thenThrow(new CepNotFoundException("CEP inválido"));

        assertThatThrownBy(() -> enderecoServiceClient.montarEnderecoEmpresa(dto)
        ).isInstanceOf(CepNotFoundException.class)
                .hasMessage("CEP inválido");

        verify(viaCepService).buscarCep("00010-001");
    }
}

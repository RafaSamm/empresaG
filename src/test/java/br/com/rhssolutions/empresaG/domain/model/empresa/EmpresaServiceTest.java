package br.com.rhssolutions.empresaG.domain.model.empresa;

import br.com.rhssolutions.empresaG.domain.repository.EmpresaRepository;
import br.com.rhssolutions.empresaG.service.impl.EmpresaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.rhssolutions.empresaG.common.EmpresaConstant.criarEmpresa;
import static br.com.rhssolutions.empresaG.common.EmpresaConstant.criarEmpresaInvalido;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmpresaServiceTest {

    @InjectMocks
    private EmpresaServiceImpl empresaService;

    @Mock
    private EmpresaRepository empresaRepository;

    @Test
    public void criarEmpresa_comDadosValidos_retornaEmpresa() {
        when(empresaRepository.save(criarEmpresa())).thenReturn(criarEmpresa());

        var sut = empresaService.criarEmpresa(criarEmpresa());
        assertThat(sut).isEqualTo(sut);
    }

    @Test
    public void criarEmpresa_comDadosInvalidos_retornaEmpresa() {
        when(empresaRepository.save(criarEmpresaInvalido())).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> empresaService.criarEmpresa(criarEmpresaInvalido()))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void criarEmpresa_comDadosValidos_QuandoCNPJjaExiste() {
        var empresa = criarEmpresa();
        when(empresaRepository.existsByCnpj(empresa.getCnpj())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> empresaService.criarEmpresa(empresa));
    }

}

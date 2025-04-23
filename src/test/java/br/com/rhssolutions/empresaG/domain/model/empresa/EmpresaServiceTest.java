package br.com.rhssolutions.empresaG.domain.model.empresa;

import br.com.rhssolutions.empresaG.domain.model.exception.EmpresaNotFoundException;
import br.com.rhssolutions.empresaG.domain.repository.EmpresaRepository;
import br.com.rhssolutions.empresaG.service.impl.EmpresaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static br.com.rhssolutions.empresaG.common.EmpresaConstant.criarEmpresa;
import static br.com.rhssolutions.empresaG.common.EmpresaConstant.criarEmpresaInvalido;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
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

    @Test
    public void buscarEmpresaPorId_comIdValido_retornaEmpresa() {
        var empresaEsperada = criarEmpresa();
        when(empresaRepository.findById(anyLong())).thenReturn(Optional.of(empresaEsperada));

        Optional<Empresa> sut = empresaService.buscarEmpresaPorId(1L);
        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(empresaEsperada);
    }

    @Test
    public void buscarEmpresaPorId_comIdInvalido_retornaExcecao() {
        when(empresaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> empresaService.buscarEmpresaPorId(1L)
        ).isInstanceOf(EmpresaNotFoundException.class)
                .hasMessage("Empresa não encontrada");
    }

    @Test
    public void buscarTodasEmpresas() {
        var empresas = List.of(criarEmpresa());

        when(empresaRepository.findAll()).thenReturn(empresas);
        Iterable<Empresa> sut = empresaService.buscarTodasEmpresas();

        //Conversão de iterable para list para facilitar a asserção
        List<Empresa> empresasList = StreamSupport.stream(sut.spliterator(),
                false).toList();

        assertThat(empresasList).isNotEmpty();
        assertThat(empresasList).hasSize(1);
        assertThat(empresasList.getFirst()).isEqualTo(criarEmpresa());
    }

    @Test
    public void buscarTodasEmpresas_quandoNaoExistemEmpresas_lancaExcecao() {
        when(empresaRepository.findAll()).thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> empresaService.buscarTodasEmpresas()
        ).isInstanceOf(EmpresaNotFoundException.class)
                .hasMessage("Não há empresas cadastradas");
    }

    @Test
    public void deletarEmpresa_comIdValido() {
        var empresa = criarEmpresa();
        when(empresaRepository.findById(anyLong())).thenReturn(Optional.of(empresa));

        empresaService.deletarEmpresaPorId(1L);

        verify(empresaRepository).delete(empresa);
    }

    @Test
    public void deletarEmpresa_comIdInvalido_retornaExcecao() {
        when(empresaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> empresaService.deletarEmpresaPorId(1L)
        ).isInstanceOf(EmpresaNotFoundException.class)
                .hasMessage("Empresa não encontrada");
    }


}

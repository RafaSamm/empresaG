package br.com.rhssolutions.empresaG.domain.model.empresa;

import br.com.rhssolutions.empresaG.common.EmpresaConstant;
import br.com.rhssolutions.empresaG.domain.repository.EmpresaRepository;
import br.com.rhssolutions.empresaG.dto.EnderecoEmpresaDTO;
import br.com.rhssolutions.empresaG.exception.EmpresaNotFoundException;
import br.com.rhssolutions.empresaG.service.EnderecoServiceClient;
import br.com.rhssolutions.empresaG.service.impl.EmpresaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.rhssolutions.empresaG.common.EmpresaConstant.CEP_VALIDO;
import static br.com.rhssolutions.empresaG.common.EmpresaConstant.criarEmpresa;
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

    @Mock
    private EnderecoServiceClient enderecoServiceClient;

    @Test
    public void criarEmpresa_comDadosValidos_retornaEmpresa() {
        //Arrange
        Empresa empresaCriada = EmpresaConstant.criarEmpresa();
        EnderecoEmpresaDTO enderecoEmpresaDTO = EmpresaConstant.criarEnderecoEmpresaDTO();
        EnderecoEmpresa enderecoCompleto = EmpresaConstant.criarEnderecoEmpresaCompleto();

        when(empresaRepository.existsByCnpj(empresaCriada.getCnpj())).thenReturn(false);
        when(enderecoServiceClient.montarEnderecoEmpresa(enderecoEmpresaDTO))
                .thenReturn(enderecoCompleto);
        when(empresaRepository.save(empresaCriada)).thenReturn(empresaCriada);

        //Act
        Empresa sut = empresaService.criarEmpresa(empresaCriada, enderecoEmpresaDTO);

        //Assert
        assertThat(sut).isNotNull();
        assertThat(sut.getEndereco()).isNotNull();
        assertThat(sut.getEndereco().getRua()).isEqualTo("Rua Carlo de Falco");
        assertThat(sut.getEndereco().getCep()).isEqualTo(CEP_VALIDO);

        verify(empresaRepository).existsByCnpj(empresaCriada.getCnpj());
        verify(empresaRepository).save(empresaCriada);

    }

    @Test
    public void criarEmpresa_comDadosInvalidos_retornaExcecao() {
        Empresa empresaCriada = EmpresaConstant.criarEmpresa();
        EnderecoEmpresaDTO enderecoEmpresaDTO = EmpresaConstant.criarEnderecoEmpresaDTO();

        when(empresaRepository.save(empresaCriada)).thenThrow(EmpresaNotFoundException.class);

        assertThatThrownBy(() -> empresaService.criarEmpresa(empresaCriada, enderecoEmpresaDTO))
                .isInstanceOf(EmpresaNotFoundException.class);

        verify(empresaRepository).save(empresaCriada);
        verify(enderecoServiceClient).montarEnderecoEmpresa(enderecoEmpresaDTO);
        verify(empresaRepository).existsByCnpj(empresaCriada.getCnpj());


    }

    @Test
    void criarEmpresa_comCnpjDuplicado_retornaExcecao() {
        Empresa empresa = EmpresaConstant.criarEmpresa();
        EnderecoEmpresaDTO enderecoDTO = EmpresaConstant.criarEnderecoEmpresaDTO();

        when(empresaRepository.existsByCnpj(empresa.getCnpj()))
                .thenReturn(true);
        EmpresaServiceImpl sut = new EmpresaServiceImpl(empresaRepository, enderecoServiceClient);

        assertThrows(EmpresaNotFoundException.class,
                () -> sut.criarEmpresa(empresa, enderecoDTO));
    }


    @Test
    public void buscarEmpresaPorId_comIdValido_retornaEmpresa() {
        var empresaEsperada = criarEmpresa();
        when(empresaRepository.findById(anyLong())).thenReturn(Optional.of(empresaEsperada));

        Empresa sut = empresaService.buscarEmpresaPorId(1L);
        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(empresaEsperada);
    }

    @Test
    public void buscarEmpresaPorId_comIdInvalido_retornaExcecao() {
        when(empresaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> empresaService.buscarEmpresaPorId(1L)
        ).isInstanceOf(EmpresaNotFoundException.class)
                .hasMessage("Empresa não encontrada");
    }

    @Test
    public void buscarTodasEmpresas_quandoExistiremEmpresas_retornaLista() {
        List<Empresa> empresas = List.of(EmpresaConstant.criarEmpresa());

        when(empresaRepository.findAll()).thenReturn(empresas);

        List<Empresa> sut = empresaService.buscarTodasEmpresasAdmin();

        assertThat(sut).isNotEmpty()
                .hasSize(1)
                .isEqualTo(empresas)
                .containsExactlyElementsOf(empresas);

        verify(empresaRepository).findAll();
    }

    @Test
    public void buscarTodasEmpresas_quandoNaoExistemEmpresas_lancaExcecao() {
        when(empresaRepository.findAll()).thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> empresaService.buscarTodasEmpresasAdmin()
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

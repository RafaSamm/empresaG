package br.com.rhssolutions.empresaG.service.impl;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import br.com.rhssolutions.empresaG.domain.model.empresa.EnderecoEmpresa;
import br.com.rhssolutions.empresaG.domain.repository.EmpresaRepository;
import br.com.rhssolutions.empresaG.dto.EnderecoEmpresaDTO;
import br.com.rhssolutions.empresaG.exception.EmpresaNotFoundException;
import br.com.rhssolutions.empresaG.service.EmpresaService;
import br.com.rhssolutions.empresaG.service.EnderecoServiceClient;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final EnderecoServiceClient enderecoServiceClient;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository, EnderecoServiceClient enderecoServiceClient) {
        this.empresaRepository = empresaRepository;
        this.enderecoServiceClient = enderecoServiceClient;
    }


    @Override
    @Transactional
    public Empresa criarEmpresa(Empresa empresa, EnderecoEmpresaDTO enderecoEmpresaDTO) {
        if (empresaRepository.existsByCnpj((empresa.getCnpj()))) {
            throw new EmpresaNotFoundException("Empresa já existe com este CNPJ");
        }
        EnderecoEmpresa enderecoEmpresa = enderecoServiceClient.montarEnderecoEmpresa(enderecoEmpresaDTO);
        empresa.setEndereco(enderecoEmpresa);

        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa buscarEmpresaPorId(Long id) {
        return empresaRepository.findById(id).orElseThrow(() ->
                new EmpresaNotFoundException("Empresa não encontrada"));
    }

    @Override
    @Transactional
    public void deletarEmpresaPorId(Long id) {
        empresaRepository.delete(empresaRepository.findById(id).orElseThrow(() ->
                new EmpresaNotFoundException("Empresa não encontrada")));
    }

    @Override
    public List<Empresa> buscarTodasEmpresasAdmin() {
        List<Empresa> empresas = empresaRepository.findAll();
        if (empresas.isEmpty()) {
            throw new EmpresaNotFoundException("Não há empresas cadastradas");
        }
        return empresas;
    }

    @Override
    public Page<Empresa> buscarTodasEmpresas(Pageable pageable) {
        Page<Empresa> empresasPage = empresaRepository.findAll(pageable);

        if (empresasPage.isEmpty())
            throw new EmpresaNotFoundException("Não há empresas cadastradas");

        return empresasPage;
    }

}

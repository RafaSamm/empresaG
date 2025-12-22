package br.com.rhssolutions.empresaG.controller;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import br.com.rhssolutions.empresaG.dto.EmpresaDTO;
import br.com.rhssolutions.empresaG.dto.mapper.EmpresaMapper;
import br.com.rhssolutions.empresaG.service.EmpresaService;
import br.com.rhssolutions.empresaG.service.EnderecoServiceClient;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;
    private final EnderecoServiceClient enderecoServiceClient;

    public EmpresaController(EmpresaService empresaService, EnderecoServiceClient enderecoServiceClient) {
        this.empresaService = empresaService;

        this.enderecoServiceClient = enderecoServiceClient;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> buscarEmpresa(@PathVariable Long id) {
        Empresa empresa = empresaService.buscarEmpresaPorId(id);

        return ResponseEntity.status(HttpStatus.OK).body(EmpresaMapper.empresaToDTO(empresa));
    }

    @PostMapping("/criar")
    public ResponseEntity<EmpresaDTO> criarEmpresa(@Valid @RequestBody EmpresaDTO dto) {
        Empresa empresa = EmpresaMapper.dtoToEmpresa(dto);

        // transformar DTO para entidade
        empresa.setEndereco(enderecoServiceClient.montarEnderecoEmpresa(dto.endereco()));

        Empresa salvar = empresaService.criarEmpresa(empresa);

        return ResponseEntity.status(HttpStatus.CREATED).body(EmpresaMapper.empresaToDTO(salvar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable Long id) {
        empresaService.deletarEmpresaPorId(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<Iterable<EmpresaDTO>> buscarTodasEmpresas() {
        Iterable<Empresa> empresas = empresaService.buscarTodasEmpresas();

        Iterable<EmpresaDTO> dtoLista = StreamSupport.stream(empresas.spliterator(), false)
                .map(EmpresaMapper::empresaToDTO)
                .toList(); //Converte para lista

        return ResponseEntity.ok(dtoLista);
    }

}

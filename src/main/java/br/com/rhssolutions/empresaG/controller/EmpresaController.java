package br.com.rhssolutions.empresaG.controller;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import br.com.rhssolutions.empresaG.dto.ApiResponse;
import br.com.rhssolutions.empresaG.dto.EmpresaDTO;
import br.com.rhssolutions.empresaG.dto.mapper.EmpresaMapper;
import br.com.rhssolutions.empresaG.service.EmpresaService;
import br.com.rhssolutions.empresaG.service.EnderecoServiceClient;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
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
    public ResponseEntity<ApiResponse<EmpresaDTO>> buscarEmpresa(@PathVariable Long id) {
        Empresa empresa = empresaService.buscarEmpresaPorId(id);

        ApiResponse<EmpresaDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Empresa encontrada com este ID",
                EmpresaMapper.empresaToDTO(empresa),
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);


    }

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse<EmpresaDTO>> criarEmpresa(@Valid @RequestBody EmpresaDTO dto) {
        Empresa empresa = EmpresaMapper.dtoToEmpresa(dto);

        // transformar DTO para entidade
        empresa.setEndereco(enderecoServiceClient.montarEnderecoEmpresa(dto.endereco()));

        Empresa salvar = empresaService.criarEmpresa(empresa);

        ApiResponse<EmpresaDTO> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Empresa criada com sucesso",
                EmpresaMapper.empresaToDTO(salvar),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<List<?>>> deletarEmpresa(@PathVariable Long id) { //Para o caso do retorno nulo onde o objeto foi deletado
        empresaService.deletarEmpresaPorId(id);

        ApiResponse<List<?>> response = new ApiResponse<>(
                HttpStatus.NO_CONTENT.value(),
                "Empresa deletada com sucesso",
                List.of(),
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<ApiResponse<Iterable<EmpresaDTO>>> buscarTodasEmpresas() {
        Iterable<Empresa> empresas = empresaService.buscarTodasEmpresas();

        Iterable<EmpresaDTO> dtoLista = StreamSupport.stream(empresas.spliterator(), false)
                .map(EmpresaMapper::empresaToDTO)
                .toList(); //Converte para lista

        ApiResponse<Iterable<EmpresaDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Lista de empresas cadastradas com sucesso",
                dtoLista,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);

    }

}

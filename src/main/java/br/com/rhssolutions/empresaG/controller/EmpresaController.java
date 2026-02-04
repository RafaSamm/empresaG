package br.com.rhssolutions.empresaG.controller;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import br.com.rhssolutions.empresaG.dto.ApiResponse;
import br.com.rhssolutions.empresaG.dto.EmpresaDTO;
import br.com.rhssolutions.empresaG.dto.mapper.EmpresaMapper;
import br.com.rhssolutions.empresaG.service.EmpresaService;
import br.com.rhssolutions.empresaG.service.EnderecoServiceClient;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService, EnderecoServiceClient enderecoServiceClient) {
        this.empresaService = empresaService;

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

        Empresa salvar = empresaService.criarEmpresa(empresa, dto.endereco());

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

    @GetMapping("/admin/todas")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<EmpresaDTO>>> buscarEmpresasComAdmin() {

        List<EmpresaDTO> dtoLista = empresaService.buscarTodasEmpresasAdmin().stream()
                .map(EmpresaMapper::empresaToDTO)
                .toList();

        ApiResponse<List<EmpresaDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Lista de empresas cadastradas com sucesso",
                dtoLista,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);

    }

    @GetMapping("/pagina")
    public ResponseEntity<ApiResponse<Page<EmpresaDTO>>> buscarEmpresasPaginadas(Pageable pageable) {
        Page<EmpresaDTO> dtoPage = empresaService.buscarTodasEmpresas(pageable)
                .map(EmpresaMapper::empresaToDTO);

        ApiResponse<Page<EmpresaDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Lista de empresas cadastradas com sucesso",
                dtoPage,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }

}

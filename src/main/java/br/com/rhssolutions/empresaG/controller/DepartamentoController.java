package br.com.rhssolutions.empresaG.controller;

import br.com.rhssolutions.empresaG.domain.model.departamento.Departamento;
import br.com.rhssolutions.empresaG.dto.ApiResponse;
import br.com.rhssolutions.empresaG.dto.DepartamentoDTO;
import br.com.rhssolutions.empresaG.dto.mapper.DepartamentoMapper;
import br.com.rhssolutions.empresaG.service.DepartamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartamentoDTO>> buscarDepartamentoPorId(@PathVariable Long id) {
        Departamento departamento = departamentoService.buscarDepartamentoPorId(id);

        ApiResponse<DepartamentoDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Departamento encontrado com este ID",
                DepartamentoMapper.departamentoToDTO(departamento),
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/criar/empresa/{empresaId}")
    public ResponseEntity<ApiResponse<DepartamentoDTO>> criarDepartamento(@PathVariable Long empresaId, @Valid @RequestBody DepartamentoDTO dto) {
        Departamento departamento = DepartamentoMapper.dtoToDepartamento(dto, null);

        Departamento salvar = departamentoService.criarDepartamento(empresaId, departamento);

        ApiResponse<DepartamentoDTO> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Departamento criado com sucesso",
                DepartamentoMapper.departamentoToDTO(salvar),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ApiResponse<DepartamentoDTO>> atualizarDepartamento(@PathVariable Long id, @Valid @RequestBody DepartamentoDTO dto) {
        Departamento departamento = DepartamentoMapper.dtoToDepartamento(dto, null);

        Departamento atualizado = departamentoService.atualizarDepartamento(id, departamento);

        ApiResponse<DepartamentoDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Departamento atualizado com sucesso",
                DepartamentoMapper.departamentoToDTO(atualizado),
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletarDepartamento(@PathVariable Long id) {
        departamentoService.deletarDepartamento(id);

        ApiResponse<Void> response = new ApiResponse<>(
                HttpStatus.NO_CONTENT.value(),
                "Departamento deletado com sucesso",
                null,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);


    }
}

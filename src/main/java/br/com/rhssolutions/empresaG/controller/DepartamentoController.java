package br.com.rhssolutions.empresaG.controller;

import br.com.rhssolutions.empresaG.domain.model.departamento.Departamento;
import br.com.rhssolutions.empresaG.dto.DepartamentoDTO;
import br.com.rhssolutions.empresaG.dto.mapper.DepartamentoMapper;
import br.com.rhssolutions.empresaG.service.DepartamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> buscarDepartamentoPorId(@PathVariable Long id) {
        Departamento departamento = departamentoService.buscarDepartamentoPorId(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(DepartamentoMapper.departamentoToDTO(departamento));

    }

    @PostMapping("/criar/empresa/{empresaId}")
    public ResponseEntity<DepartamentoDTO> criarDepartamento(@PathVariable Long empresaId, @Valid @RequestBody DepartamentoDTO dto) {
        Departamento departamento = DepartamentoMapper.dtoToDepartamento(dto, null);

        Departamento salvar = departamentoService.criarDepartamento(empresaId, departamento);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(DepartamentoMapper.departamentoToDTO(salvar));

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<DepartamentoDTO> atualizarDepartamento(@PathVariable Long id, @Valid @RequestBody DepartamentoDTO dto) {
        Departamento departamento = DepartamentoMapper.dtoToDepartamento(dto, null);

        Departamento atualizado = departamentoService.atualizarDepartamento(id, departamento);

        return ResponseEntity.ok().body(DepartamentoMapper.departamentoToDTO(atualizado));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDepartamento(@PathVariable Long id) {
        departamentoService.deletarDepartamento(id);
        return ResponseEntity.noContent().build();

    }
}

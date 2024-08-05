package br.com.rhssolutions.empresaG.controller;

import br.com.rhssolutions.empresaG.domain.model.Departamento;
import br.com.rhssolutions.empresaG.service.DepartamentoService;
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
    public ResponseEntity<Departamento> buscarDepartamentoPorId(@PathVariable Long id) {
        var departamento = departamentoService.buscarDepartamento(id);
        return ResponseEntity.ok(departamento);
    }

//    @PostMapping("/criar")
//    public ResponseEntity<Departamento> criarDepartamento(@RequestBody Departamento departamento) {
//        var novoDepartamento = departamentoService.criarDepartamento(departamento);
//        return ResponseEntity.ok(novoDepartamento);
//    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Departamento> atualizarDepartamento(@PathVariable Long id, @RequestBody Departamento departamento) {
        var departamentoAtualizado = departamentoService.atualizarDepartamento(id, departamento);
        return ResponseEntity.ok(departamentoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Departamento> deletarDepartamento(@PathVariable Long id) {
        var departamentoDeletado = departamentoService.deletarDepartamento(id);
        return ResponseEntity.ok(departamentoDeletado);
    }
}

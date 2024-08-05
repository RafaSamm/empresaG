package br.com.rhssolutions.empresaG.controller;

import br.com.rhssolutions.empresaG.domain.model.Departamento;
import br.com.rhssolutions.empresaG.domain.model.Empresa;
import br.com.rhssolutions.empresaG.service.DepartamentoService;
import br.com.rhssolutions.empresaG.service.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;
    private final DepartamentoService departamentoService;

    public EmpresaController(EmpresaService empresaService, DepartamentoService departamentoService) {
        this.empresaService = empresaService;
        this.departamentoService = departamentoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarEmpresa(@PathVariable Long id) {
        var empresa = empresaService.buscarEmpresaPorId(id);
        return ResponseEntity.ok(empresa);
    }

    @PostMapping("/criar")
    public ResponseEntity<Empresa> criarEmpresa(@RequestBody Empresa empresa) {
        var novaEmpresa = empresaService.criarEmpresa(empresa);
        return ResponseEntity.ok(novaEmpresa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empresa> deletarEmpresa(@PathVariable Long id) {
        var empresa = empresaService.deletarEmpresaPorId(id);
        return ResponseEntity.ok(empresa);
    }


    @PostMapping("/{empresaId}/departamento")
    public ResponseEntity<Departamento> adicionarDepartamentoNaEmpresa(@PathVariable Long empresaId, @RequestBody Departamento departamento) {
        var departamentoAdicionado = departamentoService.adicionarDepartamentoNaEmpresa(empresaId, departamento);
        return ResponseEntity.ok(departamentoAdicionado);
    }


}

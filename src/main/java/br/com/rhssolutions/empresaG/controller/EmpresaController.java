package br.com.rhssolutions.empresaG.controller;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import br.com.rhssolutions.empresaG.service.EmpresaService;
import br.com.rhssolutions.empresaG.service.EnderecoServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Empresa> buscarEmpresa(@PathVariable Long id) {
        return ResponseEntity.ok(empresaService.buscarEmpresaPorId(id));
    }

    @PostMapping("/criar")
    public ResponseEntity<Empresa> criarEmpresa(@RequestBody Empresa empresa) {
        var novaEmpresa = empresaService.criarEmpresa(empresa);
        return ResponseEntity.ok(novaEmpresa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empresa> deletarEmpresa(@PathVariable Long id) {
        empresaService.deletarEmpresaPorId(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<Iterable<Empresa>> buscarTodasEmpresas() {
        var empresas = empresaService.buscarTodasEmpresas();
        return ResponseEntity.ok(empresas);
    }

}

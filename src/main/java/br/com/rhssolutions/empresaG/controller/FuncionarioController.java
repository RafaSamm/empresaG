package br.com.rhssolutions.empresaG.controller;

import br.com.rhssolutions.empresaG.domain.model.funcionario.Funcionario;
import br.com.rhssolutions.empresaG.service.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Funcionario>> buscarFuncionarioPorId(@PathVariable Long id) {
        var funcionario = funcionarioService.buscarPorId(id);
        return ResponseEntity.ok().body(funcionario);
    }

    @PostMapping("/cadastrar/empresa/{empresaId}")
    public ResponseEntity<Funcionario> cadastrarFuncionario(@PathVariable Long empresaId, @RequestBody Funcionario funcionario) {
        var novoFuncionario = funcionarioService.salvarFuncionario(empresaId, funcionario);
        return ResponseEntity.ok().body(novoFuncionario);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        var funcionarioAtualizado = funcionarioService.atualizarFuncionario(id, funcionario);
        return ResponseEntity.ok().body(funcionarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Funcionario>> deletarFuncionario(@PathVariable Long id) {
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}

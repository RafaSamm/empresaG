package br.com.rhssolutions.empresaG.controller;

import br.com.rhssolutions.empresaG.domain.model.funcionario.Funcionario;
import br.com.rhssolutions.empresaG.dto.FuncionarioDTO;
import br.com.rhssolutions.empresaG.dto.mapper.FuncionarioMapper;
import br.com.rhssolutions.empresaG.service.EnderecoServiceClient;
import br.com.rhssolutions.empresaG.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;
    private final EnderecoServiceClient enderecoServiceClient;

    public FuncionarioController(FuncionarioService funcionarioService, EnderecoServiceClient enderecoServiceClient) {
        this.funcionarioService = funcionarioService;
        this.enderecoServiceClient = enderecoServiceClient;
    }

    @GetMapping("{id}")
    public ResponseEntity<FuncionarioDTO> buscarFuncionarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(FuncionarioMapper.funcionarioToDTO(funcionarioService.buscarPorId(id)));
    }

    @PostMapping("/cadastrar/empresa/{empresaId}")
    public ResponseEntity<FuncionarioDTO> cadastrarFuncionario(@PathVariable Long empresaId, @Valid @RequestBody FuncionarioDTO dto) {
        Funcionario funcionario = FuncionarioMapper.dtoToFuncionario(dto, null);

        funcionario.setEnderecoFuncionario(enderecoServiceClient.montarEnderecoFuncionario(dto.endereco()));

        Funcionario salvar = funcionarioService.salvarFuncionario(empresaId, funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(FuncionarioMapper.funcionarioToDTO(salvar));

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<FuncionarioDTO> atualizarFuncionario(@PathVariable Long id, @Valid @RequestBody FuncionarioDTO dto) {
        Funcionario funcionario = FuncionarioMapper.dtoToFuncionario(dto, null);

        Funcionario atualizado = funcionarioService.atualizarFuncionario(id, funcionario);

        return ResponseEntity.ok().body(FuncionarioMapper.funcionarioToDTO(atualizado));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}

package br.com.rhssolutions.empresaG.controller;

import br.com.rhssolutions.empresaG.domain.model.funcionario.Funcionario;
import br.com.rhssolutions.empresaG.dto.ApiResponse;
import br.com.rhssolutions.empresaG.dto.FuncionarioDTO;
import br.com.rhssolutions.empresaG.dto.mapper.FuncionarioMapper;
import br.com.rhssolutions.empresaG.service.EnderecoServiceClient;
import br.com.rhssolutions.empresaG.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    public ResponseEntity<ApiResponse<FuncionarioDTO>> buscarFuncionarioPorId(@PathVariable Long id) {

        Funcionario funcionario = funcionarioService.buscarPorId(id);

        ApiResponse<FuncionarioDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Funcionario encontrado com sucesso",
                FuncionarioMapper.funcionarioToDTO(funcionario),
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cadastrar/empresa/{empresaId}")
    public ResponseEntity<ApiResponse<FuncionarioDTO>> cadastrarFuncionario(@PathVariable Long empresaId, @Valid @RequestBody FuncionarioDTO dto) {
        Funcionario funcionario = FuncionarioMapper.dtoToFuncionario(dto, null);

        funcionario.setEnderecoFuncionario(enderecoServiceClient.montarEnderecoFuncionario(dto.endereco()));

        Funcionario salvar = funcionarioService.salvarFuncionario(empresaId, funcionario);

        ApiResponse<FuncionarioDTO> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Funcionario cadastrado com sucesso",
                FuncionarioMapper.funcionarioToDTO(salvar),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ApiResponse<FuncionarioDTO>> atualizarFuncionario(@PathVariable Long id, @Valid @RequestBody FuncionarioDTO dto) {
        Funcionario funcionario = FuncionarioMapper.dtoToFuncionario(dto, null);

        Funcionario atualizado = funcionarioService.atualizarFuncionario(id, funcionario);

        ApiResponse<FuncionarioDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Funcionario atualizado com sucesso",
                FuncionarioMapper.funcionarioToDTO(atualizado),
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<List<?>>> deletarFuncionario(@PathVariable Long id) { //Para o caso do retorno nulo onde o objeto foi deletado
        funcionarioService.deletarFuncionario(id);

        ApiResponse<List<?>> response = new ApiResponse<>(
                HttpStatus.NO_CONTENT.value(),
                "Funcionario deletado com sucesso",
                List.of(),
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);

    }
}

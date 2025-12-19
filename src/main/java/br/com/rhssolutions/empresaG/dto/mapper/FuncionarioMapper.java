package br.com.rhssolutions.empresaG.dto.mapper;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import br.com.rhssolutions.empresaG.domain.model.funcionario.EnderecoFuncionario;
import br.com.rhssolutions.empresaG.domain.model.funcionario.Funcionario;
import br.com.rhssolutions.empresaG.dto.EnderecoFuncionarioDTO;
import br.com.rhssolutions.empresaG.dto.FuncionarioDTO;

public class FuncionarioMapper {

    public static FuncionarioDTO funcionarioToDTO(Funcionario funcionario) {
        if (funcionario == null) return null;

        return new FuncionarioDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getDataNascimento(),
                funcionario.getEmail(),
                funcionario.getTelefone(),
                funcionario.getSalario(),
                enderecoFuncionarioToDTO(funcionario.getEnderecoFuncionario()),
                funcionario.getEmpresa().getId()
        );
    }

    public static EnderecoFuncionarioDTO enderecoFuncionarioToDTO(EnderecoFuncionario enderecoFuncionario) {
        if (enderecoFuncionario == null) return null;

        return new EnderecoFuncionarioDTO(
                enderecoFuncionario.getRua(),
                enderecoFuncionario.getNumero(),
                enderecoFuncionario.getComplemento(),
                enderecoFuncionario.getBairro(),
                enderecoFuncionario.getCidade(),
                enderecoFuncionario.getEstado(),
                enderecoFuncionario.getCep(),
                enderecoFuncionario.getPais()
        );
    }

    public static Funcionario dtoToFuncionario(FuncionarioDTO funcionarioDTO, Empresa empresa) {
        if (funcionarioDTO == null) return null;

        Funcionario funcionario = new Funcionario();
        if (funcionarioDTO.id() != null) {
            funcionario.setId(funcionarioDTO.id());
        }
        funcionario.setNome(funcionarioDTO.nome());
        funcionario.setCpf(funcionarioDTO.cpf());
        funcionario.setDataNascimento(funcionarioDTO.dataNascimento());
        funcionario.setEmail(funcionarioDTO.email());
        funcionario.setTelefone(funcionarioDTO.telefone());
        funcionario.setSalario(funcionarioDTO.salario());
        funcionario.setEnderecoFuncionario(dtoToEnderecoFuncionario(funcionarioDTO.endereco()));
        funcionario.setEmpresa(empresa);
        return funcionario;
    }

    public static EnderecoFuncionario dtoToEnderecoFuncionario(EnderecoFuncionarioDTO enderecoFuncionarioDTO) {
        if (enderecoFuncionarioDTO == null) return null;

        EnderecoFuncionario enderecoFuncionario = new EnderecoFuncionario();
        enderecoFuncionario.setRua(enderecoFuncionarioDTO.rua());
        enderecoFuncionario.setNumero(enderecoFuncionarioDTO.numero());
        enderecoFuncionario.setComplemento(enderecoFuncionarioDTO.complemento());
        enderecoFuncionario.setBairro(enderecoFuncionarioDTO.bairro());
        enderecoFuncionario.setCidade(enderecoFuncionarioDTO.cidade());
        enderecoFuncionario.setEstado(enderecoFuncionarioDTO.estado());
        enderecoFuncionario.setCep(enderecoFuncionarioDTO.cep());
        enderecoFuncionario.setPais(enderecoFuncionarioDTO.pais());
        return enderecoFuncionario;
    }
}

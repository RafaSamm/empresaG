package br.com.rhssolutions.empresaG.dto.mapper;

import br.com.rhssolutions.empresaG.domain.model.empresa.Empresa;
import br.com.rhssolutions.empresaG.domain.model.funcionario.Endereco;
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
                enderecoFuncionarioToDTO(funcionario.getEndereco()),
                funcionario.getEmpresa().getId()
        );
    }

    public static EnderecoFuncionarioDTO enderecoFuncionarioToDTO(Endereco endereco) {
        if (endereco == null) return null;

        return new EnderecoFuncionarioDTO(
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.getPais()
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
        funcionario.setEndereco(dtoToEnderecoFuncionario(funcionarioDTO.endereco()));
        funcionario.setEmpresa(empresa);
        return funcionario;
    }

    public static Endereco dtoToEnderecoFuncionario(EnderecoFuncionarioDTO enderecoFuncionarioDTO) {
        if (enderecoFuncionarioDTO == null) return null;

        Endereco endereco = new Endereco();
        endereco.setRua(enderecoFuncionarioDTO.rua());
        endereco.setNumero(enderecoFuncionarioDTO.numero());
        endereco.setComplemento(enderecoFuncionarioDTO.complemento());
        endereco.setBairro(enderecoFuncionarioDTO.bairro());
        endereco.setCidade(enderecoFuncionarioDTO.cidade());
        endereco.setEstado(enderecoFuncionarioDTO.estado());
        endereco.setCep(enderecoFuncionarioDTO.cep());
        endereco.setPais(enderecoFuncionarioDTO.pais());
        return endereco;
    }
}

<h1 align="center">EMPRESAG</h1>
<p align="center">Projeto destinado a área empresarial - versão 1.0.0</p>


## TECNOLOGIAS E FERRAMENTAS
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original-wordmark.svg" width="50" height="50" /><img 
src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original-wordmark.svg" width="50" height="50" /><img 
src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/intellij/intellij-original.svg" width="50" height="50" /><img 
src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postman/postman-original-wordmark.svg" width="70" height="70" /><img 
src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/swagger/swagger-plain-wordmark.svg" height="70" width="70" />

## DIAGRAMA DE CLASSES
```mermaid
classDiagram
    class Empresa {
        -nome: String
        -cnpj: String
        -dataCriacaoEmpresa: String
        +criarEmpresa(empresa: Empresa) Empresa
        +buscarEmpresaPorId(id: Long) Empresa
        +deletarEmpresaPorId(id: Long) Empresa
        +buscarTodasEmpresas() Empresa
        
    }
    class EnderecoEmpresa {   
        -rua: String
        -numero: Integer
        -bairro: String
        -cidade: String
        -estado: String
        -cep: String
        -pais: String
    }
    class Departamento {
        -nome: String
        -descricao: String
        +criarDepartamento(empresaId: Long, departamento: Departamento) Departamento
        +buscarDepartamentoPorId(id: Long) Departamento
        +atualizarDepartamento(id: Long, departamento: Departamento) Departamento
        +deletarDepartamento(id: Long) Departamento
    }
    class Funcionario {
        -salario: Double
        -inicioDataCadastro: Date
        -fimDataCadastro: Date
        +salvarFuncionario(empresaId: Long, funcionario: Funcionario) Funcionario
        +buscarPorId(id: Long) Funcionario
        +atualizarFuncionario(id: Long, funcionario: Funcionario) Funcionario
        +deletarFuncionario(id: Long) Void    
    }
    class Pessoa {
        -nome: String
        -cpf: String
        -dataNascimento: Date
        -email: String
        -telefone: String
    }

     class Endereco {   
        -rua: String
        -numero: Integer
        -complemento: String
        -bairro: String
        -cidade: String
        -estado: String
        -cep: String
        -pais: String
    }

    Empresa "1" *-- "1" EnderecoEmpresa
    Empresa "1" *-- "N" Departamento
    Empresa "1" *-- "N" Funcionario
    Departamento "1" *-- "N" Funcionario
    Pessoa "1" *-- "1" Endereco
    Funcionario <|-- Pessoa

```


          
          
          
          

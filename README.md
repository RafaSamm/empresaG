<h1 align="center">EMPRESAG</h1>
<p align="center">Projeto destinado a área empresarial</p>

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
        +String nome
        +String cnpj
        +String dataCriacaoEmpresa
    }
    class Endereco {   
        +String rua
        +int numero
        +String bairro
        +String cidade
        +String estado
        +String cep
        +String pais
    }
    class Departamento {
        + String nome
        + String descricao
    }

    Empresa "1" *-- "1" Endereco
    Empresa "1" *-- "N" Departamento

```


          
          
          
          

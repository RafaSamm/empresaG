package br.com.rhssolutions.empresaG;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EmpresaGApplication {


    public static void main(String[] args) {
        SpringApplication.run(EmpresaGApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        var empresa = new Empresa();
//        empresa.setId(null);
//        empresa.setNome("Empresa G");
//        empresa.setCnpj("24.657.285/0001-88");
//
//
//        var departamento = new Departamento();
//        departamento.setId(null);
//        departamento.setNome("TI");
//        departamento.setDescricao("Responsável pelo sistema, estrutura e manutenção.");
//        departamento.setEmpresa(empresa);
//
//        empresa.setDepartamentos(List.of(departamento));
//
//
//        empresaRepository.save(empresa);
//        departamentoRepository.save(departamento);


}


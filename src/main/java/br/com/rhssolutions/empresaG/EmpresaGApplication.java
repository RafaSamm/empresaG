package br.com.rhssolutions.empresaG;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class EmpresaGApplication {


    public static void main(String[] args) {
        SpringApplication.run(EmpresaGApplication.class, args);
    }

}


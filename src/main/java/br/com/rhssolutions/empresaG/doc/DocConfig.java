package br.com.rhssolutions.empresaG.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("EmpresaG API")
                        .version("1.0.0")
                        .description("API para gerenciamento de empresas")
                        .contact(customContact()))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor Local")
                ));


    }

    @Bean
    public Contact customContact() {
        return new Contact()
                .name("RHSSolutions - Inovação e Qualidade de Software")
                .email("rhssolutions@gmail.com")
                .url("https://www.rhssolutions.com.br");
    }
}

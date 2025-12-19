package br.com.rhssolutions.empresaG.client;

import br.com.rhssolutions.empresaG.dto.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacepClient", url = "https://viacep.com.br/ws")
public interface ViaCepClient {

    @GetMapping("/{cep}/json/")
    ViaCepResponse buscarEndereco(@PathVariable("cep") String cep);
}

package com.ntendencia.http;

import com.ntendencia.dto.CepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viaCep", url = "${cep.servico.url}")
public interface CepServiceRepository {

    @GetMapping(value = "/{cep}/json/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    CepDTO buscarCep(@PathVariable String cep);
}

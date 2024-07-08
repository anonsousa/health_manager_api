package br.com.health.manager.domain.services;

import br.com.health.manager.domain.entities.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String VIACEP_URL = "https://viacep.com.br/ws/%s/json/";

    public Endereco getEnderecoPorCep(String cep) {
        String url = String.format(VIACEP_URL, cep);
        return restTemplate.getForObject(url, Endereco.class);
    }
}

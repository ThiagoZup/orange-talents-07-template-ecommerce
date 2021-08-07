package br.com.zupacademy.thiago.mercadolivre.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.thiago.mercadolivre.domain.Compra;

@Service
public class MockNotaFiscal {

	public void processa(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("compraId", compra.getId(), "compradorId", compra.getComprador().getId());
		restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);
	}
	
}

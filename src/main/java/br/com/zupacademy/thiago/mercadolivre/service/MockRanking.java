package br.com.zupacademy.thiago.mercadolivre.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.thiago.mercadolivre.domain.Compra;

@Service
public class MockRanking {

	public void processa(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("compraId", compra.getId(), "vendedorId", compra.getProduto().getDono().getId());
		restTemplate.postForEntity("http://localhost:8080/ranking", request, String.class);
	}
	
}

package br.com.zupacademy.thiago.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.thiago.mercadolivre.controller.dto.DetalheProdutoDto;
import br.com.zupacademy.thiago.mercadolivre.domain.Produto;
import br.com.zupacademy.thiago.mercadolivre.exception.ObjectNotFoundException;



@RestController
public class DetalheProdutoController {
	
	@PersistenceContext
	private EntityManager manager;

	
	@GetMapping(value = "/produtos/{id}") 
	public ResponseEntity<DetalheProdutoDto> detalha(@PathVariable("id") Long id){
		Produto produto = manager.find(Produto.class, id);
		if(produto==null) throw new ObjectNotFoundException("Produto com id "+id+" não encontrado");
		return ResponseEntity.ok().body(new DetalheProdutoDto(produto));
	}
}

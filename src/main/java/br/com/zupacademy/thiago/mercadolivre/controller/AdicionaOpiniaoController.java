package br.com.zupacademy.thiago.mercadolivre.controller;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zupacademy.thiago.mercadolivre.controller.form.NovaOpiniaoForm;
import br.com.zupacademy.thiago.mercadolivre.domain.Opiniao;
import br.com.zupacademy.thiago.mercadolivre.domain.Produto;
import br.com.zupacademy.thiago.mercadolivre.domain.Usuario;
import br.com.zupacademy.thiago.mercadolivre.exception.ObjectNotFoundException;
import br.com.zupacademy.thiago.mercadolivre.repository.UsuarioRepository;

@RestController
public class AdicionaOpiniaoController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private UsuarioRepository repository;
	
	@PostMapping(value= "/produtos/{id}/opinioes")
	@Transactional
	public ResponseEntity<?> adiciona(@PathVariable("id") Long id, @RequestBody @Valid NovaOpiniaoForm form) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario dono = repository.findByLogin(auth.getName()).get();
		Produto produto = manager.find(Produto.class, id);
		if(produto==null) throw new ObjectNotFoundException("Produto com id "+id+" não encontrado");
		
		Opiniao opiniao = form.toModel(produto, dono);
		manager.persist(opiniao);
		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(opiniao.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}	
}

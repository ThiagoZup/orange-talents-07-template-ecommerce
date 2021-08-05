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

import br.com.zupacademy.thiago.mercadolivre.controller.form.NovaPerguntaForm;
import br.com.zupacademy.thiago.mercadolivre.domain.Pergunta;
import br.com.zupacademy.thiago.mercadolivre.domain.Produto;
import br.com.zupacademy.thiago.mercadolivre.domain.Usuario;
import br.com.zupacademy.thiago.mercadolivre.exception.ObjectNotFoundException;
import br.com.zupacademy.thiago.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.thiago.mercadolivre.service.MockEmailSender;

@RestController
public class AdicionaPerguntaController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private MockEmailSender emailSender;
	
	@PostMapping(value= "/produtos/{id}/perguntas")
	@Transactional
	public ResponseEntity<?> cria(@PathVariable("id") Long id, @RequestBody @Valid NovaPerguntaForm form) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario interessado = repository.findByLogin(auth.getName()).get();
		Produto produto = manager.find(Produto.class, id);
		if(produto==null) throw new ObjectNotFoundException("Produto com id "+id+" não encontrado");
		
		Pergunta pergunta = form.toModel(produto, interessado);
		manager.persist(pergunta);
		emailSender.envia(pergunta);
		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pergunta.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}	
}

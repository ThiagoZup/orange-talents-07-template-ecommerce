package br.com.zupacademy.thiago.mercadolivre.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zupacademy.thiago.mercadolivre.controller.form.NovasImagensForm;
import br.com.zupacademy.thiago.mercadolivre.controller.form.NovoProdutoForm;
import br.com.zupacademy.thiago.mercadolivre.domain.Produto;
import br.com.zupacademy.thiago.mercadolivre.domain.Usuario;
import br.com.zupacademy.thiago.mercadolivre.exception.ForbiddenActionException;
import br.com.zupacademy.thiago.mercadolivre.exception.ObjectNotFoundException;
import br.com.zupacademy.thiago.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.thiago.mercadolivre.service.MockUploader;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private MockUploader uploaderFake;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cria(@RequestBody @Valid NovoProdutoForm form) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario dono = repository.findByLogin(auth.getName()).get();
		
		Produto produto = form.toModel(manager, dono);
		manager.persist(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping(value = "/{id}/imagens")
	@Transactional
	public ResponseEntity<?> adicionaImagems(@PathVariable("id") Long id, @Valid NovasImagensForm form) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario logado = repository.findByLogin(auth.getName()).get();


		Produto produto = manager.find(Produto.class, id);
		if(produto==null) throw new ObjectNotFoundException("Produto com id "+id+" não encontrado");
		if(produto.getDono()!= logado) throw new ForbiddenActionException("Usuário logado não é o dono do produto");
		List<String> links = uploaderFake.envia(form.getImagens());
		produto.associaImagem(links);
		manager.merge(produto);
		return ResponseEntity.ok().build();
	}
	
	
}

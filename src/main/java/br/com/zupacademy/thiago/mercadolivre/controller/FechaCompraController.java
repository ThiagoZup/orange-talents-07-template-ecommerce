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

import br.com.zupacademy.thiago.mercadolivre.controller.form.NovaCompraForm;
import br.com.zupacademy.thiago.mercadolivre.controller.form.RetornoPagseguroForm;
import br.com.zupacademy.thiago.mercadolivre.controller.form.RetornoPaypalForm;
import br.com.zupacademy.thiago.mercadolivre.domain.Compra;
import br.com.zupacademy.thiago.mercadolivre.domain.Produto;
import br.com.zupacademy.thiago.mercadolivre.domain.Transacao;
import br.com.zupacademy.thiago.mercadolivre.domain.Usuario;
import br.com.zupacademy.thiago.mercadolivre.domain.enums.GatewayPagamento;
import br.com.zupacademy.thiago.mercadolivre.domain.enums.StatusTransacao;
import br.com.zupacademy.thiago.mercadolivre.exception.ObjectNotFoundException;
import br.com.zupacademy.thiago.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.thiago.mercadolivre.service.MockEmailSender;
import br.com.zupacademy.thiago.mercadolivre.service.MockNotaFiscal;
import br.com.zupacademy.thiago.mercadolivre.service.MockRanking;

@RestController
public class FechaCompraController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MockEmailSender emailSender;

	@Autowired
	private MockNotaFiscal notaFiscal;
	
	@Autowired
	private MockRanking ranking;

	@PostMapping(value = "/compras")
	@Transactional
	public ResponseEntity<?> cria(@RequestBody @Valid NovaCompraForm form) {

		Produto produto = manager.find(Produto.class, form.getProdutoId());
		if (produto == null)
			throw new ObjectNotFoundException("Produto com id " + form.getProdutoId() + " não encontrado");

		int quantidade = form.getQuantidade();
		boolean abateu = produto.abataEstoque(quantidade);

		if (abateu) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Usuario comprador = usuarioRepository.findByLogin(auth.getName()).get();
			
			GatewayPagamento gateway = form.getGateway();
			
			Compra compra = new Compra( quantidade, produto, comprador, gateway);
			manager.persist(compra);
			emailSender.novaCompra(compra);

			URI uri = ServletUriComponentsBuilder.fromUriString(gateway.getUri(compra)).buildAndExpand(compra.getId()).toUri();

			return ResponseEntity.created(uri).build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping(value = "/retorno-pagseguro/{id}")
	@Transactional
	public ResponseEntity<?> processamentoPagSeguro(@PathVariable("id") Long id, @RequestBody @Valid RetornoPagseguroForm form){
		
		Compra compra = manager.find(Compra.class, id);
		if(compra==null) throw new ObjectNotFoundException("Compra com id "+id+" não encontrada");
		Transacao transacao = form.toTransacao(compra);
		compra.adicionaTransacao(transacao);
		manager.merge(compra);
		if(transacao.getStatus().equals(StatusTransacao.sucesso)) {
			notaFiscal.processa(compra);
			ranking.processa(compra);
			emailSender.confirmaCompra(compra);
		} else {
			emailSender.falhaCompra(compra);
		}
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "/retorno-paypal/{id}")
	@Transactional
	public ResponseEntity<?> processamentoPaypal(@PathVariable("id") Long id, @RequestBody @Valid RetornoPaypalForm form){
		
		Compra compra = manager.find(Compra.class, id);
		if(compra==null) throw new ObjectNotFoundException("Compra com id "+id+" não encontrada");
		Transacao transacao = form.toTransacao(compra);
		compra.adicionaTransacao(transacao);
		manager.merge(compra);
		
		if(transacao.getStatus().equals(StatusTransacao.sucesso)) {
			notaFiscal.processa(compra);
			ranking.processa(compra);
			emailSender.confirmaCompra(compra);
		}else {
			emailSender.falhaCompra(compra);
		}

		return ResponseEntity.ok().build();
	}
	
}

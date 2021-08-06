package br.com.zupacademy.thiago.mercadolivre.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.zupacademy.thiago.mercadolivre.domain.Compra;
import br.com.zupacademy.thiago.mercadolivre.domain.Pergunta;

@Component
public class MockEmailSender {
	
	@Value("${default.email.sender}")
	private String sender;

	public void enviaPergunta(Pergunta pergunta) {
		System.out.println( "to:"+pergunta.getProduto().getDono().getLogin()+System.lineSeparator()
				+"from:"+ sender+System.lineSeparator()
				+"subject: Mercado Livre - "+ pergunta.getProduto().getNome()+System.lineSeparator()
				+pergunta.getTitulo());
	}

	public void novaCompra(Compra compra) {
		System.out.println( "to:"+compra.getProduto().getDono().getLogin()+System.lineSeparator()
		+"from: "+ sender+System.lineSeparator()
		+"subject: Mercado Livre - Pedido efetuado"+System.lineSeparator()
		+"produto: "+compra.getProduto().getNome()+System.lineSeparator()
		+"quantidade: "+compra.getQuantidade()+System.lineSeparator()
		+"valor total: "+ compra.getValorCompra()
		);
		
		System.out.println( "to:"+compra.getComprador().getLogin()+System.lineSeparator()
		+"from: "+ sender+System.lineSeparator()
		+"subject: Mercado Livre - Pedido efetuado"+System.lineSeparator()
		+"produto: "+compra.getProduto().getNome()+System.lineSeparator()
		+"quantidade: "+compra.getQuantidade()+System.lineSeparator()
		+"valor total: "+ compra.getValorCompra()
		);
	}
}

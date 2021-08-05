package br.com.zupacademy.thiago.mercadolivre.service;

import org.springframework.stereotype.Component;

import br.com.zupacademy.thiago.mercadolivre.domain.Pergunta;

@Component
public class MockEmailSender {

	public void envia(Pergunta pergunta) {
		System.out.println( "to:"+pergunta.getProduto().getDono().getLogin()+System.lineSeparator()
				+"from:"+ pergunta.getInteressado().getLogin()+System.lineSeparator()
				+"subject: Mercado Livre - "+ pergunta.getProduto().getNome()+System.lineSeparator()
				+pergunta.getTitulo());
	}
}

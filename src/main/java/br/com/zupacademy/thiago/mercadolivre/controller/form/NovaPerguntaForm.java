package br.com.zupacademy.thiago.mercadolivre.controller.form;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.thiago.mercadolivre.domain.Pergunta;
import br.com.zupacademy.thiago.mercadolivre.domain.Produto;
import br.com.zupacademy.thiago.mercadolivre.domain.Usuario;

public class NovaPerguntaForm {

	@NotBlank
	private String titulo;

	public String getTitulo() {
		return titulo;
	}
	
	public Pergunta toModel(Produto produto, Usuario interessado) {
		return new Pergunta(titulo, produto, interessado);
	}
}

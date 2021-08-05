package br.com.zupacademy.thiago.mercadolivre.controller.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.thiago.mercadolivre.domain.Opiniao;
import br.com.zupacademy.thiago.mercadolivre.domain.Produto;
import br.com.zupacademy.thiago.mercadolivre.domain.Usuario;

public class NovaOpiniaoForm {

	@Min(1)
	@Max(5)
	private int nota;
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Length(max=500)
	private String descricao;

	public int getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public Opiniao toModel(Produto produto, Usuario dono) {
		return new Opiniao(nota, titulo, descricao, produto, dono);
	}
	
}

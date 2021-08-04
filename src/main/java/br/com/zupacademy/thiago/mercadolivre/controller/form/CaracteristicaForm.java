package br.com.zupacademy.thiago.mercadolivre.controller.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.thiago.mercadolivre.domain.Caracteristica;

public class CaracteristicaForm {

	@NotBlank
	private String nome;
	
	@NotBlank
	@Length(max = 1000)
	private String descricao;

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public Caracteristica toModel() {
		return new Caracteristica(nome, descricao);
	}
	
}

package br.com.zupacademy.thiago.mercadolivre.controller.dto;

import br.com.zupacademy.thiago.mercadolivre.domain.Caracteristica;

public class DetalheProdutoCaracteristicaDto {
	
	private String nome;
	private String descricao;
	
	public DetalheProdutoCaracteristicaDto(Caracteristica caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
}

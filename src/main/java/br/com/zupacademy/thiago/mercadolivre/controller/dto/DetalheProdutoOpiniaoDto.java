package br.com.zupacademy.thiago.mercadolivre.controller.dto;

import br.com.zupacademy.thiago.mercadolivre.domain.Opiniao;

public class DetalheProdutoOpiniaoDto {

	private int nota;
	private String titulo;
	private String descricao;
	
	public DetalheProdutoOpiniaoDto(Opiniao opiniao) {
		this.nota = opiniao.getNota();
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
	}

	public int getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}
	
}

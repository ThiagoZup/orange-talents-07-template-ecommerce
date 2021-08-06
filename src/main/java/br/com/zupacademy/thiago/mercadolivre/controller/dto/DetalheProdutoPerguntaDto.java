package br.com.zupacademy.thiago.mercadolivre.controller.dto;

import java.time.LocalDateTime;

import br.com.zupacademy.thiago.mercadolivre.domain.Pergunta;

public class DetalheProdutoPerguntaDto {

	private String titulo;
	private LocalDateTime dataCriacao;
	
	public DetalheProdutoPerguntaDto(Pergunta pergunta) {
		this.titulo = pergunta.getTitulo();
		this.dataCriacao = pergunta.getDataCriacao();
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
}

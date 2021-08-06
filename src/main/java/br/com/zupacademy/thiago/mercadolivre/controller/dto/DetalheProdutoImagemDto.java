package br.com.zupacademy.thiago.mercadolivre.controller.dto;

import br.com.zupacademy.thiago.mercadolivre.domain.ImagemProduto;

public class DetalheProdutoImagemDto {

	private String link;
	
	public DetalheProdutoImagemDto(ImagemProduto imagem) {
		this.link = imagem.getLink();
	}

	public String getLink() {
		return link;
	}
}

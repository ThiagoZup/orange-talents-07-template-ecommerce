package br.com.zupacademy.thiago.mercadolivre.controller.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.zupacademy.thiago.mercadolivre.domain.Produto;

public class DetalheProdutoDto {

	private String nome;
	private BigDecimal valor;
	private Long quantidade;
	private String descricao;
	private int nota;
	private int contagemOpinioes;
	private List<DetalheProdutoImagemDto> imagens = new ArrayList<>();
	private List<DetalheProdutoOpiniaoDto> opinioes = new ArrayList<>();
	private List<DetalheProdutoCaracteristicaDto> caracteristicas = new ArrayList<>();
	private List<DetalheProdutoPerguntaDto> perguntas = new ArrayList<>();

	public DetalheProdutoDto(Produto produto) {
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.quantidade = produto.getQuantidade();
		this.descricao = produto.getDescricao();
		this.imagens = produto.getImagens().stream()
				.map(DetalheProdutoImagemDto::new)
				.collect(Collectors.toList());
		this.opinioes = produto.getOpinioes().stream()
				.map(opiniao -> new DetalheProdutoOpiniaoDto(opiniao))
				.collect(Collectors.toList());
		this.caracteristicas = produto.getCaracteristicas().stream()
				.map(DetalheProdutoCaracteristicaDto::new)
				.collect(Collectors.toList());
		this.perguntas = produto.getPerguntas().stream()
				.map(DetalheProdutoPerguntaDto::new)
				.collect(Collectors.toList());
		int total = 0;
		if (this.opinioes.size() > 0) {
			for (DetalheProdutoOpiniaoDto opiniao : this.opinioes) {
				total += opiniao.getNota();
			}
			this.nota = total / this.opinioes.size();
		} else {
			this.nota = 0;
		}
		this.contagemOpinioes = this.opinioes.size();
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<DetalheProdutoImagemDto> getImagens() {
		return imagens;
	}

	public int getNota() {
		return nota;
	}

	public int getContagemOpinioes() {
		return contagemOpinioes;
	}

	public List<DetalheProdutoOpiniaoDto> getOpinioes() {
		return opinioes;
	}

	public List<DetalheProdutoCaracteristicaDto> getCaracteristicas() {
		return caracteristicas;
	}

	public List<DetalheProdutoPerguntaDto> getPerguntas() {
		return perguntas;
	}
}

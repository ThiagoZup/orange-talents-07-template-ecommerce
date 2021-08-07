package br.com.zupacademy.thiago.mercadolivre.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.thiago.mercadolivre.domain.enums.GatewayPagamento;
import br.com.zupacademy.thiago.mercadolivre.exception.DataIntegrityViolationException;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Positive
	private int quantidade;

	@ManyToOne
	@NotNull
	private Produto produto;

	@ManyToOne
	@NotNull
	private Usuario comprador;

	@NotNull
	@Enumerated(EnumType.STRING)
	private GatewayPagamento gateway;

	private BigDecimal valorProduto;

	private BigDecimal valorCompra;

	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private List<Transacao> transacoes = new ArrayList<>();

	@Deprecated
	public Compra() {
	}

	public Compra(@Positive int quantidade, @NotNull Produto produto, @NotNull Usuario comprador,
			@NotNull GatewayPagamento gateway) {
		super();
		this.quantidade = quantidade;
		this.produto = produto;
		this.comprador = comprador;
		this.gateway = gateway;
		this.valorProduto = produto.getValor();
		this.valorCompra = produto.getValor().multiply(BigDecimal.valueOf(quantidade));
	}

	public Long getId() {
		return id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public BigDecimal getValorProduto() {
		return valorProduto;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void adicionaTransacao(Transacao transacao) {
		if (this.transacoes.contains(transacao)) {
			throw new DataIntegrityViolationException("Transação já existente");
		}
		if(this.processadaComSucesso()) {
			throw new DataIntegrityViolationException("Já existe transação efetuada com sucesso para essa compra");
		}
		this.transacoes.add(transacao);
	}

	private Set<Transacao> getTransacoesConcluidasComSucesso() {
		Set<Transacao> transacoesConcluidasComSucesso = this.transacoes
				.stream()
				.filter(Transacao::concluidaComSucesso)
				.collect(Collectors.toSet());
		return transacoesConcluidasComSucesso;
	}
	
	public boolean processadaComSucesso() {
		return !this.getTransacoesConcluidasComSucesso().isEmpty();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		return Objects.equals(id, other.id);
	}
}

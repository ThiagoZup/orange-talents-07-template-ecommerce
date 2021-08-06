package br.com.zupacademy.thiago.mercadolivre.domain;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.thiago.mercadolivre.domain.enums.GatewayPagamento;

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
	
	@Deprecated
	public Compra() {
	}

	public Compra(@Positive int quantidade, @NotNull Produto produto, @NotNull Usuario comprador, @NotNull GatewayPagamento gateway) {
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

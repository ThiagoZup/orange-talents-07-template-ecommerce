package br.com.zupacademy.thiago.mercadolivre.controller.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.thiago.mercadolivre.controller.validation.ExistsId;
import br.com.zupacademy.thiago.mercadolivre.domain.Produto;
import br.com.zupacademy.thiago.mercadolivre.domain.enums.GatewayPagamento;

public class NovaCompraForm {

	@ExistsId(entityClass = Produto.class, fieldName = "id", message = "Produto não existente no banco de dados")
	@NotNull
	private Long produtoId;
	
	@Min(1)
	private int quantidade;
	
	@NotNull
	private GatewayPagamento gateway;

	public Long getProdutoId() {
		return produtoId;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}
	
}

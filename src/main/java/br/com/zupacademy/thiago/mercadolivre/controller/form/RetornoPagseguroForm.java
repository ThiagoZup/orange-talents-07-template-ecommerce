package br.com.zupacademy.thiago.mercadolivre.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.thiago.mercadolivre.domain.Compra;
import br.com.zupacademy.thiago.mercadolivre.domain.Transacao;
import br.com.zupacademy.thiago.mercadolivre.domain.enums.StatusRetornoPagSeguro;

public class RetornoPagseguroForm implements RetornoGatewayPagamento{

	@NotBlank
	private String idTransacao;
	
	@NotNull
	private StatusRetornoPagSeguro status;

	public String getIdTransacao() {
		return idTransacao;
	}

	public StatusRetornoPagSeguro getStatus() {
		return status;
	}

	public Transacao toTransacao(Compra compra) {
		return new Transacao(status.normaliza(), idTransacao, compra);
	}	
}

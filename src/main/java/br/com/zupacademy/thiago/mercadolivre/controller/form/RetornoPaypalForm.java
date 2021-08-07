package br.com.zupacademy.thiago.mercadolivre.controller.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.thiago.mercadolivre.domain.Compra;
import br.com.zupacademy.thiago.mercadolivre.domain.Transacao;
import br.com.zupacademy.thiago.mercadolivre.domain.enums.StatusTransacao;

public class RetornoPaypalForm implements RetornoGatewayPagamento{

	@NotBlank
	private String idTransacao;
	
	@Min(0)
	@Max(1)
	private int status;

	public String getIdTransacao() {
		return idTransacao;
	}

	public int getStatus() {
		return status;
	}

	public Transacao toTransacao(Compra compra) {
		
		StatusTransacao statusNormalizado = null;
		
		if(this.status == 1) {
			statusNormalizado = StatusTransacao.sucesso;
		}else {
			statusNormalizado = StatusTransacao.erro;
		}
		
		return new Transacao(statusNormalizado, idTransacao, compra);
	}	
}

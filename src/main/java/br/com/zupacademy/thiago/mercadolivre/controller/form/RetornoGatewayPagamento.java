package br.com.zupacademy.thiago.mercadolivre.controller.form;

import br.com.zupacademy.thiago.mercadolivre.domain.Compra;
import br.com.zupacademy.thiago.mercadolivre.domain.Transacao;

public interface RetornoGatewayPagamento {

	public Transacao toTransacao(Compra compra);
}

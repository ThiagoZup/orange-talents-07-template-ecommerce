package br.com.zupacademy.thiago.mercadolivre.domain.enums;

public enum StatusRetornoPagSeguro {

	SUCESSO,ERRO;

	public StatusTransacao normaliza() {
		if(this.equals(SUCESSO)) {
			return StatusTransacao.sucesso;
		}else {
			return StatusTransacao.erro;
		}
	}
}

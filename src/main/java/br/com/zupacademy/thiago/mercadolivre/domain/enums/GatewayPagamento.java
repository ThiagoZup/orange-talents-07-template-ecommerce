package br.com.zupacademy.thiago.mercadolivre.domain.enums;

import br.com.zupacademy.thiago.mercadolivre.domain.Compra;

public enum GatewayPagamento {

	Paypal {
		@Override
		public String getUri(Compra compra) {
			return "paypal.com/"+compra.getId()+"?redirectUrl=retorno-paypal/{id}";
		}
	},
	Pagseguro {
		
		@Override
		public String getUri(Compra compra) {
			return "pagseguro.com/"+compra.getId().toString()+"?redirectUrl=retorno-pagseguro/{id}";
		}
	};
	
	public abstract String getUri(Compra compra);
}

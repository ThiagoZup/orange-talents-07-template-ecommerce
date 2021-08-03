package br.com.zupacademy.thiago.mercadolivre.exception;

public class FieldMessage {

	private String campo;
	private String mensagem;
	
	public FieldMessage(String campo, String mensagem) {
		super();
		this.campo = campo;
		this.mensagem = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	
}

package br.com.zupacademy.thiago.mercadolivre.exception;

public class ForbiddenActionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ForbiddenActionException(String msg) {
		super(msg);
	}
	
	public ForbiddenActionException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

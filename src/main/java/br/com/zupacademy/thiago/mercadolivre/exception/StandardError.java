package br.com.zupacademy.thiago.mercadolivre.exception;

public class StandardError {

	private Long timestamp;
	private Integer status;
	private String message;
	private String path;
	
	public StandardError(Long timestamp, Integer status, String error, String message) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.message = error;
		this.path = message;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public String getError() {
		return message;
	}

	public String getMessage() {
		return path;
	}
	
}

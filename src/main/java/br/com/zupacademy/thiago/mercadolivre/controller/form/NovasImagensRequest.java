package br.com.zupacademy.thiago.mercadolivre.controller.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class NovasImagensRequest {

	@NotNull
	//@Size(min = 1)
	private List<MultipartFile> imagens = new ArrayList<>();
	
	public NovasImagensRequest() {
	}

	public NovasImagensRequest(@NotNull List<MultipartFile> imagens) {
		super();
		this.imagens = imagens;
	}

	public void setImagens(List<MultipartFile> imagens) {
		this.imagens = imagens;
	}

	public List<MultipartFile> getImagens() {
		return imagens;
	}
}

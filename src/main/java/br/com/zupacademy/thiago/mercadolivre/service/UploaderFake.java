package br.com.zupacademy.thiago.mercadolivre.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploaderFake {

	public String envia(MultipartFile imagem) {
		return "http://bucket.io/"+imagem.getOriginalFilename();
	}
}

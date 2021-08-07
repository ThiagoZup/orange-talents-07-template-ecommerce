package br.com.zupacademy.thiago.mercadolivre.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MockUploader {

	public List<String> envia(List<MultipartFile> imagens) {
		return imagens.stream()
				.map(imagem -> "http://bucket.io/"+imagem.getOriginalFilename())
				.collect(Collectors.toList());
	}
}

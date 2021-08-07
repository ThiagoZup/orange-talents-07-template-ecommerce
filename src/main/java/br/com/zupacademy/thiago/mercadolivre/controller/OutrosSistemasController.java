package br.com.zupacademy.thiago.mercadolivre.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.thiago.mercadolivre.controller.form.NovaCompraNFForm;
import br.com.zupacademy.thiago.mercadolivre.controller.form.NovaCompraRankingForm;

@RestController
public class OutrosSistemasController {

	@PostMapping(value = "/notas-fiscais")
	public void criaNota(@RequestBody NovaCompraNFForm form) throws InterruptedException {
		System.out.println("Criando nota para "+form.getCompraId()+" do comprador "+form.getCompradorId());
		Thread.sleep(150);
	}
	
	@PostMapping(value = "/ranking")
	public void ranking(@RequestBody NovaCompraRankingForm form) throws InterruptedException {
		System.out.println("Atualizado ranking do vendedor "+form.getVendedorId()+" referente à compra "+ form.getCompraId());
		Thread.sleep(150);
	}
}

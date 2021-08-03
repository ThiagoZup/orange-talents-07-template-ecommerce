package br.com.zupacademy.thiago.mercadolivre.controller.form;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import br.com.zupacademy.thiago.mercadolivre.controller.validation.UniqueValue;
import br.com.zupacademy.thiago.mercadolivre.domain.Categoria;
import br.com.zupacademy.thiago.mercadolivre.exception.ObjectNotFoundException;

public class NovaCategoriaForm {

	@NotBlank
	@UniqueValue(entityClass = Categoria.class, fieldName = "nome", message = "Categoria já existente no banco de dados")
	private String nome;

	@Positive
	private Long categoriaMaeId;

	public String getNome() {
		return nome;
	}

	public Long getCategoriaMaeId() {
		return categoriaMaeId;
	}

	public Categoria toModel(EntityManager manager) {
		Categoria categoria = new Categoria(nome);
		if (categoriaMaeId != null) {
			Categoria categoriaMae = manager.find(Categoria.class, categoriaMaeId);
			if (categoriaMae != null) {
				categoria.setCategoriaMae(categoriaMae);
			} else {
				throw new ObjectNotFoundException("Categoria mãe não encontrada");
			}
		}
		return categoria;
	}
}

package br.com.zupacademy.thiago.mercadolivre.controller.form;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.thiago.mercadolivre.controller.validation.ExistsId;
import br.com.zupacademy.thiago.mercadolivre.domain.Caracteristica;
import br.com.zupacademy.thiago.mercadolivre.domain.Categoria;
import br.com.zupacademy.thiago.mercadolivre.domain.Produto;
import br.com.zupacademy.thiago.mercadolivre.domain.Usuario;
import br.com.zupacademy.thiago.mercadolivre.exception.DataIntegrityViolationException;

public class NovoProdutoForm {

	@NotBlank
	private String nome;
	
	@NotNull
	@Positive
	private BigDecimal valor;
	
	@NotNull
	@Positive
	private Long quantidade;
	
	@NotBlank
	@Length(max = 1000)
	private String descricao;
	
	@NotNull
	@Positive
	@ExistsId(entityClass = Categoria.class, fieldName = "id", message = "Categoria não existente no banco de dados")
	private Long categoriaId;

	@Size(min=3, message = "Deve conter no mínimo 3 características")
	@Valid
	private List<CaracteristicaForm> caracteristicas;

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public List<CaracteristicaForm> getCaracteristicas() {
		return caracteristicas;
	}
	
	public Produto toModel(EntityManager manager, Usuario dono) {
		Categoria categoria = manager.find(Categoria.class, categoriaId);
		List<Caracteristica> novasCaracteristicas = caracteristicas.stream().map(caracteristica -> caracteristica.toModel()).collect(Collectors.toList());
		Produto produto = new Produto(nome, valor, quantidade, descricao, categoria, dono,novasCaracteristicas);
		HashSet<String> nomesCaracteristicas = new HashSet<>();
		for(Caracteristica novaCaracteristica : novasCaracteristicas) {
			novaCaracteristica.setProduto(produto);
			if(!nomesCaracteristicas.add(novaCaracteristica.getNome())) {
				throw new DataIntegrityViolationException("Mais de uma característica com o mesmo nome");
			}
		}
		return produto;
	}
	
}

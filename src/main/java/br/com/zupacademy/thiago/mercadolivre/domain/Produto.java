package br.com.zupacademy.thiago.mercadolivre.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	@Lob
	private String descricao;
	
	@NotNull
	@ManyToOne
	private Categoria categoria;

	@OneToMany(mappedBy = "produto",cascade = CascadeType.PERSIST)
	private List<Caracteristica> caracteristicas = new ArrayList<>();

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @Positive Long quantidade,
			@NotBlank @Length(max = 1000) String descricao, @NotNull Categoria categoria,
			List<Caracteristica> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.caracteristicas.addAll(caracteristicas);
	}

	public Long getId() {
		return id;
	}

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

	public Categoria getCategoria() {
		return categoria;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
	
}

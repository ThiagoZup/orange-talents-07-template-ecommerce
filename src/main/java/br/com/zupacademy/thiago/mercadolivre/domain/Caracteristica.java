package br.com.zupacademy.thiago.mercadolivre.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class Caracteristica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	@Length(max = 1000)
	@Lob
	private String descricao;
	
	@ManyToOne
	private Produto produto;

	public Caracteristica(@NotBlank String nome, @NotBlank @Length(max = 1000) String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Produto getProduto() {
		return produto;
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
		Caracteristica other = (Caracteristica) obj;
		return Objects.equals(id, other.id);
	}
}

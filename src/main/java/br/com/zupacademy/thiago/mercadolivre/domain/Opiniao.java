package br.com.zupacademy.thiago.mercadolivre.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Min(1)
	@Max(5)
	private int nota;
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Length(max=500)
	private String descricao;
	
	@NotNull
	@ManyToOne
	private Produto produto;
	
	@NotNull
	@ManyToOne
	private Usuario dono;
	
	@Deprecated
	public Opiniao() {
	}

	public Opiniao(@Min(1) @Max(5) int nota, @NotBlank String titulo, @NotBlank @Length(max = 500) String descricao,
			@NotNull Produto produto, @NotNull Usuario dono) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produto = produto;
		this.dono = dono;
	}

	public Long getId() {
		return id;
	}

	public int getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
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
		Opiniao other = (Opiniao) obj;
		return Objects.equals(id, other.id);
	}
}

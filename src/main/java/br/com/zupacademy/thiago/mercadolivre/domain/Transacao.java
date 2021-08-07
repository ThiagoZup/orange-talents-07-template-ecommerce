package br.com.zupacademy.thiago.mercadolivre.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.thiago.mercadolivre.domain.enums.StatusTransacao;

@Entity
public class Transacao {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusTransacao status;
	
	@NotBlank
	private String idTransacao;
	
	@NotNull
	@ManyToOne
	private Compra compra;
	
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	@Deprecated
	public Transacao() {	
	}

	public Transacao(@NotNull StatusTransacao status, @NotBlank String idTransacao, @NotNull Compra compra) {
		super();
		this.status = status;
		this.idTransacao = idTransacao;
		this.compra = compra;
	}

	public Long getId() {
		return id;
	}

	public StatusTransacao getStatus() {
		return status;
	}

	public String getIdTransacao() {
		return idTransacao;
	}

	public Compra getCompra() {
		return compra;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	
	public boolean concluidaComSucesso() {
		return this.status.equals(StatusTransacao.sucesso);
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
		Transacao other = (Transacao) obj;
		return Objects.equals(id, other.id);
	}
}

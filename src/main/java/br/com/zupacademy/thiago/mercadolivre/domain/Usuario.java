package br.com.zupacademy.thiago.mercadolivre.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@PastOrPresent
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@NotBlank
	@Email
	@Column(unique=true)
	private String login;
	
	@NotBlank
	@Length(min=6)
	private String senha;
	
	@Deprecated
	public Usuario() {
	}

	public Usuario(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}	
}

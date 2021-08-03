package br.com.zupacademy.thiago.mercadolivre.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.zupacademy.thiago.mercadolivre.controller.validation.UniqueValue;
import br.com.zupacademy.thiago.mercadolivre.domain.Usuario;

public class NovoUsuarioForm {

	@NotBlank
	@Email
	@UniqueValue(entityClass = Usuario.class, fieldName = "login", message = "Email já existente no banco de dados")
	private String login;
	
	//senha-limpa
	@NotBlank
	@Length(min = 6)
	private String senha;

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}
	
	public Usuario toModel() {
		return new Usuario(this.login, new BCryptPasswordEncoder().encode(this.senha));
	}
	
	
	
}

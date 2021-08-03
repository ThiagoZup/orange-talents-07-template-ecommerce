package br.com.zupacademy.thiago.mercadolivre.controller;

import java.net.URI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsuarioControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@DisplayName("deve validar os atributos e retornar o status apropriado")
	@ParameterizedTest
	@CsvSource({
		"zupperson.silva@zup.com,123456,201",
		",123456,400",
		"zupperson.silva@zup.com,,400",
		"zupperson.silva@zup.com,12345,400",
		"zupperson.silva,123456,400"
	})
	public void teste1(String login, String senha, int resultadoEsperado) throws Exception {
		URI uri = new URI("/usuarios");
		String json = "{\"login\":\""+login+"\",\"senha\":\""+senha+"\"}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(resultadoEsperado));	
	}

}
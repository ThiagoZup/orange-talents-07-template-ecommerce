package br.com.zupacademy.thiago.mercadolivre.controller;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.zupacademy.thiago.mercadolivre.domain.Categoria;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoriaControllerTest {

    @Autowired	
	private MockMvc mockMvc;

	@Test
	@Transactional
	public void deveCriarCategoria() throws Exception {
		URI uri = new URI("/categorias");
		String json = "{\"nome\":\"Eletrônicos\",\"categoriaMaeId\":null}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}
	
	@Test
	@Transactional
	public void deveCriarCategoriaComCategoriaMaeIdValido() throws Exception {
		URI uri = new URI("/categorias");
		
		EntityManager manager = Mockito.mock(EntityManager.class);
		Categoria categoriaMae = new Categoria("teste");
		Long categoriaMaeId = categoriaMae.getId();
		
		String json = "{\"nome\":\"Móveis\",\"categoriaMaeId\":"+categoriaMaeId+"}";

		Mockito.when(manager.find(Categoria.class, 1l)).thenReturn(categoriaMae);
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}
	
	@Test
	@Transactional
	public void naoDeveCriarCategoriaComCategoriaMaeIdInvalido() throws Exception {
		URI uri = new URI("/categorias");
		Long categoriaMaeId = Long.MAX_VALUE;
		
		String json = "{\"nome\":\"Móveis\",\"categoriaMaeId\":"+categoriaMaeId+"}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(404));
	}
	
	@Test
	@Transactional
	public void naoDeveCriarCategoriaComNomeVazio() throws Exception {
		URI uri = new URI("/categorias");
		
		String json = "{\"nome\":\"\",\"categoriaMaeId\":null}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}
	
	@Test
	@Transactional
	public void naoDeveCriarCategoriaComNomeRepetido() throws Exception {
		URI uri = new URI("/categorias");
		
		String json = "{\"nome\":\"Eletrônicos\",\"categoriaMaeId\":null}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON));
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(400));
	}

}
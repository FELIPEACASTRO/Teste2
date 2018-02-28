package br.com.usuario.restful.integration.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.usuario.restful.model.Usuario;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
@Sql({ "classpath:init-data.sql" })
public class UsuarioControllerTest {
	
	
	@Autowired
	private TestRestTemplate restTemplate;

	private static final String URL = "/usuario/";

	@Test
	public void testAddUsuario() throws Exception {

		Usuario usuario = new Usuario("FELIPE ANGELO", "FELIPE@HOTMAIL.COM", "27/09/1983","Corinthians");

		ResponseEntity<Usuario> responseEntity = restTemplate.postForEntity(URL,usuario,Usuario.class);

		int status = responseEntity.getStatusCodeValue();
		Usuario resultUsuario = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.CREATED.value(), status);

		assertNotNull(resultUsuario);
		assertNotNull(resultUsuario.getId().longValue());

	}

	@Test
	public void testGetUsuario() throws Exception {

		ResponseEntity<Usuario> responseEntity = restTemplate.getForEntity(URL + "{id}", Usuario.class,new Long(1));

		int status = responseEntity.getStatusCodeValue();
		Usuario resultUsuario = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		assertNotNull(resultUsuario);
		assertEquals(1l, resultUsuario.getId().longValue());

	}

	@Test
	public void testGetUsuarioNotExist() throws Exception {


		ResponseEntity<Usuario> responseEntity = restTemplate.getForEntity(URL + "{id}", Usuario.class,new Long(100));

		int status = responseEntity.getStatusCodeValue();
		Usuario resultUsuario = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND.value(), status);
		assertNull(resultUsuario);
	}

	@Test
	public void testGetAllUsuario() throws Exception {

		ResponseEntity<List> responseEntity = restTemplate.getForEntity(URL,List.class);

		int status = responseEntity.getStatusCodeValue();
		List<Usuario> userListResult = null;
		if (responseEntity.getBody() != null) {
			userListResult = responseEntity.getBody();
		}

		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		assertNotNull("Usuarios not found", userListResult);
		assertEquals("Incorrect Usuario List", 1, userListResult.size());

	}

	@Test
	public void testDeleteUsuario() throws Exception {

		ResponseEntity<Void> responseEntity = restTemplate.exchange(URL + "{id}",HttpMethod.DELETE, null, Void.class,new Long(1));

		int status = responseEntity.getStatusCodeValue();
		assertEquals("Incorrect Response Status", HttpStatus.GONE.value(), status);

	}

	@Test
	public void testUpdateUsuario() throws Exception {
		Usuario usuario = new Usuario(1l, "FELIPE ANGELO", "FELIPE@HOTMAIL.COM", "27/09/1983","Corinthians");
		
		HttpEntity<Usuario> requestEntity = new HttpEntity<Usuario>(usuario);

		ResponseEntity<Void> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, Void.class);

		int status = responseEntity.getStatusCodeValue();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
	}

}

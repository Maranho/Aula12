package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import model.Usuario;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import service.UsuarioService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioServiceTest {
	UsuarioService service;

	/*
	 * Certifique-s que sobrecarregou o equals na classe Usuario. 
	 * Certifique-se que a fixture foi criada no banco.
	 */
	@Before
	public void setUp() throws Exception {
		service = new UsuarioService();
	}
	
	@Test
	public void testValidar() {
		//para funcionar o usuario adm@usjt.br deve ter sido carregado no banco por fora
		//insert into usuario (username, password) values ('adm@usjt.br', 'usjt');
		Usuario usuario = new Usuario();
		usuario.setUsername("adm@usjt.br");
		usuario.setPassword("usjt");
		try {
			assertTrue("usuario e senha validos", service.validar(usuario));
			usuario.setUsername("");
			assertFalse("usuario invalido e senha valida", service.validar(usuario));
			usuario.setUsername("adm@usjt.br");
			usuario.setPassword("");
			assertFalse("usuario valido e senha invalida", service.validar(usuario));
			usuario.setUsername("");
			assertFalse("usuario invalido e senha invalida", service.validar(usuario));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
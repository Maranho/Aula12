package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import model.Usuario;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import dao.UsuarioDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioDAOTest {
	static UsuarioDAO dao;
	static Usuario usuario;
	
	/*
	 * Certifique-s que sobrecarregou o equals na classe Usuario. 
	 * Certifique-se que a fixture foi criada no banco.
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		dao = new UsuarioDAO();
	}
	
	@Test
	public void testValidar() {
		//para funcionar o usuario adm@usjt.br deve ter sido carregado no banco por fora
		//insert into usuario (username, password) values ('adm@usjt.br', 'usjt');
		Usuario usuario = new Usuario();
		usuario.setUsername("adm@usjt.br");
		usuario.setPassword("usjt");
		try {
			assertTrue("usuario e senha validos", dao.validar(usuario));
			usuario.setUsername("");
			assertFalse("usuario invalido e senha valida", dao.validar(usuario));
			usuario.setUsername("adm@usjt.br");
			usuario.setPassword("");
			assertFalse("usuario valido e senha invalida", dao.validar(usuario));
			usuario.setUsername("");
			assertFalse("usuario invalido e senha invalida", dao.validar(usuario));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

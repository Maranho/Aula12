package test;

import static org.junit.Assert.assertEquals;
import model.Usuario;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {
	Usuario usuario;
	@Before
	public void setUp() throws Exception {
		usuario = new Usuario();
		usuario.setPassword("123456789");
		usuario.setUsername("BelaLugosi");
	}

	@Test
	public void testGets() {
		assertEquals("getUsername", usuario.getUsername(), "BelaLugosi");
		assertEquals("getPassword", usuario.getPassword(), "123456789");
	}
	
	@Test
	public void testEquals(){
		Usuario copia = new Usuario();
		copia.setUsername(usuario.getUsername());
		copia.setPassword(usuario.getPassword());
		assertEquals("teste usuario igual a copia", usuario, copia);
	}

}

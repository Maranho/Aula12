package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClienteDAOTest.class, ClienteServiceTest.class,
		ClienteTest.class, ConnectionFactoryTest.class, UsuarioDAOTest.class,
		UsuarioServiceTest.class, UsuarioTest.class })
public class AllTests {

}

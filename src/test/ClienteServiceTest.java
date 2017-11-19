package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import model.Cliente;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import service.ClienteService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteServiceTest {
	static Cliente cliente;
	static ClienteService cs;

	/*
	 * Antes de rodar este teste, certifique-se que nao ha no banco nenhuma
	 * linha com o id igual ao do escolhido para o to instanciado abaixo. Se
	 * houver, delete. 
	 * Certifique-se também que sobrecarregou o equals na classe
	 * Cliente. 
	 * Certifique-se que a fixture cliente1 foi criada no banco.
	 * Além disso, a ordem de execução dos testes é importante; por
	 * isso a anotação FixMethodOrder logo acima do nome desta classe
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		cs = new ClienteService();
		cliente = new Cliente();
		cliente.setNome("Bela Lugosi");
		cliente.setFone("123456789");
		cliente.setEmail("bela@bauhaus.net");
		cliente.setId(-1);
	}
	
	@Test
	public void test00Carregar() {
		//para funcionar o cliente 1 deve ter sido carregado no banco por fora
		//delete from cliente;
		//insert into cliente (nome, fone) values ('nome1', 'fone1');
		Cliente fixture = new Cliente();
		fixture.setId(1);
		fixture.setNome("Carlos Drummond de Andrade");
		fixture.setFone("(11) 91234-4321");
		fixture.setEmail("cda@usjt.br");
		Cliente novo = new Cliente();
		novo.setId(1);
		try {
			novo = cs.carregar(fixture.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("testa inclusao", novo, fixture);
	}

	@Test
	public void test01Criar() {
		Cliente copia = null;
		try {
			cliente.setId(cs.criar(cliente));
			copia = cs.carregar(cliente.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("testa criacao", cliente, copia);
	}

	@Test
	public void test02Atualizar() {
		Cliente copia = null;
		cliente.setFone("999999");
			
		try {
			copia = cs.carregar(cliente.getId());
			cs.atualizar(cliente);
			copia.setFone("999999");	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("testa inclusao", cliente, copia);
	}

	@Test
	public void test03Excluir() {
		cliente.setNome(null);
		cliente.setFone(null);
		cliente.setEmail(null);
		Cliente copia = null;
		try {
			cs.excluir(cliente.getId());
			copia = cs.carregar(cliente.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("testa exclusao", cliente, copia);
	}
}
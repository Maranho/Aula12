package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import model.Cliente;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import dao.ClienteDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteDAOTest {
	static ClienteDAO dao;
	static Cliente to;
	
	/*
	 * Antes de rodar este teste, certifique-se que nao ha no banco
	 * nenhuma linha com o id igual ao do escolhido para o to instanciado
	 * abaixo. Se houver, delete. 
	 * Certifique-se de que o fixture cliente 1 existe no banco.
	 * Certifique-se também que sobrecarregou o equals na classe ClienteTO
	 * Além disso, a ordem de execução dos testes é importante; por isso a anotação
	 * FixMethodOrder logo acima do nome desta classe
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		dao = new ClienteDAO();
		to = new Cliente();
		to.setFone("123456789");
		to.setNome("Bela Lugosi");
		to.setEmail("bela@bauhaus.net");
		to.setId(-1);
	}
	
	@Test
	public void test00Carregar() {
		//para funcionar o cliente 1 deve ter sido carregado no banco por fora
		//delete from cliente;
		//insert into cliente (id, nome, fone, email) values (1, 'Carlos Drummond de Andrade', '(11) 91234-4321', 'cda@usjt.br');
		Cliente fixture = new Cliente();
		fixture.setNome("Carlos Drummond de Andrade");
		fixture.setFone("(11) 91234-4321");
		fixture.setEmail("cda@usjt.br");
		fixture.setId(1);
		Cliente novo = null;
		try {
			novo = dao.carregar(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("testa inclusao", novo, fixture);
	}

	@Test
	public void test01Inserir() {
		Cliente novo = null;
		try {
			dao.incluir(to);
			novo = dao.carregar(to.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("testa inclusao", novo, to);
	}
	
	@Test
	public void test02Atualizar() {
		to.setFone("999999");
		Cliente novo = null;
		try {
			novo = dao.carregar(to.getId());
			novo.setFone("999999");
			dao.atualizar(to);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("testa inclusao", novo, to);
	}
	
	@Test
	public void test03Excluir() {
		to.setNome(null);
		to.setFone(null);
		to.setEmail(null);
		Cliente novo = null;
		try {
			dao.excluir(to.getId());
			novo = dao.carregar(to.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("testa inclusao", novo, to);
	}
}

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import service.ClienteService;
import service.VendedorService;
import util.JSonFacade;

@WebServlet("/cliente")
public class ServicoManterClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	/*
	 * configurar a request e a response para todos os métodos
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		super.service(request, response);
	}
	/*
	 * listar clientes
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String chave = request.getParameter("chave");
		VendedorService vendedor = new VendedorService();
		ArrayList<Cliente> lista = null;

		PrintWriter out = response.getWriter();

		try {
			if (chave != null && chave.length() > 0) {
				lista = vendedor.listarClientes(chave);
			} else {
				lista = vendedor.listarClientes();
			}
			out.println(JSonFacade.listToJSon(lista));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSonFacade.errorToJSon(e));
		}
		
	}

	/*
	 * inclusão de clientes
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StringBuilder sb = JSonFacade.montaJSon(request);
		PrintWriter out = response.getWriter();

		try {
			Cliente cliente = JSonFacade.jSonToCliente(sb.toString());
			ClienteService cs = new ClienteService();
			int id = cs.criar(cliente);
			cliente.setId(id);
			//retorna o cliente cadastrado com o id atribuido pelo banco
			out.println(JSonFacade.clienteToJSon(cliente));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSonFacade.errorToJSon(e));
		}
	}
	/*
	 * atualiza clientes
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StringBuilder sb = JSonFacade.montaJSon(request);
		PrintWriter out = response.getWriter();

		try {
			Cliente cliente = JSonFacade.jSonToCliente(sb.toString());
			ClienteService cs = new ClienteService();
			cs.atualizar(cliente);
			//retorna o cliente atualizado
			out.println(JSonFacade.clienteToJSon(cliente));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSonFacade.errorToJSon(e));
		}
	}

	/*
	 * exclusão de clientes
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder sb = JSonFacade.montaJSon(request);
		PrintWriter out = response.getWriter();

		try {
			Cliente cliente = JSonFacade.jSonToCliente(sb.toString());
			ClienteService cs = new ClienteService();
			cs.excluir(cliente.getId()); 
			cliente = cs.carregar(cliente.getId());
			//retorna dados null se o cliente foi deletado
			out.println(JSonFacade.clienteToJSon(cliente));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSonFacade.errorToJSon(e));
		}
	}
	
}

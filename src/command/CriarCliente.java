package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cliente;
import service.ClienteService;

public class CriarCliente implements Command {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pId = request.getParameter("id");
		String pNome = request.getParameter("nome");
		String pFone = request.getParameter("fone");
		String pEmail = request.getParameter("email");
		int id = -1;
		try {
			id = Integer.parseInt(pId);
		} catch (Exception e) {

		}

		ClienteService cs = new ClienteService();
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome(pNome);
		cliente.setFone(pFone);
		cliente.setEmail(pEmail);
		HttpSession session = request.getSession();

		cs.criar(cliente);
		ArrayList<Cliente> lista = new ArrayList<>();
		lista.add(cliente);
		session.setAttribute("lista", lista);
		RequestDispatcher view =  request.getRequestDispatcher("ListarClientes.jsp");
		view.forward(request, response);
	}
}

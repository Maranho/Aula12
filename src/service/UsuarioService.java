package service;

import java.io.IOException;

import model.Usuario;
import dao.UsuarioDAO;

public class UsuarioService {
	UsuarioDAO dao;
	
	public UsuarioService(){
		dao = new UsuarioDAO();
	}
	
	public boolean validar(Usuario usuario) throws IOException{
		return dao.validar(usuario);
	}
}

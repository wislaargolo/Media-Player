package aplicacao;

import dao.DiretorioDAO;
import dao.MusicaDAO;
import dao.UsuarioDAO;

public class Player {
	UsuarioDAO usuarios = new UsuarioDAO();
	MusicaDAO musicas = new MusicaDAO();
	DiretorioDAO diretorios = new DiretorioDAO(musicas);

}

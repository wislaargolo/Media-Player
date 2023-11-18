package visao;

import dao.DiretorioDAO;
import dao.UsuarioDAO;
import modelo.Usuario;
import modelo.UsuarioVIP;

public class Teste {

	public Teste() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		UsuarioDAO udao = new UsuarioDAO("src/dados/usuarios.txt");
		DiretorioDAO dDAO = new DiretorioDAO("src/dados/diretorio.txt");
		//System.out.println("VIP");
		udao.carregar();
		dDAO.carregar();//System.out.println("VIP");
		Usuario usuario1 = new UsuarioVIP("SFAS", "Andre", "aa123");
		Usuario usuario2 = new UsuarioVIP("fss", "LANa", "91412s");
		udao.adicionar(usuario1);
		udao.adicionar(usuario2);
		//udao.adicionar(usuario);
		//udao.remover(usuario1);
		//System.out.println("VIP");
		dDAO.adicionar("arquivo/sfasf");
		dDAO.adicionar("arquivo/sfasf");
		dDAO.remover("arquivo/sfasf");
		for(Usuario u : udao.getUsuarios()) {
			//System.out.println("VIP");
			System.out.println(u.getNome());
			System.out.println(u.getId());
			System.out.println(u.getSenha());
			
			if(u instanceof UsuarioVIP) {
				System.out.println("VIP");
			}
		}
		
		for(String u : dDAO.getPastas()) {
			//System.out.println("VIP");
			System.out.println(u);
		
		}
		
	}

}

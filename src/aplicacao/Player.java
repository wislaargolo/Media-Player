package aplicacao;

import java.util.ArrayList;

import dao.DiretorioDAO;
import dao.MusicaDAO;
import dao.PlaylistDAO;
import dao.UsuarioDAO;
import modelo.Musica;
import modelo.Playlist;
import modelo.UsuarioVIP;

public class Player {
	
	public static void main(String[] args) {
		
		UsuarioDAO usuarios = new UsuarioDAO();
		MusicaDAO musicas = new MusicaDAO();
		DiretorioDAO diretorios = new DiretorioDAO(musicas);
		UsuarioVIP usuario = new UsuarioVIP("RM777", "Rubens", "rrmm");
		PlaylistDAO play = usuario.getPlaylistDAO();
		
		Musica musicaTeste = new Musica("Como tudo deve ser", "/dados/musicas");
		ArrayList<Musica> musicasss = new ArrayList<>();
		Playlist playlist = new Playlist("playlistDeRubens", musicasss);
		//usuario.adicionarPlaylist(playlist);
		for (Playlist l : play.getPlaylists()) {
			System.out.println(l.getNome());
		}
		
		usuario.renomearPaylist(playlist, "novoNome");
		
		System.out.println("\n\n\n\n");
		
		play = usuario.getPlaylistDAO();
		for (Playlist l : play.getPlaylists()) {
			System.out.println(l.getNome());
		}
		//System.out.println(diretorios.getCaminhoArquivo());
		
    }
	
}

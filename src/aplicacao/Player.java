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
		MusicaDAO musicas = new MusicaDAO("dados/musicas/musicas.txt");
		musicas.carregar();
		DiretorioDAO diretorios = new DiretorioDAO(musicas, "dados/diretorios/diretorios.txt");
		diretorios.carregar();
		
		UsuarioVIP usuario = new UsuarioVIP("RM777", "Rubens", "rrmm");
		PlaylistDAO play = usuario.getPlaylistDAO();
		
		Musica musicaTeste = new Musica("Como tudo deve ser", "/dados/musicas/musicasss");
		Musica musicaT2 = new Musica("Como tudo lalal ala", "/dados/musicas/musicasss");
		
		musicas.adicionar(musicaTeste);
		musicas.adicionar(musicaT2);
		
		ArrayList<Musica> musicasss = new ArrayList<>();
		musicasss.add(musicaTeste);
		musicasss.add(musicaT2);
		
		Playlist playlist = new Playlist("playlistDeRubens", musicasss);
		
		usuario.adicionarPlaylist(playlist);
		
//		for(Musica m : playlist.getMusicas()) {
//			System.out.println(m.getNome());
//		}
		
		musicas.remover(usuario,musicaTeste);
		

//		for(Musica m : playlist.getMusicas()) {
//			System.out.println(m.getNome());
//		}
//		
//		for (Playlist l : play.getPlaylists()) {
//			System.out.println(l.getNome());
//		}
//		
//		usuario.renomearPaylist(playlist, "novoNome");
//		
//		System.out.println("\n\n\n\n");
//		
//		play = usuario.getPlaylistDAO();
//		for (Playlist l : play.getPlaylists()) {
//			System.out.println(l.getNome());
//		}
		//System.out.println(diretorios.getCaminhoArquivo());
		
    }
	
}

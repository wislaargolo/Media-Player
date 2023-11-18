package modelo;

import java.util.ArrayList;

public class UsuarioVIP extends Usuario{
	
	private ArrayList<Playlist> playlists;
	
	public UsuarioVIP(String id, String nome, String senha) {
		super(id, nome, senha);
		this.playlists = new ArrayList<>();
	}

	public UsuarioVIP(String id, String nome, String senha, ArrayList<Playlist> playlists) {
		super(id, nome, senha);
		this.playlists = playlists;
	}

	public ArrayList<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(ArrayList<Playlist> playlists) {
		this.playlists = playlists;
	}

}

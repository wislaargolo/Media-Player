package dao;

import java.util.ArrayList;

import modelo.Playlist;


public class PlaylistDAO implements ArquivoDAO {
	private ArrayList<Playlist> playlists;

	public PlaylistDAO() {
		playlists = new ArrayList<>();
	}

	@Override
	public void carregar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionar(Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(Object object) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(ArrayList<Playlist> playlists) {
		this.playlists = playlists;
	}
	
	
	
	

}

package modelo;

import java.util.ArrayList;

public class Playlist {
	
	private String nome; 
	private ArrayList<Musica> musicas;

	public Playlist() {
		this.musicas = new ArrayList<>();
	}
	
	public Playlist(String nome, ArrayList<Musica> musicas) {
		this.nome = nome;
		this.musicas = musicas;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(ArrayList<Musica> musicas) {
		this.musicas = musicas;
	}
	

}

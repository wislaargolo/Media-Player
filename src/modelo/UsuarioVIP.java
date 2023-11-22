package modelo;


import dao.PlaylistDAO;

public class UsuarioVIP extends Usuario {
	
	private PlaylistDAO playlistDAO;
	
	public UsuarioVIP(String id, String nome, String senha) {
		super(id, nome, senha);
		String diretorioAtual = System.getProperty("user.dir");
		playlistDAO = new PlaylistDAO(diretorioAtual + "/src/dados/playlistGeral.txt");
		playlistDAO.carregar(this);
	}
	
	public void adicionarPlaylist(Playlist playlist) {
		playlistDAO.adicionar(playlist, this);
	}
	
	public void removerPlaylist(Playlist playlist) {
		playlistDAO.remover(playlist, this);
	}

	public PlaylistDAO getPlaylistDAO() {
		return playlistDAO;
	}

	public void setPlaylistDAO(PlaylistDAO playlistDAO) {
		this.playlistDAO = playlistDAO;
	}

	public String printarCaminho() {
		String diretorioAtual = System.getProperty("user.dir");
		return diretorioAtual;
	}


}

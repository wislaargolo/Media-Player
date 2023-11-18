package modelo;

public class Musica {
	
	private String nome;
	private String caminhoArquivo;

	public Musica() {
		// TODO Auto-generated constructor stub
	}
	
	public Musica(String nome, String caminhoArquivo) {
		super();
		this.nome = nome;
		this.caminhoArquivo = caminhoArquivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}
	
	

}

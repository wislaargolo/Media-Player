package modelo;

public class Musica {
	
	private String nome;
	private Usuario dono;
	private String caminhoArquivo;

	public Musica() {
		// TODO Auto-generated constructor stub
	}
	
	public Musica(String nome, String caminhoArquivo) {
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

	public Usuario getDono() {
		return dono;
	}

	public void setDono(Usuario dono) {
		this.dono = dono;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Musica musica = (Musica) obj;

        return nome.equals(musica.nome) && dono.equals(musica.dono) &&
               caminhoArquivo.equals(musica.caminhoArquivo);
    }
	
}

package modelo;

public class Diretorio {
	
	private Usuario dono;
	private String caminhoArquivo;

	public Diretorio(Usuario dono, String caminhoDiretorio) {
		super();
		this.dono = dono;
		this.caminhoArquivo = caminhoArquivo;
	}
	public Usuario getDono() {
		return dono;
	}
	public void setDono(Usuario dono) {
		this.dono = dono;
	}
	public String getCaminhoDiretorio() {
		return caminhoArquivo;
	}
	public void setCaminhoDiretorio(String caminhoDiretorio) {
		this.caminhoArquivo = caminhoDiretorio;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Diretorio diretorio = (Diretorio) obj;

        return dono.equals(diretorio.dono) &&
               caminhoArquivo.equals(diretorio.caminhoArquivo);
    }
	

}

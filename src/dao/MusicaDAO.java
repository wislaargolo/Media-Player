package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import modelo.Musica;

public class MusicaDAO implements ArquivoDAO{
	private ArrayList<Musica> musicas;
	private String caminhoArquivo;
	
	
	
	public MusicaDAO(ArrayList<Musica> musicas, String caminhoArquivo) {
		musicas = new ArrayList<Musica>();
		this.caminhoArquivo = caminhoArquivo;
	}
	
	@Override
	public void carregar() {
		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
            	String[] partes = linha.split(",");
                String nome = partes[0];
                String caminho = partes[1];
                Musica aux;
                aux = new Musica(nome, caminho);
                musicas.add(aux);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	@Override
	public void adicionar(Object musica) {
		Musica musicaAdicionar = (Musica) musica;
		if (!musicas.contains(musicaAdicionar)) {
			musicas.add(musicaAdicionar);
	
	        try (FileWriter fw = new FileWriter(caminhoArquivo, true)){
	            String conteudo = musicaAdicionar.getNome() + "," + musicaAdicionar.getCaminhoArquivo();
	            
	            fw.write(conteudo);
	            fw.write(System.lineSeparator());
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }
	}
	@Override
	public void remover(Object musica) {
		Musica musicaRemover = (Musica) musica;
		
		if(musicas.contains(musicaRemover)) {
			musicas.remove(musicaRemover);
        	
        	try (FileWriter fw = new FileWriter(caminhoArquivo, false)){
        		
        		for (Musica u : musicas) {
                    String conteudo = u.getNome() + "," + u.getCaminhoArquivo();
                    
                    fw.write(conteudo);
                    fw.write(System.lineSeparator());
                }
        		
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	public ArrayList<Musica> getMusicas() {
		return musicas;
	}
	public void setMusicas(ArrayList<Musica> musicas) {
		this.musicas = musicas;
	}
	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}
	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}
	
	
}

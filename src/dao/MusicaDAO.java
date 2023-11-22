package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import modelo.Musica;

public class MusicaDAO {
	private ArrayList<Musica> musicas;
	private String caminhoArquivo;
	
	public MusicaDAO() {
		musicas = new ArrayList<Musica>();
		String diretorioAtual = System.getProperty("user.dir");
		this.caminhoArquivo = diretorioAtual + "/dados/musicas.txt";
		this.carregar();
	}
	
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
	public void adicionar(Musica musica) {
		if (!musicas.contains(musica)) {
			musicas.add(musica);		
	
	        try (FileWriter fw = new FileWriter(caminhoArquivo, true)){
	            String conteudo = musica.getNome() + "," + musica.getCaminhoArquivo();
	            
	            fw.write(conteudo);
	            fw.write(System.lineSeparator());
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }
	}
	public void remover(Musica musica) {
		if(musicas.contains(musica)) {
			musicas.remove(musica);
        	
        	try (FileWriter fw = new FileWriter(caminhoArquivo, false)){
        		
        		for (Musica m : musicas) {
                    String conteudo = m.getNome() + "," + m.getCaminhoArquivo();
                    
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

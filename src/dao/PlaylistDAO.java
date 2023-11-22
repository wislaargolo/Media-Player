package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import modelo.Musica;
import modelo.Playlist;
import modelo.UsuarioVIP;


public class PlaylistDAO  {
	private String caminhoArquivo;
	private ArrayList<Playlist> playlists;

	public PlaylistDAO(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
		playlists = new ArrayList<>();
	}

	public void carregar(UsuarioVIP usuario) {
		ArrayList<String> nomes = new ArrayList<>();
		ArrayList<String> caminhos = new ArrayList<>();
		// Le o arquivo com todas as playlists
		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
            	String[] partes = linha.split(",");
                nomes.add(partes[0]);
                caminhos.add(partes[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		// abre arquivo por arquivo e testa se pertence ao úsuario
		ArrayList<String> playlistAtual = new ArrayList<>();
		ArrayList<Musica> musicas = new ArrayList<>();
		for (int i = 0; i < nomes.size(); i++) {
			try (BufferedReader br = new BufferedReader(new FileReader(caminhos.get(i)))) {
	            String linha;

	            while ((linha = br.readLine()) != null) {
	                Stream<String> partes = Arrays.stream(linha.split(","));
		            partes.forEach(playlistAtual::add);
	            }
	            if (playlistAtual.get(0).equals(usuario.getNome()) && playlistAtual.get(1).equals(usuario.getId())) {
	            	for (int j = 3; j < playlistAtual.size(); j += 2) {
	            		Musica aux;
	                    aux = new Musica(playlistAtual.get(j), playlistAtual.get(j+1));
	                    musicas.add(aux);
	            	}
	            	Playlist playTemp;
	            	playTemp = new Playlist(playlistAtual.get(2), musicas);
	            	playlists.add(playTemp);
            	}
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
	}
	
	public void adicionar(Playlist playlist, UsuarioVIP usuario) {
		if (!playlists.contains(playlist) ) {
			playlists.add(playlist);
			
			String diretorioAtual = System.getProperty("user.dir");
			String caminho = diretorioAtual + "/src/dados/playlists/playlist_" + usuario.getId() + 
							"_" + playlist.getNome() + ".txt";
		
			// cria e escreve no arquivo inividual da playlist
			try (FileWriter fw = new FileWriter(caminho, false)){
				
				String conteudo = usuario.getNome() + "," + usuario.getId();
				fw.write(conteudo);
	            fw.write(System.lineSeparator());
	            
	            conteudo = playlist.getNome();	        
	            fw.write(conteudo);
	            fw.write(System.lineSeparator());
	            
	            for (Musica musica : playlist.getMusicas()) {
	            	conteudo = musica.getNome() + "," + musica.getCaminhoArquivo();
                    
                    fw.write(conteudo);
                    fw.write(System.lineSeparator());
	            }
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }		
			
			// escrever no arquivo geral
	        try (FileWriter fw = new FileWriter(caminhoArquivo, true)){
	            String conteudo = playlist.getNome() + "," + caminho;
	            
	            fw.write(conteudo);
	            fw.write(System.lineSeparator());
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }
		
	}
	
	public void remover(Playlist playlist, UsuarioVIP usuario) {
		if(playlists.contains(playlist)) {
			playlists.remove(playlist);
			
			String diretorioAtual = System.getProperty("user.dir");
			String caminho = diretorioAtual + "/src/dados/playlists/playlist_" + usuario.getId() + "_" + 
							playlist.getNome() + ".txt";
			
        	try {
        		File arquivo = new File(caminho);
        		if (arquivo.exists()) {
        			arquivo.delete();
        		}
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
        	ArrayList<String> nomes = new ArrayList<>();
    		ArrayList<String> caminhos = new ArrayList<>();
    		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
                String linha;

                while ((linha = br.readLine()) != null) {
                	String[] partes = linha.split(",");
                    nomes.add(partes[0]);
                    caminhos.add(partes[1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    		
    		try (FileWriter fw = new FileWriter(caminhoArquivo, false)){
        		    		
        		for (int i = 0; i < nomes.size(); i++) {
        			if (!(nomes.get(i).equals(playlist.getNome()))) {
	                    String conteudo = nomes.get(i) + "," + caminhos.get(i);
	                    
	                    fw.write(conteudo);
	                    fw.write(System.lineSeparator());
        			}
        		}
        		
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	

	public ArrayList<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(ArrayList<Playlist> playlists) {
		this.playlists = playlists;
	}
	
}

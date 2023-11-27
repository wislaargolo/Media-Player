package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import modelo.Diretorio;
import modelo.Musica;
import modelo.Usuario;

public class DiretorioDAO {
	private ArrayList<Diretorio> diretorios;
	private String caminhoArquivo;
	private MusicaDAO musicaDAO;

	public DiretorioDAO(MusicaDAO musicaDAO) {
		String diretorioAtual = System.getProperty("user.dir");
		this.caminhoArquivo = diretorioAtual + "/dados/diretorios.txt";
		diretorios = new ArrayList<Diretorio>();
		this.musicaDAO = musicaDAO;
		
	}

	public void carregar(Usuario usuario) {
		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(","); 
                String id = partes[0];
                String caminhoDiretorio = partes[1];

                if (usuario.getId().equals(id)) {
                    File diretorio = new File(caminhoDiretorio);
                    if (diretorio.isDirectory()) {
                        diretorios.add(new Diretorio(usuario, caminhoDiretorio));
                        carregarMusicas(diretorio); 
                    }
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	private void carregarMusicas(File diretorio) {
        File[] listaArquivos = diretorio.listFiles();
        if (listaArquivos != null) {
            for (File arquivo : listaArquivos) {
                if (arquivo.isFile() && arquivo.getName().toLowerCase().endsWith(".mp3")) {
                    musicaDAO.adicionar(new Musica(arquivo.getName(), arquivo.getAbsolutePath()));
                }
            }
        }
	}
	
	private void removerMusicas(File diretorio) {
        File[] listaArquivos = diretorio.listFiles();
        if (listaArquivos != null) {
            for (File arquivo : listaArquivos) {
                if (arquivo.isFile() && arquivo.getName().toLowerCase().endsWith(".mp3")) {
                    musicaDAO.remover(new Musica(arquivo.getName(), arquivo.getAbsolutePath()));
                }
            }
        }
	}

	public void adicionar(String caminhoDiretorio, Usuario usuario) {
		Diretorio novo = new Diretorio(usuario, caminhoDiretorio);
		
	    if (!diretorios.contains(novo)) {
	        File diretorio = new File(caminhoDiretorio);
	        if (diretorio.isDirectory()) {
	            diretorios.add(novo); 
	            carregarMusicas(diretorio); 

	            try (FileWriter fw = new FileWriter(caminhoArquivo, true)) {
	            	String linhaAdicionar = usuario.getId() + "," + caminhoDiretorio;
	                fw.write(linhaAdicionar);
	                fw.write(System.lineSeparator());
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
        	
		
	}
	
	public void remover(String caminhoDiretorio, Usuario usuario) {
		Diretorio remover = new Diretorio(usuario, caminhoDiretorio);

	    if (!diretorios.contains(remover)) {
	        File diretorio = new File(caminhoDiretorio);
	        if (diretorio.isDirectory()) {
	            diretorios.remove(remover); 
	            removerMusicas(diretorio); 

	            try (FileWriter fw = new FileWriter(caminhoArquivo, false)) {
	            	for (Diretorio d : diretorios) {
	            		String linhaAdicionar = d.getDono().getId() + "," + caminhoDiretorio;
						fw.write(linhaAdicionar);
						fw.write(System.lineSeparator());
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
        	
		
	}	


	public ArrayList<Diretorio> getDiretorios() {
		return diretorios;
	}

	public void setDiretorios(ArrayList<Diretorio> diretorios) {
		this.diretorios = diretorios;
	}

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}


}

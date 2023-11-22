package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import modelo.Musica;

public class DiretorioDAO {
	private ArrayList<String> diretorios;
	private String caminhoArquivo;
	private MusicaDAO musicaDAO;

	public DiretorioDAO(MusicaDAO musicaDAO) {
		String diretorioAtual = System.getProperty("user.dir");
		this.caminhoArquivo = diretorioAtual + "/dados/diretorios.txt";
		diretorios = new ArrayList<String>();
		this.musicaDAO = musicaDAO;
		this.carregar();
		
	}

	public void carregar() {
		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
            	File diretorio = new File(linha);
            	if(diretorio.isDirectory()) {
	            	diretorios.add(linha);
	            	carregarMusicas(diretorio);
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

	public void adicionar(String caminhoDiretorio) {
        if (!diretorios.contains(caminhoDiretorio)) {
        	File diretorio = new File(caminhoDiretorio);
        	if(diretorio.isDirectory()) {
            	diretorios.add(caminhoDiretorio);
            	carregarMusicas(diretorio);
	
		        try (FileWriter fw = new FileWriter(caminhoArquivo, true)){
		            fw.write(caminhoDiretorio);
		            fw.write(System.lineSeparator());
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
        	}
        }
        	
		
	}

//	public void remover(String diretorio) {
//        if(diretorios.contains(diretorio)) {
//        	diretorios.remove(diretorio);
//        	
//        	try (FileWriter fw = new FileWriter(caminhoArquivo, false)){
//        		
//        		for (String d : diretorios) {
//					fw.write(d);
//					fw.write(System.lineSeparator());
//                }
//        		
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
		


	public ArrayList<String> getDiretorios() {
		return diretorios;
	}

	public void setDiretorios(ArrayList<String> diretorios) {
		this.diretorios = diretorios;
	}

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}

	public MusicaDAO getMusicaDAO() {
		return musicaDAO;
	}

	public void setMusicaDAO(MusicaDAO musicaDAO) {
		this.musicaDAO = musicaDAO;
	}


}

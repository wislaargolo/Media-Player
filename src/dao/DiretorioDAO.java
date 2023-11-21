package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DiretorioDAO {
	private ArrayList<String> diretorios;
	private String caminhoArquivo;

	public DiretorioDAO(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
		diretorios = new ArrayList<String>();
	}

	public void carregar() {
		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
            	diretorios.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	public void adicionar(String diretorio) {
        if (!diretorios.contains(diretorio)) {
        	diretorios.add(diretorio);
	
	        try (FileWriter fw = new FileWriter(caminhoArquivo, true)){
	            fw.write(diretorio);
	            fw.write(System.lineSeparator());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }
		
	}

	public void remover(String diretorio) {
        if(diretorios.contains(diretorio)) {
        	diretorios.remove(diretorio);
        	
        	try (FileWriter fw = new FileWriter(caminhoArquivo, false)){
        		
        		for (String d : diretorios) {
					fw.write(d);
					fw.write(System.lineSeparator());
                }
        		
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		


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


}

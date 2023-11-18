package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import modelo.Usuario;
import modelo.UsuarioComum;
import modelo.UsuarioVIP;

public class DiretorioDAO implements ArquivoDAO{
	private ArrayList<String> pastas;
	private String caminhoArquivo;

	public DiretorioDAO(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
		pastas = new ArrayList<String>();
	}

	@Override
	public void carregar() {
		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                pastas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void adicionar(Object pasta) {
		String pastaAdicionar = (String) pasta;
        
        if (!pastas.contains(pastaAdicionar)) {
	        pastas.add(pastaAdicionar);
	
	        try (FileWriter fw = new FileWriter(caminhoArquivo, true)){
	            fw.write(pastaAdicionar);
	            fw.write(System.lineSeparator());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }
		
	}

	@Override
	public void remover(Object pasta) {
		String pastaAdicionar = (String) pasta;
		
        if(pastas.contains(pastaAdicionar)) {
        	pastas.remove(pastaAdicionar);
        	
        	try (FileWriter fw = new FileWriter(caminhoArquivo, false)){
        		
        		for (String p : pastas) {
					fw.write(p);
					fw.write(System.lineSeparator());
                }
        		
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		


	public ArrayList<String> getPastas() {
		return pastas;
	}

	public void setPastas(ArrayList<String> pastas) {
		this.pastas = pastas;
	}

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}


}

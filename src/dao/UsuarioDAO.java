package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import modelo.Usuario;
import modelo.UsuarioComum;
import modelo.UsuarioVIP;

public class UsuarioDAO implements ArquivoDAO{

	private ArrayList<Usuario> usuarios;
	private String caminhoArquivo;

	public UsuarioDAO(String caminhoArquivo) {
		usuarios = new ArrayList<Usuario>();
		this.caminhoArquivo = caminhoArquivo;
	}

	@Override
	public void carregar() {
	
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))){	
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 4) {
                    String nome = partes[0];
                    String id = partes[1];
                    String senha = partes[2];
                    Usuario aux;
                    if (partes[3].equalsIgnoreCase("VIP")) {
                        aux = new UsuarioVIP(id, nome, senha);
                    } else {
                        aux = new UsuarioComum(id, nome, senha);
                    }
                    usuarios.add(aux);
                   
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	@Override
    public void adicionar(Object usuario) {
        Usuario usuarioAdicionar = (Usuario) usuario;
        
        if (!usuarios.contains(usuarioAdicionar)) {
	        usuarios.add(usuarioAdicionar);
	
	        try (FileWriter fw = new FileWriter(caminhoArquivo, true)){
	            String conteudo = usuarioAdicionar.getNome() + "," + usuarioAdicionar.getId() + "," 
	            						   + usuarioAdicionar.getSenha() + ",";
	            
	            if(usuario instanceof UsuarioComum) {
	            	conteudo = conteudo + "COMUM";
	            } else {
	            	conteudo = conteudo + "VIP";
	            }
	            
	            fw.write(conteudo);
	            fw.write(System.lineSeparator());
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }
        
    }
	
	@Override
    public void remover(Object usuario) {
		Usuario usuarioRemover = (Usuario) usuario;
		
        if(usuarios.contains(usuarioRemover)) {
        	usuarios.remove(usuarioRemover);
        	
        	try (FileWriter fw = new FileWriter(caminhoArquivo, false)){
        		
        		for (Usuario u : usuarios) {
                    String conteudo = u.getNome() + "," + u.getId() + "," + u.getSenha() + ",";
                    
                    if(usuario instanceof UsuarioComum) {
    	            	conteudo = conteudo + "COMUM";
    	            } else {
    	            	conteudo = conteudo + "VIP";
    	            }
                    
                    fw.write(conteudo);
                    fw.write(System.lineSeparator());
                }
        		
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}
	
	

}

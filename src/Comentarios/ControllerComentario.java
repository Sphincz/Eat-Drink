/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Comentarios;

import Suporte.TipoComentario;

import javax.swing.JOptionPane;

/**
 *
 * @author Nuno
 */
public class ControllerComentario {
    
    private ComentarioPrato comentPrato = new ComentarioPrato();
    private ComentarioEstabelecimento comentEstabelecimento = new ComentarioEstabelecimento();
    
    public boolean save(TipoComentario tipo, String e, String p, String user, String comentario, int nota) {
        if(tipo.equals(TipoComentario.ESTABELECIMENTO)){
        	System.out.println("passou");
            return comentEstabelecimento.save(e, user, comentario, nota);
        }else{
            return comentPrato.save(p, user, comentario, nota);
        }
    }

    public boolean delete(TipoComentario tipo, String prato, String estabelecimento, String user, String coment, int nota) {
        boolean encontrou=false;
        if(tipo.equals(TipoComentario.ESTABELECIMENTO)){
        	encontrou = comentEstabelecimento.find(estabelecimento, user, coment, nota);
        	System.out.println("estb");
        }
        else{
        	encontrou = comentPrato.find(prato, user, coment, nota);
        	System.out.println("prato");
        }
        if(encontrou && tipo.equals(TipoComentario.ESTABELECIMENTO)){
        	System.out.println("destoru etst");
        	comentEstabelecimento.destroy(estabelecimento, user, coment, nota);
            return true;
        }else{
            if(encontrou && tipo.equals(TipoComentario.PRATO)){
            	System.out.println("prato destory");
            	comentPrato.destroy(prato, user, coment, nota);
                return true;
            }
        }
        return false;
        
        
    }
    
    
}

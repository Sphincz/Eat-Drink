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
    
    public boolean save(TipoComentario tipo, int id, String comentario, int nota) {
        
        if(tipo.equals(TipoComentario.ESTABELECIMENTO)){
            return comentEstabelecimento.save(id, comentario, nota);
        }else{
            return comentPrato.save(id, comentario, nota);
        }
    }

    public boolean delete(int idComent) {
        boolean encontrouComentP = comentPrato.find(idComent);
        boolean encontrouComentE = comentEstabelecimento.find(idComent);
        if(encontrouComentP){
            comentPrato.destroy(idComent);
            JOptionPane.showMessageDialog(null, "Comentário Eliminado");
            return true;
        }else{
            if(encontrouComentE){
                comentEstabelecimento.destroy(idComent);
                JOptionPane.showMessageDialog(null, "Comentário Eliminado");
                return true;
            }
        }
        return false;
        
        
    }
    
    
}

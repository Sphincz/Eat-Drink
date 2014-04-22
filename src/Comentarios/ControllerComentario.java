/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Comentarios;

import Suporte.TipoComentario;

/**
 *
 * @author Nuno
 */
public class ControllerComentario {

    ComentarioPrato comentPrato = new ComentarioPrato();
    ComentarioEstabelecimento comentEstabelecimento = new ComentarioEstabelecimento();

    
    public boolean save(TipoComentario tipo, int id, String comentario, int nota) {
        if(tipo.equals(TipoComentario.ESTABELECIMENTO)){
            return comentEstabelecimento.save(id, comentario, nota);
        }else{
            return comentPrato.save(id, comentario, nota);
        }
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Comentarios;

import BaseDados.DBConnector;

/**
 *
 * @author Nuno
 */
public class ComentarioPrato {

    boolean save(int id, String comentario, int nota) {
        DBConnector db = new DBConnector();
        return db.inserirComentarioPrato(id, comentario, nota);
    }
    
}

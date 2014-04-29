/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Comentarios;

import BaseDados.DBConnector;
import Pratos.Prato;
import java.util.ArrayList;

/**
 *
 * @author Nuno
 */
public class ComentarioPrato {

    public boolean save(int id, String comentario, int nota) {
        DBConnector db = new DBConnector();
        return db.inserirComentarioPrato(id, comentario, nota);
    }

    public boolean find(int idComent) {
        DBConnector db = new DBConnector();
        return db.findComent(idComent);
    }

    public void destroy(int idComent) {
        DBConnector db = new DBConnector();
        db.destroyComent(idComent);
    }

    public ArrayList<ComentarioPrato> findAll(int userID, ArrayList<Prato> listaPratos, int avaliacao, boolean fotografia, String comentario) {
        DBConnector db = new DBConnector();
        db.findAllComents(userID, listaPratos, avaliacao, fotografia, comentario);
        return null;
    }

    
}

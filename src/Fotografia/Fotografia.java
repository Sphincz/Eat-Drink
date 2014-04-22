/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fotografia;

import BaseDados.DBConnector;
import java.io.File;

/**
 *
 * @author Nuno
 */
public class Fotografia {
    private File foto;
    private int id;
    
    public Fotografia(int id, File foto) {
        this.foto=foto;
        this.id=id;
    }

    public Fotografia() {
        
    }

    public void save() {
        DBConnector db = new DBConnector();
        db.saveFoto(id, foto);
    }

    public boolean find(int idFoto) {
        DBConnector db = new DBConnector();
        boolean encontrou = db.findFoto(idFoto);
        return encontrou;
    }

    public void destroy(int idFoto) {
        DBConnector db = new DBConnector();
        db.destroyFoto(idFoto);
    }
    
}

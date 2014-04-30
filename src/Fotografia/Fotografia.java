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
	private String coment;
	private String email;


    public Fotografia(String email, String coment, File file) {
    	this.foto=foto;
        this.coment=coment;
        this.email=email;
    }

    public Fotografia() {
		// TODO Auto-generated constructor stub
	}

	public void save() {
        DBConnector db = new DBConnector();
        db.saveFoto(email, coment, foto);
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

    public void save(File file) {
        DBConnector db = new DBConnector();
        int id = db.saveFoto(file);
    }
    
}

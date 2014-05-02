/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fotografia;

import BaseDados.DBConnector;
import Controller.ControllerPesquisa;

import java.io.File;

/**
 *
 * @author Nuno
 */
public class Fotografia {
    private File foto;
	private String coment;
	private String email;
	private int idFoto;
	private String estabelecimento;
	private String prato;
	private ControllerPesquisa controller;


    public Fotografia(ControllerPesquisa controller, String estabelecimento, String prato, String email, String coment, File file) {
    	this.foto=file;
        this.coment=coment;
        this.email=email;
        idFoto=0;
        this.estabelecimento=estabelecimento;
        this.prato=prato;
        this.controller=controller;
    }

	public Fotografia() {
		// TODO Auto-generated constructor stub
	}

	public String getEstabelecimento() {
		return estabelecimento;
	}

	public String getPrato() {
		return prato;
	}

	public void save() {
        DBConnector db = new DBConnector();
        db.saveFoto(controller, estabelecimento, prato, email, coment, foto);
    }

    public boolean find(String email, String comentario, String estabelecimento, String prato) {
        DBConnector db = new DBConnector();
        boolean encontrou = db.findFoto(this, email, comentario, estabelecimento, prato);
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

	public void setId(int id) {
		idFoto=id;
		
	}

	public int getId() {
		return idFoto;
	}
    
}

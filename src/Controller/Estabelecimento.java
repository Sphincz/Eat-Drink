/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BaseDados.DBConnector;
import java.util.ArrayList;

/**
 *
 * @author Nuno
 */
public class Estabelecimento {
    private int id;
    private String nomeUser;
    private String designacao;
    private ControllerPesquisa controller;
    private String rating;
    
    public Estabelecimento(ControllerPesquisa controller, int id, String nomeUser, String designacao, String rating){
        this.nomeUser=nomeUser;
        this.controller=controller;
        this.rating=rating;
        this.designacao=designacao;
        this.id=id;
        controller.getEstabelecimentos().add(this);
    }
    
    public Estabelecimento(int id, String nomeUser, String designacao, String rating){
        this.nomeUser=nomeUser;
        this.rating=rating;
        this.designacao=designacao;
        this.id=id;
    }

    public Estabelecimento() {
        
    }

    public int getId() {
        return id;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public String getDesignacao() {
        return designacao;
    }
    
    

    
    public void findAll(ControllerPesquisa controller, String user, String estabelecimento, String prato, int avaliacao, boolean fotografia, String comentario){
        this.controller=controller;
        DBConnector db = new DBConnector();
        db.findEstabelecimentos(controller, user, estabelecimento, prato, avaliacao, fotografia, comentario);
    }

	public ArrayList<Estabelecimento> findAllForStart() {
		DBConnector db = new DBConnector();
        return db.findAllE();
	}
    
}

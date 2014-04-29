/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Comentarios;

import BaseDados.DBConnector;
import Controller.ControllerPesquisa;
import Controller.Estabelecimento;

import java.util.ArrayList;

/**
 *
 * @author Nuno
 */
public class ComentarioEstabelecimento {
    
    private String userID;
    private String comentario;
    private int idEstabelecimento;
    
    private ControllerPesquisa controller;
	private String nota;

    public int getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(int idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

	public ComentarioEstabelecimento(ControllerPesquisa controller, int idEstabelecimento, String userID, String comentario, String nota) {
        this.userID = userID;
        this.nota=nota;
        this.comentario = comentario;
        this.idEstabelecimento=idEstabelecimento;
        this.controller=controller;
        controller.getComentariosEstabelecimento().add(this);
    }

    public ComentarioEstabelecimento() {
        
    }


    public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public ControllerPesquisa getController() {
		return controller;
	}

	public void setController(ControllerPesquisa controller) {
		this.controller = controller;
	}

	public boolean save(int id, String comentario, int nota) {
        DBConnector db = new DBConnector();
        return db.inserirComentarioEstabelecimento(id, comentario, nota);
        
    }

    public boolean find(int idComent) {
        DBConnector db = new DBConnector();
        return db.findComent(idComent);
    }

    public void destroy(int idComent) {
        DBConnector db = new DBConnector();
        db.destroyComent(idComent);
    }

    public void findAll(ControllerPesquisa controller, String userID, ArrayList<Estabelecimento> listaEstabelecimentos, int avaliacao, boolean fotografia, String comentario) {
        this.controller=controller;
        DBConnector db = new DBConnector();
        db.findComentarios(controller, userID, listaEstabelecimentos, avaliacao, fotografia, comentario);
    }

	public String getNota() {
		return nota;
	}
    
}

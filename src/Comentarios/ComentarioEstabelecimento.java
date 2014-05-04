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
    
    public int getIdFoto() {
		return idFoto;
	}

	private String userID;
    private String comentario;
    private int idEstabelecimento;
    
    private ControllerPesquisa controller;
	private String nota;
	private int idFoto;

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
        this.idFoto=0;
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

	public boolean save(String e, String user, String comentario, int nota) {
        DBConnector db = new DBConnector();
        return db.inserirComentarioEstabelecimento(e, user, comentario, nota);
        
    }

    public boolean find(String estabelecimento, String user, String coment, int nota) {
        DBConnector db = new DBConnector();
        return db.findComentE(estabelecimento, user, coment, nota);
    }

    public void destroy(String estabelecimento, String user, String coment, int nota) {
        DBConnector db = new DBConnector();
        db.destroyComentE(estabelecimento, user, coment, nota);
    }

    public void findAll(ControllerPesquisa controller, String userID, ArrayList<Estabelecimento> listaEstabelecimentos, String estabelecimento, int avaliacao, boolean fotografia, String comentario) {
        this.controller=controller;
        DBConnector db = new DBConnector();
        db.findComentarios(controller, userID, estabelecimento, listaEstabelecimentos, avaliacao, fotografia, comentario);
    }

	public String getNota() {
		return nota;
	}

	public static ArrayList<ComentarioEstabelecimento> checkForDuplicated(ArrayList<ComentarioEstabelecimento> listaComentariosEstabelecimento) {
		for (int i = 1; i < listaComentariosEstabelecimento.size(); i++) {
			if(listaComentariosEstabelecimento.get(i).getComentario().equals(listaComentariosEstabelecimento.get(i-1).getComentario()) && listaComentariosEstabelecimento.get(i).getIdEstabelecimento()==listaComentariosEstabelecimento.get(i-1).getIdEstabelecimento() && listaComentariosEstabelecimento.get(i).getUserID().equals(listaComentariosEstabelecimento.get(i-1).getUserID())){
				listaComentariosEstabelecimento.remove(i);
				i--;
			}
		}
		return listaComentariosEstabelecimento;
	}

	public void setFotografiaID(int idFoto) {
		this.idFoto=idFoto;
		
	}

	public void findFotos(ControllerPesquisa controllerPesquisa,
			ArrayList<ComentarioEstabelecimento> listaComentariosEstabelecimento, ArrayList<Estabelecimento> listaEstabelecimentos) {
		this.controller=controller;
        DBConnector db = new DBConnector();
        db.findFotoComents(this, listaComentariosEstabelecimento, listaEstabelecimentos);
		
	}

	public static String getFotoForComent(String email, String estabelecimento,
			String comentario) {
		DBConnector db = new DBConnector();
        return db.findFotoForComentEstabelecimento(email, estabelecimento, comentario);
	}
    
}

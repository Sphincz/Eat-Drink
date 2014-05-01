/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Comentarios;

import BaseDados.DBConnector;
import Controller.ControllerPesquisa;
import Pratos.Prato;

import java.util.ArrayList;

/**
 *
 * @author Nuno
 */
public class ComentarioPrato {

    private int id;
	private String email;
	private String comentario;
	private String nota;
	
	public ComentarioPrato(){
		
	}

	public ComentarioPrato(int id, String email, String comentario,
			String nota) {
		this.id=id;
		this.email=email;
		this.comentario=comentario;
		this.nota=nota;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getComentario() {
		return comentario;
	}

	public String getNota() {
		return nota;
	}

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

    public ArrayList<ComentarioPrato> findAll(ControllerPesquisa controller,String username, String prato, String estabelecimento, int avaliacao, boolean fotografia, String comentario) {
        DBConnector db = new DBConnector();
        return db.findAllComents(controller, username, prato, estabelecimento, avaliacao, fotografia, comentario);
    }

	public static ArrayList<ComentarioPrato> checkForDuplicated(ArrayList<ComentarioPrato> listComentariosPrato) {
		for (int i = 1; i < listComentariosPrato.size(); i++) {
			if(listComentariosPrato.get(i).getComentario().equals(listComentariosPrato.get(i-1).getComentario()) && listComentariosPrato.get(i).getId()==listComentariosPrato.get(i-1).getId() && listComentariosPrato.get(i).getEmail().equals(listComentariosPrato.get(i-1).getEmail())){
				listComentariosPrato.remove(i);
				i--;
			}
		}
		return listComentariosPrato;
	}

    
}

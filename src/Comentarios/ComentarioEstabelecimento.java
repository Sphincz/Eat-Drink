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
    
    private int id;
    private int userID;
    private String comentario;
    
    private ControllerPesquisa controller;

    public ComentarioEstabelecimento(int id, int userID, String comentario) {
        this.id = id;
        this.userID = userID;
        this.comentario = comentario;
        controller.getComentariosEstabelecimento().add(this);
    }

    public ComentarioEstabelecimento() {
        
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

    public void findAll(ControllerPesquisa controller, int userID, ArrayList<Estabelecimento> listaEstabelecimentos, int avaliacao, boolean fotografia, String comentario) {
        this.controller=controller;
        DBConnector db = new DBConnector();
        db.findComentarios(userID, listaEstabelecimentos, avaliacao, fotografia, comentario);
    }
    
}

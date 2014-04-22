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
    private String nome;
    private String prato;
    private boolean fotografia;
    private ControllerPesquisa controller;
    
    public Estabelecimento(int id, String nome, String prato, boolean fotografia){
        this.nome=nome;
        this.prato=prato;
        this.fotografia=fotografia;
        this.id=id;
        controller.getEstabelecimentos().add(this);
    }

    public Estabelecimento() {
        
    }

    public boolean isFotografia() {
        return fotografia;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPrato() {
        return prato;
    }
    
    

    
    public void findAll(ControllerPesquisa controller, String user, String estabelecimento, String prato, int avaliacao, boolean fotografia, String comentario){
        this.controller=controller;
        DBConnector db = new DBConnector();
        db.findEstabelecimentos(user, estabelecimento, prato, avaliacao, fotografia, comentario);
    }
    
}

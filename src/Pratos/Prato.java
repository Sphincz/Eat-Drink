/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pratos;

import BaseDados.DBConnector;
import Controller.ControllerPesquisa;
import java.util.ArrayList;

/**
 *
 * @author Nuno
 */
public class Prato {
	
	private int id;
	private String descricao;
	private String preco;
	private int tipoPrato;
	private String rating;
	

    public int getId() {
		return id;
	}


	public String getDescricao() {
		return descricao;
	}


	public String getPreco() {
		return preco;
	}


	public int getTipoPrato() {
		return tipoPrato;
	}


	public String getRating() {
		return rating;
	}
	
	public Prato(){
		
	}


	public ArrayList<Prato> findAll(String estabelecimento, String prato, boolean fotografia) {
        DBConnector db = new DBConnector();
        ArrayList<Prato> pratos = db.findPratos(estabelecimento, prato, fotografia);
        return pratos;
    }
	
	public Prato(int id, String descricao, String preco, int tipoPrato, String rating){
		this.id=id;
		this.descricao=descricao;
		this.preco=preco;
		this.tipoPrato=tipoPrato;
		this.rating=rating;
	}
    
}

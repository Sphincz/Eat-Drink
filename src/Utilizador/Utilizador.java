/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilizador;

import java.util.ArrayList;

import BaseDados.DBConnector;

/**
 *
 * @author Nuno
 */
public class Utilizador {
    private String email;
    private int idFotografia;
    private String nome;
    private String escola;
    
    
    
	public Utilizador(String email, int idFotografia, String nome, String escola) {
		this.email = email;
		this.idFotografia = idFotografia;
		this.nome = nome;
		this.escola = escola;
	}
	public Utilizador() {
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return email;
	}
	public int getIdFotografia() {
		return idFotografia;
	}
	public String getNome() {
		return nome;
	}
	public String getEscola() {
		return escola;
	}
	public ArrayList<Utilizador> getAll() {
		DBConnector db = new DBConnector();
		return db.findAllUsers();
	}
    
    
}

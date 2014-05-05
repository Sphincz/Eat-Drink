package Utilizador;

import java.util.ArrayList;

import BaseDados.DBConnector;

/**
 * Classe Utilizador.
 * Esta classe representa um utilizador.
 *
 * @author Nuno Coelho, Antonio Raimundo, Jose Serro, Diogo Peres
 */
public class Utilizador {
    
    /** o email. */
    private String email;
    
    /** o id de fotografia. */
    private int idFotografia;
    
    /** o nome. */
    private String nome;
    
    /** a escola. */
    private String escola;
    
    
    
	/**
	 * Instancia um novo utilizador.
	 *
	 * @param email o email
	 * @param idFotografia o id de fotografia
	 * @param nome o nome
	 * @param escola a escola
	 */
	public Utilizador(String email, int idFotografia, String nome, String escola) {
		this.email = email;
		this.idFotografia = idFotografia;
		this.nome = nome;
		this.escola = escola;
	}
	
	/**
	 * Construtor vazio para aceder aos metodos sem a necessidade de passar argumentos.
	 */
	public Utilizador() {}
	
	/**
	 * Retorna o email do utilizador.
	 *
	 * @return o email do utilizador
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Retorna o id da fotografia.
	 *
	 * @return o id da fotografia
	 */
	public int getIdFotografia() {
		return idFotografia;
	}
	
	/**
	 * Retorna o nome.
	 *
	 * @return o nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Retorna a escola.
	 *
	 * @return a escola
	 */
	public String getEscola() {
		return escola;
	}
	
	/**
	 * Retorna a lista de todos os utilizadores.
	 * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
	 *
	 * @return the all
	 */
	public ArrayList<Utilizador> getAll() {
		DBConnector db = new DBConnector();
		return db.findAllUsers();
	}
}

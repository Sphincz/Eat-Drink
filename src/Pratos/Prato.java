package Pratos;

import BaseDados.DBConnector;

import java.util.ArrayList;

/**
 * Classe Prato.
 * Esta classe representa um prato.
 *
 * @author Nuno Coelho, Antonio Raimundo, Jose Serro, Diogo Peres
 */
public class Prato {
	
	/** o id. */
	private int id;
	
	/** a descricao. */
	private String descricao;
	
	/** o preco. */
	private String preco;
	
	/** o tipo de prato. */
	private int tipoPrato;
	
	/** a avaliacao. */
	private String rating;
	
	/** o id do estabelecimento. */
	@SuppressWarnings("unused")
	private String idEstabelecimento;
	

    /**
     * Retorna o id do prato.
     *
     * @return o id do prato
     */
    public int getId() {
		return id;
	}


	/**
	 * Retorna a descricao do prato.
	 *
	 * @return a descricao do prato
	 */
	public String getDescricao() {
		return descricao;
	}


	/**
	 * Retorna o preco do prato.
	 *
	 * @return o preco do prato
	 */
	public String getPreco() {
		return preco;
	}


	/**
	 * Gets the tipo prato.
	 *
	 * @return the tipo prato
	 */
	public int getTipoPrato() {
		return tipoPrato;
	}


	/**
	 * Retorna a avaliacao do prato.
	 *
	 * @return a avaliacao do prato
	 */
	public String getRating() {
		return rating;
	}
	
	/**
	 * Construtor vazio para aceder aos metodos sem necessitar de argumentos.
	 */
	public Prato(){
		
	}


	/**
	 * Procura todos os pratos.
	 *
	 * @param estabelecimento o estabelecimento
	 * @param prato o prato
	 * @param fotografia se tem fotografia
	 * @return a lista de todos os pratos
	 */
	public ArrayList<Prato> findAll(String estabelecimento, String prato, boolean fotografia) {
        DBConnector db = new DBConnector();
        ArrayList<Prato> pratos = db.findPratos(estabelecimento, prato, fotografia);
        return pratos;
    }
	
	/**
	 * Instancia um novo prato.
	 *
	 * @param id o id
	 * @param descricao a descricao
	 * @param preco o preco
	 * @param tipoPrato o tipo de prato
	 * @param rating a avaliacao
	 */
	public Prato(int id, String descricao, String preco, int tipoPrato, String rating){
		this.id=id;
		this.descricao=descricao;
		this.preco=preco;
		this.tipoPrato=tipoPrato;
		this.rating=rating;
	}


	/**
	 * Procura todos os pratos.
	 * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
	 *
	 * @return a lista de todos os pratos
	 */
	public ArrayList<Prato> findAllForStart() {
		DBConnector db = new DBConnector();
        return db.findAllPratos();
	}


	/**
	 * Modifica o ID do estabelecimento.
	 *
	 * @param id o novo ID do estabelecimento
	 */
	public void setIDEstabelecimento(String id) {
		this.idEstabelecimento=id;
	}
}

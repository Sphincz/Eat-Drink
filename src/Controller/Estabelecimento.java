package Controller;

import BaseDados.DBConnector;

import java.util.ArrayList;

/**
 * Classe Estabelecimento.
 * Esta classe representa um estabelecimento.
 *
 * @author Nuno Coelho, Antonio Raimundo, Jose Serro, Diogo Peres
 */
public class Estabelecimento {
    
    /** o id do estabelecimento. */
    private int id;
    
    /** o nome do user que sugestionou o estabelecimento. */
    private String nomeUser;
    
    /** a designacao. */
    private String designacao;
    
    /** o controlador de pesquisa. */
    @SuppressWarnings("unused")
	private ControllerPesquisa controller;
    
    /** a avaliacao. */
    @SuppressWarnings("unused")
	private String rating;
    
    /**
     * Instancia um novo estabelecimento e adiciona este estabelecimento a lista de estabelecimentos.
     *
     * @param controller o controlador de pesquisa
     * @param id o id do estabelecimento
     * @param nomeUser o nome do user que sugestionou
     * @param designacao a designacao
     * @param rating a avaliacao
     */
    public Estabelecimento(ControllerPesquisa controller, int id, String nomeUser, String designacao, String rating){
        this.nomeUser=nomeUser;
        this.controller=controller;
        this.rating=rating;
        this.designacao=designacao;
        this.id=id;
        controller.getEstabelecimentos().add(this);
    }
    
    /**
     * Instancia um novo estabelecimento.
     *
     * @param id o id do estabelecimento
     * @param nomeUser o nome do user sugestionou
     * @param designacao the designacao
     * @param rating the rating
     */
    public Estabelecimento(int id, String nomeUser, String designacao, String rating){
        this.nomeUser=nomeUser;
        this.rating=rating;
        this.designacao=designacao;
        this.id=id;
    }

    /**
     * Construtor vazio para aceder aos metodos sem necessitar de passar argumentos.
     */
    public Estabelecimento() {
        
    }

    /**
     * Retorna o id do estabelecimento.
     *
     * @return o id do estabelecimento
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna o nome do user que sugestionou o estabelecimento.
     *
     * @return o nome do user que sugestionou o estabelecimento
     */
    public String getNomeUser() {
        return nomeUser;
    }

    /**
     * Retorna a designacao do estabelecimento.
     *
     * @return a designacao do estabelecimento
     */
    public String getDesignacao() {
        return designacao;
    }
    
    /**
     * Procura todos os estabelecimentos e pratos.
     * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
     *
     * @param controller the controller
     * @param user the user
     * @param estabelecimento the estabelecimento
     * @param prato the prato
     * @param avaliacao the avaliacao
     * @param fotografia the fotografia
     * @param comentario the comentario
     */
    public void findAll(ControllerPesquisa controller, String user, String estabelecimento, String prato, int avaliacao, boolean fotografia, String comentario){
        this.controller=controller;
        DBConnector db = new DBConnector();
        db.findEstabelecimentos(controller, user, estabelecimento, prato, avaliacao, fotografia, comentario);
    }

	/**
	 * Procura todos os estabelecimentos.
	 * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
	 *
	 * @return the array list
	 */
	public ArrayList<Estabelecimento> findAllForStart() {
		DBConnector db = new DBConnector();
        return db.findAllE();
	}   
}

package Fotografia;

import BaseDados.DBConnector;
import Controller.ControllerPesquisa;

import java.io.File;

/**
 * Classe Fotografia.
 * Esta classe representa uma fotografia a um estabelecimento/prato.
 *
 * @author Nuno Coelho, Antonio Raimundo, Jose Serro, Diogo Peres
 */
public class Fotografia {
    
    /** o ficheiro de fotografia. */
    private File foto;
	
	/** o comentario. */
	private String coment;
	
	/** o email do user. */
	private String email;
	
	/** o id da fotografia. */
	private int idFoto;
	
	/** o estabelecimento. */
	private String estabelecimento;
	
	/** o prato. */
	private String prato;
	
	/** o controlador. */
	private ControllerPesquisa controller;


    /**
     * Instancia uma nova fotografia.
     * Coloca o id da fotografia a 0.
     *
     * @param controller o controlador de pesquisa
     * @param estabelecimento o estabelecimento
     * @param prato o prato
     * @param email o email
     * @param coment o comentario
     * @param file o ficheiro de fotografia
     */
    public Fotografia(ControllerPesquisa controller, String estabelecimento, String prato, String email, String coment, File file) {
    	this.foto=file;
        this.coment=coment;
        this.email=email;
        idFoto=0;
        this.estabelecimento=estabelecimento;
        this.prato=prato;
        this.controller=controller;
    }

	/**
	 * Construtor vazio para acesso aos metodos sem a necessidade de passar argumentos
	 */
	public Fotografia() {
	}

	/**
	 * Retorna o estabelecimento.
	 *
	 * @return o estabelecimento
	 */
	public String getEstabelecimento() {
		return estabelecimento;
	}

	/**
	 * Retorna o prato.
	 *
	 * @return o prato
	 */
	public String getPrato() {
		return prato;
	}

	/**
	 * Insere uma fotografia.
	 * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
	 */
	public void save() {
        DBConnector db = new DBConnector();
        db.saveFoto(controller, estabelecimento, prato, email, coment, foto);
    }

    /**
     * Procura uma fotografia.
     * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
     *
     * @param email the email
     * @param comentario the comentario
     * @param estabelecimento the estabelecimento
     * @param prato the prato
     * @return true, if successful
     */
    public boolean find(String email, String comentario, String estabelecimento, String prato) {
        DBConnector db = new DBConnector();
        boolean encontrou = db.findFoto(this, email, comentario, estabelecimento, prato);
        return encontrou;
    }

    /**
     * Elimina uma fotografia.
     * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
     *
     * @param idFoto o id da fotografia
     */
    public void destroy(int idFoto) {
        DBConnector db = new DBConnector();
        db.destroyFoto(idFoto);
    }

    /**
     * Insere uma fotografia.
     * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
     *
     * @param file o ficheiro da fotografia
     * */
    public void save(File file) {
        DBConnector db = new DBConnector();
        @SuppressWarnings("unused")
		int id = db.saveFoto(file);
    }

	/**
	 * Modifica o id da fotografia.
	 *
	 * @param id o novo id da fotografia
	 */
	public void setId(int id) {
		idFoto=id;
	}

	/**
	 * Retorna o id da fotografia.
	 *
	 * @return o id da fotografia
	 */
	public int getId() {
		return idFoto;
	}
    
}

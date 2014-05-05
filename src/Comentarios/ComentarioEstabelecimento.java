package Comentarios;

import BaseDados.DBConnector;
import Controller.ControllerPesquisa;
import Controller.Estabelecimento;

import java.util.ArrayList;

/**
 * Classe ComentarioEstabelecimento.
 * Esta classe representa um comentario ao estabelecimento.
 *
 * @author Nuno Coelho, Antonio Raimundo, Jose Serro, Diogo Peres
 */
public class ComentarioEstabelecimento {

	/** o id do user. */
	private String userID;
    
    /** o comentario ao estabelecimento. */
    private String comentario;
    
    /** o id do estabelecimento. */
    private int idEstabelecimento;
    
    /** o controlador da pesquisa. */
	private ControllerPesquisa controller;
	
	/** a avaliacao. */
	private String nota;
	
	/** o id da fotografia. */
	private int idFoto;

    /**
     * Retornar o id do estabelecimento.
     *
     * @return o id do estabelecimento
     */
    public int getIdEstabelecimento() {
		return idEstabelecimento;
	}

	/**
	 * Modificar o id do estabelecimento.
	 *
	 * @param idEstabelecimento o novo id do estabelecimento
	 */
	public void setIdEstabelecimento(int idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

	/**
	 * Instancia a classe com os argumentos fornecidos e adiciona o comentario a lista de comentarios ao estabelecimento.
	 *
	 * @param controller o controlador da pesquisa
	 * @param idEstabelecimento o id do estabelecimento
	 * @param userID o id do user
	 * @param comentario o comentario ao estabelecimento
	 * @param nota a avaliacao
	 */
	public ComentarioEstabelecimento(ControllerPesquisa controller, int idEstabelecimento, String userID, String comentario, String nota) {
        this.userID = userID;
        this.nota=nota;
        this.comentario = comentario;
        this.idEstabelecimento=idEstabelecimento;
        this.controller=controller;
        this.idFoto=0;
        controller.getComentariosEstabelecimento().add(this);
    }

    /**
     * Construtor vazio para aceder aos metodos da classe sem necessitar de passar argumentos.
     */
    public ComentarioEstabelecimento() {}

    /**
     * Retorna o id do user.
     *
     * @return o id do user
     */
    public String getUserID() {
		return userID;
	}
    
    /**
     * Modifica o id do user
     * 
     */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * Retorna o comentario.
	 *
	 * @return o comentario
	 */
	public String getComentario() {
		return comentario;
	}
	
	/**
	 * Modifica o comentario.
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * Retorna o controlador de pesquisa.
	 * 
	 * @return o controlador de pesquisa
	 */
	public ControllerPesquisa getController() {
		return controller;
	}

	/**
	 * Modifica o controlador de pesquisa.
	 */
	public void setController(ControllerPesquisa controller) {
		this.controller = controller;
	}

	/**
	 * Insere um comentario ao estabelecimento na base de dados.
	 * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
	 *
	 * @param e o estabelecimento
	 * @param user o user
	 * @param comentario o comentario ao estabelecimento
	 * @param nota a avaliacao
	 * @return true, se bem sucedido
	 */
	public boolean save(String e, String user, String comentario, int nota) {
        DBConnector db = new DBConnector();
        return db.inserirComentarioEstabelecimento(e, user, comentario, nota);
    }

    /**
     * Procura um comentario ao estabelecimento na base de dados.
	 * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
     *
     * @param estabelecimento o estabelecimento
     * @param user o user
     * @param coment o comentario ao estabelecimento
     * @param nota a avaliacao
     * @return true, se bem sucedido
     */
    public boolean find(String estabelecimento, String user, String coment, int nota) {
        DBConnector db = new DBConnector();
        return db.findComentE(estabelecimento, user, coment, nota);
    }

    /**
     * Elimina um comentario ao estabelecimento na base de dados.
	 * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
     *
     * @param estabelecimento o estabelecimento
     * @param user o user
     * @param coment o comentario ao estabelecimento
     * @param nota a avaliacao
     */
    public void destroy(String estabelecimento, String user, String coment, int nota) {
        DBConnector db = new DBConnector();
        db.destroyComentE(estabelecimento, user, coment, nota);
    }

    /**
     * Procura todos comentarios aos estabelecimentos existentes na base de dados.
	 * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
     *
     * @param controller o controlador de pesquisa
     * @param userID o id do user
     * @param listaEstabelecimentos a lista de estabelecimentos
     * @param estabelecimento o estabelecimento
     * @param avaliacao a avaliacao
     * @param fotografia se tem fotografia
     * @param comentario o comentario ao estabelecimento
     */
    public void findAll(ControllerPesquisa controller, String userID, ArrayList<Estabelecimento> listaEstabelecimentos, String estabelecimento, int avaliacao, boolean fotografia, String comentario) {
        this.controller=controller;
        DBConnector db = new DBConnector();
        db.findComentarios(controller, userID, estabelecimento, listaEstabelecimentos, avaliacao, fotografia, comentario);
    }

	/**
	 * Retorna a avaliacao.
	 *
	 * @return a avaliacao
	 */
	public String getNota() {
		return nota;
	}

	/**
	 * Procura resultados duplicados.
	 * Caso haja ocorrencia de resultados duplicados, os mesmos sao eliminados da pesquisa 
	 * e sao apenas retornados resultados unicos.
	 *
	 * @param listaComentariosEstabelecimento a lista comentarios ao estabelecimento
	 * @return a lista comentarios ao estabelecimento sem duplicados
	 */
	public static ArrayList<ComentarioEstabelecimento> checkForDuplicated(ArrayList<ComentarioEstabelecimento> listaComentariosEstabelecimento) {
		for (int i = 1; i < listaComentariosEstabelecimento.size(); i++) {
			if(listaComentariosEstabelecimento.get(i).getComentario().equals(listaComentariosEstabelecimento.get(i-1).getComentario()) && listaComentariosEstabelecimento.get(i).getIdEstabelecimento()==listaComentariosEstabelecimento.get(i-1).getIdEstabelecimento() && listaComentariosEstabelecimento.get(i).getUserID().equals(listaComentariosEstabelecimento.get(i-1).getUserID())){
				listaComentariosEstabelecimento.remove(i);
				i--;
			}
		}
		return listaComentariosEstabelecimento;
	}

	/**
	 * Modifica o id da fotografia.
	 *
	 * @param idFoto o novo id da fotografia
	 */
	public void setFotografiaID(int idFoto) {
		this.idFoto=idFoto;
	}

	/**
	 * Procura todos os comentarios com fotografia aos estabelecimentos.
	 * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
	 *
	 * @param controllerPesquisa o controlador de pesquisa
	 * @param listaComentariosEstabelecimento a lista de comentarios ao estabelecimento
	 * @param listaEstabelecimentos a lista de estabelecimentos
	 */
	public void findFotos(ControllerPesquisa controllerPesquisa, ArrayList<ComentarioEstabelecimento> listaComentariosEstabelecimento, ArrayList<Estabelecimento> listaEstabelecimentos) {
        DBConnector db = new DBConnector();
        db.findFotoComents(this, listaComentariosEstabelecimento, listaEstabelecimentos);
	}

	/**
	 * Procura fotografia do comentario ao estabelecimento.
	 * Retorna a localizacao/path/caminho do ficheiro da fotografia.
	 * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
	 *
	 * @param email o email do user
	 * @param estabelecimento o estabelecimento
	 * @param comentario o comentario ao estabelecimento
	 * @return a localizacao/path/caminho do ficheiro da fotografia
	 */
	public static String getFotoForComent(String email, String estabelecimento, String comentario) {
		DBConnector db = new DBConnector();
        return db.findFotoForComentEstabelecimento(email, estabelecimento, comentario);
	}
	
    /**
     * Retorna o id da fotografia.
     *
     * @return o id da fotografia
     */
    public int getIdFoto() {
		return idFoto;
	}
}

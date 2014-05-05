package Comentarios;

import BaseDados.DBConnector;
import Controller.ControllerPesquisa;
import Pratos.Prato;

import java.util.ArrayList;

/**
 * Classe ComentarioPrato.
 * Esta classe representa um comentario ao prato.
 *
 * @author Nuno Coelho, Antonio Raimundo, Jose Serro, Diogo Peres
 */
public class ComentarioPrato {

    /** o id do prato. */
    private int id;
	
	/** o email do user. */
	private String email;
	
	/** o comentario. */
	private String comentario;
	
	/** a avaliacao. */
	private String nota;
	
	/** o id da fotografia. */
	private int idFoto;
	
	/**
	 * Construtor vazio para aceder aos metodos da classe sem necessitar de passar argumentos.
	 */
	public ComentarioPrato(){}

	/**
	 * Instancia a classe com os argumentos fornecidos e coloca o id da fotografia a 0.
	 *
	 * @param id o id do prato
	 * @param email o email do user
	 * @param comentario o comentario
	 * @param nota a avaliacao
	 */
	public ComentarioPrato(int id, String email, String comentario, String nota) {
		this.id=id;
		this.email=email;
		this.comentario=comentario;
		this.nota=nota;
		idFoto=0;
	}

	/**
	 * Retorna o id do prato.
	 *
	 * @return o id do prato
	 */
	public int getId() {
		return id;
	}

	/**
	 * Retorna o email do user.
	 *
	 * @return o email do user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Retorna o comentario ao prato.
	 *
	 * @return o comentario ao prato
	 */
	public String getComentario() {
		return comentario;
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
	 * Insere um comentario ao prato na base de dados.
	 * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
	 *
	 * @param p o prato
	 * @param user o user
	 * @param comentario o comentario ao prato
	 * @param nota a avaliacao
	 * @return true, se bem sucedido
	 */
	public boolean save(String p, String user, String comentario, int nota) {
        DBConnector db = new DBConnector();
        return db.inserirComentarioPrato(p, user, comentario, nota);
    }

    /**
     * Procura um comentario ao prato na base de dados.
	 * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector)..
     *
     * @param prato o prato
     * @param user o user
     * @param coment o comentario ao prato
     * @param nota a avaliacao
     * @return true, se bem sucecido
     */
    public boolean find(String prato, String user, String coment, int nota) {
        DBConnector db = new DBConnector();
        return db.findComent(prato, user, coment, nota);
    }

    /**
     * Elimina um comentario ao estabelecimento na base de dados.
	 * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
     *
     * @param prato o prato
     * @param user o user
     * @param coment o comentario ao prato
     * @param nota a avaliacao
     */
    public void destroy(String prato, String user, String coment, int nota) {
        DBConnector db = new DBConnector();
        db.destroyComent(prato, user, coment, nota);
    }

    /**
     * Procura todos os comentario ao estabelecimento existentes na base de dados.
     * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
     *
     * @param controller o controlador de pesquisa
     * @param username o username
     * @param prato o prato
     * @param estabelecimento o estabelecimento
     * @param avaliacao a avaliacao
     * @param fotografia se tem fotografia
     * @param comentario o comentario ao prato
     * @return a lista de todos os pratos existentes na base de dados
     */
    public ArrayList<ComentarioPrato> findAll(ControllerPesquisa controller,String username, String prato, String estabelecimento, int avaliacao, boolean fotografia, String comentario) {
        DBConnector db = new DBConnector();
        return db.findAllComents(controller, username, prato, estabelecimento, avaliacao, fotografia, comentario);
    }

	/**
	 * Verifica a existencia de resultados duplicados.
	 *
	 * @param listComentariosPrato a lista de todos comentarios ao prato
	 * @return the a lista de todos comentarios ao prato sem duplicados
	 */
	public static ArrayList<ComentarioPrato> checkForDuplicated(ArrayList<ComentarioPrato> listComentariosPrato) {
		for (int i = 1; i < listComentariosPrato.size(); i++) {
			if(listComentariosPrato.get(i).getComentario().equals(listComentariosPrato.get(i-1).getComentario()) && listComentariosPrato.get(i).getId()==listComentariosPrato.get(i-1).getId() && listComentariosPrato.get(i).getEmail().equals(listComentariosPrato.get(i-1).getEmail())){
				listComentariosPrato.remove(i);
				i--;
			}
		}
		return listComentariosPrato;
	}

	/**
	 * Procura todos os comentarios com fotografia aos pratos.
	 * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
	 *
	 * @param controllerPesquisa o controlador de pesquisa
	 * @param listComentariosPrato a lista de comentarios ao prato
	 * @param listaPratos a lista de pratos com fotografia
	 */
	public void findFotos(ControllerPesquisa controllerPesquisa, ArrayList<ComentarioPrato> listComentariosPrato, ArrayList<Prato> listaPratos) {
        DBConnector db = new DBConnector();
        db.findFotoComentsPlates(this, listComentariosPrato, listaPratos);
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
	 * Retorna o id da fotografia.
	 *
	 * @return o id da fotografia
	 */
	public int getIdFoto() {
		return idFoto;
	}

	/**
	 * Procura fotografia do comentario ao prato.
	 * Retorna a localizacao/path/caminho do ficheiro da fotografia.
	 * Estabelece comunicacao com a classe que gerencia a base de dados (DBConnector).
	 *
	 * @param email o email do user
	 * @param prato o prato
	 * @param comentario o comentario ao prato
	 * @return a localizacao/path/caminho do ficheiro da fotografia.
	 */
	public static String getFotoForComent(String email, String prato, String comentario) {
		DBConnector db = new DBConnector();
        return db.findFotoForComentPrato(email, prato, comentario);
	}
}

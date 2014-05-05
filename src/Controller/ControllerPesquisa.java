package Controller;

import APIs.APIEstabelecimentos;
import Comentarios.ComentarioEstabelecimento;
import Comentarios.ComentarioPrato;
import Comentarios.ControllerComentario;
import Comentarios.InterfaceComentario;
import Fotografia.InterfaceFotografia;
import Pratos.Prato;
import Suporte.TipoComentario;
import Utilizador.Utilizador;

import java.util.ArrayList;

/**
 * Classe ControllerPesquisa.
 * Esta classe controla as operacoes que sao necessarias na pesquisa por pratos/estabelecimentos.
 *
 * @author Nuno Coelho, Antonio Raimundo, Jose Serro, Diogo Peres
 */
public class ControllerPesquisa implements APIEstabelecimentos{
    
    /** o id do user. */
    @SuppressWarnings("unused")
	private String userID;
    
    /** a lista de comentarios ao estabelecimento. */
    private ArrayList<ComentarioEstabelecimento> listaComentariosEstabelecimento = new ArrayList<ComentarioEstabelecimento>();
    
    /** a lista de estabelecimentos. */
    private ArrayList<Estabelecimento> listaEstabelecimentos = new ArrayList<Estabelecimento>();
    
    /** o controlador de comentarios. */
    @SuppressWarnings("unused")
	private ControllerComentario controlComent = new ControllerComentario();
    
    /** a lista de pratos. */
    private ArrayList<Prato> listaPratos = new ArrayList<Prato>();
    
    /** a lista de comentarios ao prato. */
    private ArrayList<ComentarioPrato> listComentariosPrato = new ArrayList<ComentarioPrato>();
    
    /**
     * Cria um comentario.
     */
    public void createComentario() {
        //InterfaceComentario window = new InterfaceComentario();
        //window.init();
    }
    
    /**
     * Instancia um novo controlador de pesquisa.
     */
    public ControllerPesquisa(){
    	listaEstabelecimentos=setWindowData();
    	listaPratos=setWindowDataPratos();
    }
    
    /**
     * Retorna o estabelecimento, dando um nome.
     *
     * @param name o nome do estabelecimento
     * @return os estabelecimentos com o nome fornecido no argumento
     */
    public int getEstabelecimentoByName(String name){
    	for (int i = 0; i < listaEstabelecimentos.size(); i++) {
			if(listaEstabelecimentos.get(i).getDesignacao().equals(name)){
				return listaEstabelecimentos.get(i).getId();
			}
		}
    	return 0;
    }
    
    /**
     * Retorna a lista de estabelecimentos.
     *
     * @return a lista de estabelecimentos
     */
    public ArrayList<Estabelecimento> getEstabelecimentos(){
        return listaEstabelecimentos;
    }
    
    /**
     * Retorna a lista de comentarios ao estabelecimento.
     *
     * @return a lista de comentarios ao estabelecimento
     */
    public ArrayList<ComentarioEstabelecimento> getComentariosEstabelecimento(){
        return listaComentariosEstabelecimento;
    }

    /**
     * Adiciona uma fotografia ao estabelecimento/prato.
     *
     * @param frame a frame/janela
     * @param email o email do user
     * @param estabelecimento o estabelecimento
     * @param prato o prato
     * @param comentario o comentario
     */
    public void addFotografia(InterfacePesquisa frame, String email, String estabelecimento, String prato, String comentario) {
        @SuppressWarnings("unused")
		InterfaceFotografia window = new InterfaceFotografia();
        InterfaceFotografia.init(this, frame, email, estabelecimento, prato, comentario);
    }
    
    /**
     * Ver fotografia.
     * Este metodo verifica o tipo de comentario e aprensenta os comentarios com fotografias de acordo com 
     * os parametros de pesquisa efectuada pelo utilizador.
     *
     * @param frame a frame/janela
     * @param email o email do user
     * @param estabelecimento o estabelecimento
     * @param prato o prato
     * @param comentario o comentario
     */
    public void viewFotografia(InterfacePesquisa frame, String email, String estabelecimento, String prato, String comentario) {
    	@SuppressWarnings("unused")
		InterfaceFotografia window = new InterfaceFotografia();
    	String caminhoFoto="";
    	if(InterfacePesquisa.getTipoComentario().equals(TipoComentario.ESTABELECIMENTO)){
    		caminhoFoto= ComentarioEstabelecimento.getFotoForComent(email, estabelecimento, comentario);
    	}else{
    		caminhoFoto= ComentarioPrato.getFotoForComent(email, prato, comentario);
    	}
        InterfaceFotografia.init2(this, frame, email, estabelecimento, prato, comentario, caminhoFoto);
	}
    
    /**
     * Procura por estabelecimentos.
     * Este metodo procura por todos os estabelecimentos, conforme os parametros de pesquisa 
     * introduzidos pelo utilizador do sistema e preenche a tabela de resultados sem dados duplicados.
     *
     * @param user o user
     * @param estabelecimento o estabelecimento
     * @param prato o prato
     * @param avaliacao a avaliacao
     * @param fotografia se tem fotografia
     * @param comentario o comentario
     */
    public void searchEstabelecimento(String user, String estabelecimento, String prato, int avaliacao, boolean fotografia, String comentario){
        Estabelecimento e = new Estabelecimento();
        e.findAll(this, user, estabelecimento, prato, avaliacao, fotografia, comentario); 
        ComentarioEstabelecimento c = new ComentarioEstabelecimento();
        c.findAll(this, user, listaEstabelecimentos, estabelecimento, avaliacao, fotografia, comentario);
        listaComentariosEstabelecimento = ComentarioEstabelecimento.checkForDuplicated(listaComentariosEstabelecimento);
        c.findFotos(this, listaComentariosEstabelecimento, listaEstabelecimentos);
        InterfacePesquisa.preencherPesquisa(listaEstabelecimentos, listaComentariosEstabelecimento);
    }
    
    /**
     * Procura por pratos.
     * Este metodo procura por todos os pratos, conforme os parametros de pesquisa 
     * introduzidos pelo utilizador do sistema e preenche a tabela de resultados sem dados duplicados.
     *
     * @param user o user
     * @param estabelecimento o estabelecimento
     * @param prato o prato
     * @param avaliacao a avaliacao
     * @param fotografia se tem fotografia
     * @param comentario o comentario
     */
    public void searchPrato(String user, String estabelecimento, String prato, int avaliacao, boolean fotografia, String comentario){
        Prato novoPrato = new Prato();
        listaPratos = novoPrato.findAll(estabelecimento, prato, fotografia);
        ComentarioPrato coments = new ComentarioPrato();
        listComentariosPrato = coments.findAll(this, user, prato, estabelecimento, avaliacao, fotografia, comentario);
        listComentariosPrato = ComentarioPrato.checkForDuplicated(listComentariosPrato);
        coments.findFotos(this, listComentariosPrato, listaPratos);
        InterfacePesquisa.preencherPesquisaPratos(listaPratos, listComentariosPrato, listaEstabelecimentos);
    }

	/**
	 * Retorna a lista de todos os estabelecimentos.
	 *
	 * @return a lista de todos os estabelecimentos
	 */
	public ArrayList<Estabelecimento> setWindowData() {
		Estabelecimento e = new Estabelecimento();
		listaEstabelecimentos =  e.findAllForStart();
		return listaEstabelecimentos;
		
	}

	/**
	 * Retorna a lista de todos os pratos.
	 *
	 * @return a lista de todos os pratos
	 */
	public ArrayList<Prato> setWindowDataPratos() {
		Prato p = new Prato();
		return p.findAllForStart();
	}

	/**
	 * Retorna a lista de todos os users.
	 *
	 * @return a lista de todos os users
	 */
	public ArrayList<Utilizador> getAllUsers() {
		Utilizador users = new Utilizador();
		return users.getAll();
	}

	/**
	 * Ver comentario.
	 *
	 * @param pesquisa a interface de pesquisa
	 * @param tipoComentario o tipo de comentario
	 * @param email o email do user
	 * @param avaliacao a avaliacao
	 * @param comentario o comentario
	 * @param estabelecimento o estabelecimento
	 * @param prato o prato
	 */
	public void viewComentario(InterfacePesquisa pesquisa, TipoComentario tipoComentario, String email, String avaliacao, String comentario, String estabelecimento, String prato) {
		InterfaceComentario.init(pesquisa, false, tipoComentario, email, avaliacao, comentario, prato, estabelecimento);
	}

	/**
	 * Retorna o prato, dando um nome.
	 *
	 * @param prato o prato
	 * @return os pratos com o nome fornecido no argumento
	 */
	public int getPratoByName(String prato) {
		for (int i = 0; i < listaPratos.size(); i++) {
			if(listaPratos.get(i).getDescricao().equals(prato)){
				return listaPratos.get(i).getId();
			}
		}
    	return 0;
	}

	/* (non-Javadoc)
	 * @see APIs.APIEstabelecimentos#viewAllComentarioAoPrato(int, int)
	 */
	@Override
	public void viewAllComentarioAoPrato(int idPrato, int idEstabelecimento) {
		//não especificado ainda
	}

	/* (non-Javadoc)
	 * @see APIs.APIEstabelecimentos#viewAllComentarioAoEstabelecimento(int)
	 */
	@Override
	public void viewAllComentarioAoEstabelecimento(int idEstabelecimento) {
		//não especificado ainda
	}
	
	

	
}

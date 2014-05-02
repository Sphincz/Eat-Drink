/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
 *
 * @author Nuno
 */
public class ControllerPesquisa {
    private String userID;
    private ArrayList<ComentarioEstabelecimento> listaComentariosEstabelecimento = new ArrayList<ComentarioEstabelecimento>();
    private ArrayList<Estabelecimento> listaEstabelecimentos = new ArrayList<Estabelecimento>();
    private ControllerComentario controlComent = new ControllerComentario();
    private ArrayList<Prato> listaPratos = new ArrayList<Prato>();
    private ArrayList<ComentarioPrato> listComentariosPrato = new ArrayList<ComentarioPrato>();
    
    public void createComentario() {
        //InterfaceComentario window = new InterfaceComentario();
        //window.init();
    }
    
    public int getEstabelecimentoByName(String name){
    	System.out.println(listaEstabelecimentos.size());
    	for (int i = 0; i < listaEstabelecimentos.size(); i++) {
    		System.out.println("->"+listaEstabelecimentos.get(i).getDesignacao());
			if(listaEstabelecimentos.get(i).getDesignacao().equals(name)){
				System.out.println(name + "-" +listaEstabelecimentos.get(i).getDesignacao());
				return listaEstabelecimentos.get(i).getId();
			}
		}
    	return 0;
    }
    
    public ArrayList<Estabelecimento> getEstabelecimentos(){
        return listaEstabelecimentos;
    }
    
    public ArrayList<ComentarioEstabelecimento> getComentariosEstabelecimento(){
        return listaComentariosEstabelecimento;
    }

    public void addFotografia(InterfacePesquisa frame) {
        InterfaceFotografia window = new InterfaceFotografia();
        window.init(frame);
    }
    
    public void viewFotografia(InterfacePesquisa frame, String email, String estabelecimento, String prato, String comentario) {
    	InterfaceFotografia window = new InterfaceFotografia();
    	String caminhoFoto="";
    	if(frame.getTipoComentario().equals(TipoComentario.ESTABELECIMENTO)){
    		caminhoFoto= ComentarioEstabelecimento.getFotoForComent(email, estabelecimento, comentario);
    	}else{
    		caminhoFoto= ComentarioPrato.getFotoForComent(email, prato, comentario);
    	}
        window.init2(frame, email, estabelecimento, prato, comentario, caminhoFoto);
	}
    
    public void searchEstabelecimento(String user, String estabelecimento, String prato, int avaliacao, boolean fotografia, String comentario){
        Estabelecimento e = new Estabelecimento();
        e.findAll(this, user, estabelecimento, prato, avaliacao, fotografia, comentario); 
        ComentarioEstabelecimento c = new ComentarioEstabelecimento();
        c.findAll(this, user, listaEstabelecimentos, estabelecimento, avaliacao, fotografia, comentario);
        listaComentariosEstabelecimento = ComentarioEstabelecimento.checkForDuplicated(listaComentariosEstabelecimento);
        c.findFotos(this, listaComentariosEstabelecimento, listaEstabelecimentos);
        InterfacePesquisa.preencherPesquisa(listaEstabelecimentos, listaComentariosEstabelecimento);
    }
    
    public void searchPrato(String user, String estabelecimento, String prato, int avaliacao, boolean fotografia, String comentario){
        Prato novoPrato = new Prato();
        listaPratos = novoPrato.findAll(estabelecimento, prato, fotografia);
        ComentarioPrato coments = new ComentarioPrato();
        listComentariosPrato = coments.findAll(this, user, prato, estabelecimento, avaliacao, fotografia, comentario);
        listComentariosPrato = ComentarioPrato.checkForDuplicated(listComentariosPrato);
        coments.findFotos(this, listComentariosPrato, listaPratos);
        InterfacePesquisa.preencherPesquisaPratos(listaPratos, listComentariosPrato, listaEstabelecimentos);
    }

	public ArrayList<Estabelecimento> setWindowData() {
		Estabelecimento e = new Estabelecimento();
		listaEstabelecimentos =  e.findAllForStart();
		return listaEstabelecimentos;
		
	}

	public ArrayList<Prato> setWindowDataPratos() {
		Prato p = new Prato();
		return p.findAllForStart();
	}

	public ArrayList<Utilizador> getAllUsers() {
		Utilizador users = new Utilizador();
		return users.getAll();
	}

	public void viewComentario(TipoComentario tipoComentario, String email, String avaliacao, String comentario, String estabelecimento, String prato) {
		InterfaceComentario.init(false, tipoComentario, email, avaliacao, comentario, prato, estabelecimento);
	}

	
}

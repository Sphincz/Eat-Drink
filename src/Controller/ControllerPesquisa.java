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
    
    public void createComentario() {
        InterfaceComentario window = new InterfaceComentario(controlComent);
        window.init();
    }
    
    public ArrayList<Estabelecimento> getEstabelecimentos(){
        return listaEstabelecimentos;
    }
    
    public ArrayList<ComentarioEstabelecimento> getComentariosEstabelecimento(){
        return listaComentariosEstabelecimento;
    }

    public void addFotografia() {
        InterfaceFotografia window = new InterfaceFotografia();
        window.init();
    }
    
    public void searchEstabelecimento(String user, String estabelecimento, String prato, int avaliacao, boolean fotografia, String comentario){
        Estabelecimento e = new Estabelecimento();
        e.findAll(this, user, estabelecimento, prato, avaliacao, fotografia, comentario); 
        ComentarioEstabelecimento c = new ComentarioEstabelecimento();
        c.findAll(this, userID, listaEstabelecimentos, avaliacao, fotografia, comentario);
        InterfacePesquisa.preencherPesquisa(listaEstabelecimentos, listaComentariosEstabelecimento);
        //interface vem buscar as listas depois
    }
    
    public void searchPrato(String user, String estabelecimento, String Prato, int avaliacao, boolean fotografia, String comentario){
        Prato prato = new Prato();
        ArrayList<Prato> listaPratos = prato.findAll(estabelecimento, prato, fotografia);
        ComentarioPrato coments = new ComentarioPrato();
        ArrayList<ComentarioPrato> listaComentariosPrato = coments.findAll(userID, listaPratos, avaliacao, fotografia, comentario);
    }
}

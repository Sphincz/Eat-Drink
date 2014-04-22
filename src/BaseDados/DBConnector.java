/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDados;

import Controller.Estabelecimento;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Nuno
 */
public class DBConnector {

    public boolean inserirComentarioEstabelecimento(int id, String comentario, int nota) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    public boolean inserirComentarioPrato(int id, String comentario, int nota) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void saveFoto(int id, File foto) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean findComent(int idComent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void destroyComent(int idComent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean findFoto(int idFoto) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void destroyFoto(int idFoto) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void findEstabelecimentos(String user, String estabelecimento, String prato, int avaliacao, boolean fotografia, String comentario) {
        throw new UnsupportedOperationException("Not yet implemented");
        //for
        //Estabelecimento e = new Estabalecimento(id, nome, prato, fotografia);
    }

    public void findComentarios(int userID, ArrayList<Estabelecimento> listaEstabelecimentos, int avaliacao, boolean fotografia, String comentario) {
        throw new UnsupportedOperationException("Not yet implemented");
        //for
        //ComentarioEstabelecimento c = new ComentarioEstabelecimento(userID, comentarioID, comentario);
    }
    
}

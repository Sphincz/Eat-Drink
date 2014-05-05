package Fotografia;

import java.io.File;

import javax.swing.JOptionPane;

import Controller.ControllerPesquisa;

/**
 * Classe ControllerFotografia.
 * Esta classe controla as operacoes necessarias para fazer upload de uma fotografia.
 *
 * @author Nuno Coelho, Antonio Raimundo, Jose Serro, Diogo Peres
 */
public class ControllerFotografia {

    /**
     * Upload de uma fotografia.
     * Este metodo cria uma fotografia, e introduz a fotografia posteriormente na base de dados.
     * 
     *
     * @param controller o controlador de pesquisa
     * @param estabelecimento o estabelecimento
     * @param prato o prato
     * @param email o email do user
     * @param coment o comentario
     * @param file o ficheiro de fotografia
     */
    public void uploadFotografia(ControllerPesquisa controller, String estabelecimento, String prato, String email, String coment, File file) {
        Fotografia foto = new Fotografia(controller, estabelecimento, prato, email, coment, file);//guarda temporariamente foto
        foto.save();
    }

    /**
     * Elimina uma fotografia.
     * Cria uma mensagem de informacao que indica que a fotografia foi eliminada com sucesso.
     *
     * @param email o email do user
     * @param comentario o comentario
     * @param estabelecimento o estabelecimento
     * @param prato o prato
     * @return true, se bem sucedido
     */
    public boolean deleteFotografia(String email, String comentario, String estabelecimento, String prato) {
        Fotografia foto = new Fotografia();
        boolean encontrou = foto.find(email, comentario, estabelecimento, prato);
        if(encontrou){
            foto.destroy(foto.getId());
            JOptionPane.showMessageDialog(null, "Foto eliminada.");
            return true;
        }
        return false; 
    }
}

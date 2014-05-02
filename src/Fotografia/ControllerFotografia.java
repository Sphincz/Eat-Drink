/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fotografia;

import java.io.File;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Controller.ControllerPesquisa;

/**
 *
 * @author Nuno
 */
public class ControllerFotografia {

    public void uploadFotografia(ControllerPesquisa controller, String estabelecimento, String prato, String email, String coment, File file) {
        Fotografia foto = new Fotografia(controller, estabelecimento, prato, email, coment, file);//guarda temporariamente foto
        foto.save();//guarda na BD
    }

    public boolean deleteFotografia(String email, String comentario, String estabelecimento, String prato) {
        Fotografia foto = new Fotografia();
        boolean encontrou = foto.find(email, comentario, estabelecimento, prato);
        System.out.println("ok");
        if(encontrou){
        	System.out.println("okok");
            foto.destroy(foto.getId());
            JOptionPane.showMessageDialog(null, "Foto eliminada.");
            return true;
        }
        return false;
        
    }

    

   
    
}

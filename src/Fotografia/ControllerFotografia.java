/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fotografia;

import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Nuno
 */
public class ControllerFotografia {

    public void uploadFotografia(String email, String coment, File file) {
        Fotografia foto = new Fotografia(email, coment, file);//guarda temporariamente foto
        foto.save();//guarda na BD
    }

    public boolean deleteFotografia(int idFoto) {
        Fotografia foto = new Fotografia();
        boolean encontrou = foto.find(idFoto);
        if(encontrou){
            foto.destroy(idFoto);
            JOptionPane.showMessageDialog(null, "Foto eliminada.");
            return true;
        }
        return false;
        
    }

    

   
    
}

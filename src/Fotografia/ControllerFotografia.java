/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fotografia;

import java.io.File;

/**
 *
 * @author Nuno
 */
public class ControllerFotografia {

    public void uploadFotografia(int id, File file) {
        Fotografia foto = new Fotografia(id, file);//guarda temporariamente foto
        foto.save();//guarda na BD
    }

   
    
}

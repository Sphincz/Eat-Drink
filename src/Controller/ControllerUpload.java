/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Fotografia.Fotografia;
import java.io.File;

/**
 *
 * @author Nuno
 */
public class ControllerUpload {

    public boolean uploadFotografiaUtilizador(File file) {
        Fotografia fotoPerfil = new Fotografia();
        fotoPerfil.save(file);
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}

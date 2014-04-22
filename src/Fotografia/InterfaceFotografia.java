/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fotografia;
import java.io.File;
import javax.swing.JFrame;

/**
 *
 * @author Nuno
 */
public class InterfaceFotografia extends JFrame{
    private ControllerFotografia controlFoto;

    public void init() {
        controlFoto = new ControllerFotografia();
        //janela
    }
    
    public void uploadFotografia(){
        File file=null;
        int comentID=0;
        controlFoto.uploadFotografia(comentID, file);
    }
    
    
    
}

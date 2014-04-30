/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import javax.swing.JFrame;

/**
 *
 * @author Nuno
 */
public class InterfaceUpload extends JFrame{
	private static final long serialVersionUID = 1L;

	public void init(){//pseudo-construtor
        upload();//ABRIR CARREGAR FOTOGRAFIA DO Utilizador
        
    }

    private boolean upload() {//ABRIR CARREGAR FOTOGRAFIA DO Utilizador
        File file = null; //imagem carregada aqui
        ControllerUpload controller = new ControllerUpload();
        boolean result = controller.uploadFotografiaUtilizador(file);
        return result;
    }
}

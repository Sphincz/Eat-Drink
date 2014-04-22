/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Comentarios.ControllerComentario;
import Comentarios.InterfaceComentario;
import Fotografia.InterfaceFotografia;

/**
 *
 * @author Nuno
 */
public class ControllerPesquisa {
    
    ControllerComentario controlComent = new ControllerComentario();
    
    public void createComentario() {
        InterfaceComentario window = new InterfaceComentario(controlComent);
        window.init();
    }

    public void addFotografia() {
        InterfaceFotografia window = new InterfaceFotografia();
        window.init();
    }
}

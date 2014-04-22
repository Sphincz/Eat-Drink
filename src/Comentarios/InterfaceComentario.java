/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Comentarios;

import Suporte.TipoComentario;
import javax.swing.JFrame;

/**
 *
 * @author Nuno
 */
public class InterfaceComentario extends JFrame{
	private static final long serialVersionUID = 1L;
	private ControllerComentario controlComent;

    public InterfaceComentario(ControllerComentario controlComent){
        this.controlComent=controlComent;
    }
    
    public void init() {
        //janela
    }
    
    public boolean save(TipoComentario tipo, int id, String comentario, int nota){
        return controlComent.save(tipo, id, comentario, nota);
    }
    
}

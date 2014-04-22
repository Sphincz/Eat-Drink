/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Comentarios;

import java.awt.EventQueue;

import Suporte.TipoComentario;

import javax.swing.JFrame;

/**
 *
 * @author Nuno
 */
public class InterfaceComentario extends JFrame{
	private static final long serialVersionUID = 1L;
	private static ControllerComentario controlComent;

    public InterfaceComentario(ControllerComentario controlComent){
    	setTitle("Eat&Drink - Adicionar coment\u00E1rio");
    	getContentPane().setLayout(null);
        InterfaceComentario.controlComent=controlComent;
    }
    
    public static void init() {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceComentario frame = new InterfaceComentario(controlComent);
					frame.setVisible(true);
					frame.setSize(650, 550);
					frame.setLocation(350, 75);
					frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
    
    public boolean save(TipoComentario tipo, int id, String comentario, int nota){
        return controlComent.save(tipo, id, comentario, nota);
    }
    
    public void delete(int idComent){
        boolean apagou = controlComent.delete(idComent);
        if(apagou){
            this.setVisible(false);
        }
    }
    
}

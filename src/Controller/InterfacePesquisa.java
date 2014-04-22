/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.swing.JFrame;

/**
 *
 * @author Group 1 - LEI | 2014 © SID 
 */
public class InterfacePesquisa extends JFrame{

	private static final long serialVersionUID = 1L;

	private ControllerPesquisa controlPesquisa;
    
    private JFrame window;
    private final int WIDTH=600;
    private final int HEIGHT=600;
    
    public void execute() {//start system
        controlPesquisa = new ControllerPesquisa();
        initWindow();
        
    }

    private void initWindow() {//window configuration
        window = new JFrame("Eat & Drink - Files Manager");
        
        //window componentes
        
        
        //window options
        window.setSize(WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
    }
    
    public void createComentario(){
        controlPesquisa.createComentario();
    }
    
    public void addFotografia(){
        controlPesquisa.addFotografia();
    }
    
}

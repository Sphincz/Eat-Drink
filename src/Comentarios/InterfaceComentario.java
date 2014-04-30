/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Comentarios;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Suporte.TipoComentario;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Nuno
 */
public class InterfaceComentario extends JFrame{
	private static final long serialVersionUID = 1L;
	private static ControllerComentario controlComent;
	private static InterfaceComentario frame;

    public InterfaceComentario(ControllerComentario controlComent){
    	setTitle("Eat&Drink - Adicionar coment\u00E1rio");
    	getContentPane().setLayout(null);
    	
    	JLabel lblColocadoPor = new JLabel("Colocado por:");
    	lblColocadoPor.setBounds(35, 20, 67, 14);
    	getContentPane().add(lblColocadoPor);
    	
    	JLabel lblSphincz = new JLabel("Sphincz");
    	lblSphincz.setBounds(112, 20, 46, 14);
    	getContentPane().add(lblSphincz);
    	
    	JLabel lblEm = new JLabel("Em:");
    	lblEm.setBounds(84, 45, 18, 14);
    	getContentPane().add(lblEm);
    	
    	JLabel lblData = new JLabel("Data");
    	lblData.setBounds(112, 45, 46, 14);
    	getContentPane().add(lblData);
    	
    	JLabel lbls = new JLabel("\u00E0s");
    	lbls.setBounds(160, 45, 11, 14);
    	getContentPane().add(lbls);
    	
    	JLabel lblHoras = new JLabel("Horas");
    	lblHoras.setBounds(181, 45, 46, 14);
    	getContentPane().add(lblHoras);
    	
    	JLabel lblEstabelecimento = new JLabel("Estabelecimento:");
    	lblEstabelecimento.setBounds(20, 70, 82, 14);
    	getContentPane().add(lblEstabelecimento);
    	
    	JLabel lblBarDoToni = new JLabel("Bar do Toni");
    	lblBarDoToni.setBounds(112, 70, 54, 14);
    	getContentPane().add(lblBarDoToni);
    	
    	JLabel lblPrato = new JLabel("Prato:");
    	lblPrato.setBounds(181, 70, 46, 14);
    	getContentPane().add(lblPrato);
    	
    	JLabel lblMenuEspecial = new JLabel("Menu Especial");
    	lblMenuEspecial.setBounds(222, 70, 67, 14);
    	getContentPane().add(lblMenuEspecial);
    	
    	JTextArea textArea = new JTextArea();
    	textArea.setBounds(10, 96, 339, 94);
    	getContentPane().add(textArea);
    	
    	JLabel lblAvaliao = new JLabel("Avalia\u00E7\u00E3o:");
    	lblAvaliao.setBounds(108, 204, 50, 14);
    	getContentPane().add(lblAvaliao);
    	
    	JSpinner spinner = new JSpinner(new SpinnerNumberModel(3, 0, 5, 1));
    	spinner.setBounds(170, 201, 82, 20);
    	getContentPane().add(spinner);
    	
    	JButton btnGravarAlteraes = new JButton("Gravar altera\u00E7\u00F5es");
    	btnGravarAlteraes.setBounds(10, 253, 119, 23);
    	getContentPane().add(btnGravarAlteraes);
    	
    	JButton btnApagar = new JButton("Apagar");
    	btnApagar.setBounds(171, 253, 67, 23);
    	getContentPane().add(btnApagar);
    	
    	JButton btnSair = new JButton("Sair");
    	btnSair.setBounds(280, 253, 51, 23);
    	btnSair.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
    	});
    	getContentPane().add(btnSair);
        InterfaceComentario.controlComent=controlComent;
    }
    
    public static void init() {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new InterfaceComentario(controlComent);
					frame.setVisible(true);
					frame.setSize(375, 325);
					frame.setLocation(475, 175);
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

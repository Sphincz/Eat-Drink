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
	private static JLabel estabelecimento;
	private static JLabel username;
	private static JLabel prato;
	private static JSpinner avaliacao;
	private static JTextArea comentario;
	private static JButton btnApagar;
	private static JButton btnGravarAlteraes;

    public InterfaceComentario(ControllerComentario controlComent){
    	//setTitle("Eat&Drink - Adicionar coment\u00E1rio");
    	getContentPane().setLayout(null);
    	
    	JLabel lblColocadoPor = new JLabel("Colocado por:");
    	lblColocadoPor.setBounds(35, 20, 67, 14);
    	getContentPane().add(lblColocadoPor);
    	
    	username = new JLabel("Sphincz");
    	username.setBounds(112, 20, 400, 14);
    	getContentPane().add(username);
    	
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
    	
    	estabelecimento = new JLabel("Bar do Toni");
    	estabelecimento.setBounds(112, 70, 54, 14);
    	getContentPane().add(estabelecimento);
    	
    	JLabel lblPrato = new JLabel("Prato:");
    	lblPrato.setBounds(181, 70, 46, 14);
    	getContentPane().add(lblPrato);
    	
    	prato = new JLabel("Menu Especial");
    	prato.setBounds(222, 70, 67, 14);
    	getContentPane().add(prato);
    	
    	comentario = new JTextArea();
    	comentario.setBounds(10, 96, 339, 94);
    	getContentPane().add(comentario);
    	
    	JLabel lblAvaliao = new JLabel("Avalia\u00E7\u00E3o:");
    	lblAvaliao.setBounds(108, 204, 50, 14);
    	getContentPane().add(lblAvaliao);
    	
    	avaliacao = new JSpinner(new SpinnerNumberModel(3, 0, 5, 1));
    	avaliacao.setBounds(170, 201, 82, 20);
    	getContentPane().add(avaliacao);
    	
    	btnGravarAlteraes = new JButton("Gravar altera\u00E7\u00F5es");
    	btnGravarAlteraes.setBounds(10, 253, 119, 23);
    	getContentPane().add(btnGravarAlteraes);
    	
    	btnApagar = new JButton("Apagar");
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
    
    public static void init(final boolean editable, TipoComentario tipoComentario, final String name, final String a, final String c, final String p, final String e) {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new InterfaceComentario(controlComent);
					frame.setVisible(true);
					frame.setSize(375, 325);
					frame.setLocation(475, 175);
					frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					estabelecimento.setText(e);
					username.setText(name);
					prato.setText(p);
					comentario.setText(c);
					if(!editable){
						frame.setTitle("Eat&Drink - Ver comentario");
						avaliacao.setValue(Integer.parseInt(a));
						comentario.setEditable(false);
						btnApagar.setEnabled(false);
						btnGravarAlteraes.setEnabled(false);
						avaliacao.setEnabled(false);
					}else{
						frame.setTitle("Eat&Drink - Adicionar comentario");
					}
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

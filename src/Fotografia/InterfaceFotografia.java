/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fotografia;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.ControllerPesquisa;
import Controller.InterfacePesquisa;

/**
 *
 * @author Nuno
 */
public class InterfaceFotografia extends JFrame{
	private static InterfaceFotografia frame;
	private File file;
	private static JButton btnApagar;
	private static JPanel fotoPanel=null;
	private static JButton btnUpload;
	private static JLabel lblEm;
	private static JLabel user;
	private static JLabel comentario;
	private static JLabel estabelecimento;
	private static JLabel prato;
	private static String caminhoFoto;
	
	public InterfaceFotografia() {
		setTitle("Eat&Drink - Adicionar fotografia");
		getContentPane().setLayout(null);
		
		JLabel lblColocadoPor = new JLabel("Colocado por:");
		lblColocadoPor.setBounds(25, 11, 67, 14);
		getContentPane().add(lblColocadoPor);
		
		user = new JLabel("Sphincz");
		user.setBounds(102, 11, 46, 14);
		getContentPane().add(user);
		
		lblEm = new JLabel("Em:");
		lblEm.setBounds(74, 36, 18, 14);
		getContentPane().add(lblEm);
		
		comentario = new JLabel("Data");
		comentario.setBounds(102, 40, 46, 14);
		getContentPane().add(comentario);
		
		JLabel lblEstabelecimento = new JLabel("Estabelecimento:");
		lblEstabelecimento.setBounds(10, 61, 82, 14);
		getContentPane().add(lblEstabelecimento);
		
		estabelecimento = new JLabel("Bar do Toni");
		estabelecimento.setBounds(102, 61, 54, 14);
		getContentPane().add(estabelecimento);
		
		JLabel lblPrato = new JLabel("Prato:");
		lblPrato.setBounds(62, 86, 30, 14);
		getContentPane().add(lblPrato);
		
		prato = new JLabel("Menu Especial");
		prato.setBounds(102, 86, 67, 14);
		getContentPane().add(prato);
		
		btnApagar = new JButton("Apagar");
		btnApagar.setBounds(10, 128, 89, 23);
		getContentPane().add(btnApagar);
		btnApagar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				controlFoto.deleteFotografia(user.getText(), comentario.getText(), estabelecimento.getText(), prato.getText());
				frame.dispose();
			}
			
		});
		
		btnUpload = new JButton("Upload");
		btnUpload.setBounds(260, 128, 89, 23);
		getContentPane().add(btnUpload);
		btnUpload.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
                uploadFotografia();
				
			}
			
		});
		
		UIManager.put("FileChooser.lookInLabelText","Procurar em:"); 
		UIManager.put("FileChooser.filesOfTypeLabelText","Tipo de ficheiros:"); 
		UIManager.put("FileChooser.fileNameLabelText","Nome do ficheiro:");
		UIManager.put("FileChooser.openButtonText","Abrir");
		UIManager.put("FileChooser.cancelButtonText","Cancelar");
		UIManager.put("FileChooser.openDialogTitleText","Adicionar fotografia");
		
		final JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens", "jpg", "png", "gif", "jpeg");
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(filter);
		
		fotoPanel = new JPanel(new GridBagLayout());;
		fotoPanel.setBounds(260, 11, 89, 89);
		fotoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		fotoPanel.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int returnVal = fc.showOpenDialog(fotoPanel);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
	                file = fc.getSelectedFile();
	                
	                try {
	                	fotoPanel.removeAll();
	            		BufferedImage img=ImageIO.read(file);
	            		Image resized = img.getScaledInstance(fotoPanel.getWidth(), fotoPanel.getHeight(), Image.SCALE_SMOOTH);
	            		ImageIcon icon=new ImageIcon(resized);
	            		
	            		JLabel fotoLabel = new JLabel(icon);
	            		fotoLabel.setHorizontalAlignment(JLabel.CENTER);

	            		fotoPanel.add(fotoLabel, new GridBagConstraints());
	            		fotoPanel.revalidate(); 
	            	}
	            	catch(IOException e) {
	            		e.printStackTrace();
	            	}
	                System.out.println("[Fotografia] Caminho da imagem: "+file);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		getContentPane().add(fotoPanel);
	}
	private static final long serialVersionUID = 1L;
	private static ControllerFotografia controlFoto;
	private static InterfacePesquisa framePrincipal;
	private static ControllerPesquisa controllerPesquisa;

	public static void init2(ControllerPesquisa controll, InterfacePesquisa frameP, final String email, final String e, final String p, final String c, final String caminho) {
		controllerPesquisa=controll;
		framePrincipal=frameP;
        controlFoto = new ControllerFotografia();
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new InterfaceFotografia();
					frame.setVisible(true);
					frame.setSize(375, 200);
					frame.setLocation(475, 175);
					frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					lblEm.setText("Comentario");
					estabelecimento.setText(e);
					prato.setText(p);
					comentario.setText(c);
					btnUpload.setEnabled(false);
					user.setText(email);
					caminhoFoto=caminho;
					BufferedImage img=ImageIO.read(new File(caminhoFoto));
					Image resized = img.getScaledInstance(fotoPanel.getWidth(), fotoPanel.getHeight(), Image.SCALE_SMOOTH);
					ImageIcon icon=new ImageIcon(resized);
            		JLabel fotoLabel = new JLabel(icon);
            		fotoLabel.setHorizontalAlignment(JLabel.CENTER);

            		fotoPanel.add(fotoLabel, new GridBagConstraints());
            		fotoPanel.revalidate(); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
	
    public static void init(ControllerPesquisa controll, InterfacePesquisa frameP, final String email, final String e, final String p, final String c) {
    	controllerPesquisa=controll;
    	framePrincipal=frameP;
        controlFoto = new ControllerFotografia();
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new InterfaceFotografia();
					frame.setVisible(true);
					frame.setSize(375, 200);
					frame.setLocation(475, 175);
					frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					lblEm.setText("Comentario");
					estabelecimento.setText(e);
					btnApagar.setEnabled(false);
					prato.setText(p);
					comentario.setText(c);
					user.setText(email);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
    
    public void uploadFotografia(){
    	int comentID=0;
    	String email = (String) framePrincipal.getTable().getModel().getValueAt(framePrincipal.getTable().getSelectedRow(), 0);
    	String coment = (String) framePrincipal.getTable().getModel().getValueAt(framePrincipal.getTable().getSelectedRow(), 4);
    	controlFoto.uploadFotografia(controllerPesquisa, estabelecimento.getText(), prato.getText(), email, coment, file);
    	this.dispose();
    }


    public void delete(int idFoto){
        //boolean apagou = controlFoto.deleteFotografia(idFoto);
        /*if(apagou){
            this.setVisible(false);
        }*/
    }
}

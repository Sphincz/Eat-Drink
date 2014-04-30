/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fotografia;
import java.awt.Color;
import java.awt.EventQueue;
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

import Controller.InterfacePesquisa;

/**
 *
 * @author Nuno
 */
public class InterfaceFotografia extends JFrame{
	private static InterfaceFotografia frame;
	private File file;
	private final JPanel fotoPanel;
	
	public InterfaceFotografia() {
		setTitle("Eat&Drink - Adicionar fotografia");
		getContentPane().setLayout(null);
		
		JLabel lblColocadoPor = new JLabel("Colocado por:");
		lblColocadoPor.setBounds(25, 11, 67, 14);
		getContentPane().add(lblColocadoPor);
		
		JLabel lblSphincz = new JLabel("Sphincz");
		lblSphincz.setBounds(102, 11, 46, 14);
		getContentPane().add(lblSphincz);
		
		JLabel lblEm = new JLabel("Em:");
		lblEm.setBounds(74, 36, 18, 14);
		getContentPane().add(lblEm);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(102, 36, 46, 14);
		getContentPane().add(lblData);
		
		JLabel lblEstabelecimento = new JLabel("Estabelecimento:");
		lblEstabelecimento.setBounds(10, 61, 82, 14);
		getContentPane().add(lblEstabelecimento);
		
		JLabel lblBarDoToni = new JLabel("Bar do Toni");
		lblBarDoToni.setBounds(102, 61, 54, 14);
		getContentPane().add(lblBarDoToni);
		
		JLabel lblPrato = new JLabel("Prato:");
		lblPrato.setBounds(62, 86, 30, 14);
		getContentPane().add(lblPrato);
		
		JLabel lblMenuEspecial = new JLabel("Menu Especial");
		lblMenuEspecial.setBounds(102, 86, 67, 14);
		getContentPane().add(lblMenuEspecial);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.setBounds(10, 128, 89, 23);
		getContentPane().add(btnApagar);
		
		JButton btnUpload = new JButton("Upload");
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
		
		fotoPanel = new JPanel();
		fotoPanel.setBounds(260, 11, 89, 89);
		fotoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		fotoPanel.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int returnVal = fc.showOpenDialog(fotoPanel);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
	                file = fc.getSelectedFile();
	                
	                try {
	            		BufferedImage img=ImageIO.read(file);
	            		ImageIcon icon=new ImageIcon(img);
	            		
	            		JLabel fotoLabel = new JLabel(icon);

	            		fotoPanel.add(fotoLabel);
	            		fotoPanel.revalidate(); 
	            		fotoPanel.repaint();
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

    public static void init(InterfacePesquisa frameP) {
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
    	controlFoto.uploadFotografia(email, coment, file);
    	
    }


    public void delete(int idFoto){
        boolean apagou = controlFoto.deleteFotografia(idFoto);
        if(apagou){
            this.setVisible(false);
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fotografia;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Nuno
 */
public class InterfaceFotografia extends JFrame{
	private File file;
	
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
		
		final JPanel fotoPanel = new JPanel();
		fotoPanel.setBounds(260, 11, 89, 89);
		fotoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		fotoPanel.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int returnVal = fc.showOpenDialog(fotoPanel);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
	                file = fc.getSelectedFile();
	                System.out.println(file);
	                uploadFotografia();
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

    public static void init() {
        controlFoto = new ControllerFotografia();
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceFotografia frame = new InterfaceFotografia();
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
        controlFoto.uploadFotografia(comentID, file);
    }
    
    public void delete(int idFoto){
        boolean apagou = controlFoto.deleteFotografia(idFoto);
        if(apagou){
            this.setVisible(false);
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fotografia;
import java.awt.EventQueue;
import java.io.File;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.UIManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Nuno
 */
public class InterfaceFotografia extends JFrame{
	public InterfaceFotografia() {
		setTitle("Eat&Drink - Adicionar fotografia");
		getContentPane().setLayout(null);
		
		JLabel lblColocadoPor = new JLabel("Colocado por:");
		lblColocadoPor.setBounds(25, 40, 67, 14);
		getContentPane().add(lblColocadoPor);
		
		JLabel lblSphincz = new JLabel("Sphincz");
		lblSphincz.setBounds(102, 40, 46, 14);
		getContentPane().add(lblSphincz);
		
		JLabel lblEm = new JLabel("Em:");
		lblEm.setBounds(74, 65, 18, 14);
		getContentPane().add(lblEm);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(102, 65, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblEstabelecimento = new JLabel("Estabelecimento:");
		lblEstabelecimento.setBounds(10, 90, 82, 14);
		getContentPane().add(lblEstabelecimento);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(102, 89, 118, 17);
		getContentPane().add(comboBox);
		
		JLabel lblPrato = new JLabel("Prato:");
		lblPrato.setBounds(62, 115, 30, 14);
		getContentPane().add(lblPrato);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(102, 114, 118, 17);
		getContentPane().add(comboBox_1);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.setBounds(10, 153, 89, 23);
		getContentPane().add(btnApagar);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(260, 153, 89, 23);
		getContentPane().add(btnAdicionar);
		
		JLabel lblProcurarImagem = new JLabel("Procurar imagem:");
		lblProcurarImagem.setBounds(10, 15, 84, 14);
		getContentPane().add(lblProcurarImagem);
		
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
		
		final JButton btnAbrir = new JButton("Procurar...");
		btnAbrir.setBounds(102, 11, 85, 23);
		btnAbrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fc.showOpenDialog(btnAbrir);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                System.out.println(file);
				}
			}
		});
		getContentPane().add(btnAbrir);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(246, 11, 103, 114);
		getContentPane().add(textArea);
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
					frame.setSize(375, 225);
					frame.setLocation(350, 75);
					frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
    
    public void uploadFotografia(){
        File file=null;
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

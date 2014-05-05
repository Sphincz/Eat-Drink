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
import javax.swing.JOptionPane;
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
 * Classe InterfaceComentario:
 * Esta classe cria o GUI necessario para adicionar/ver/remover fotografia de comentários.
 * @author Nuno Coelho, Antonio Raimundo, Jose Serro, Diogo Peres
 */
public class InterfaceFotografia extends JFrame{
	/**
	 * janela do formulario
	 */
	private static InterfaceFotografia frame;
	
	/**
	 * ficheiro da imagem do comentário
	 */
	private File file;
	
	/**
	 * botão para apagar imagem do comentário
	 */
	private static JButton btnApagar;
	
	/**
	 * painel de previsualização da imagem, browse 
	 */
	private static JPanel fotoPanel=null;
	
	/**
	 * botão para carregar imagem para o comentário
	 */
	private static JButton btnUpload;
	
	/**
	 * label auxiliar para a data
	 */
	private static JLabel lblEm;
	
	/**
	 * label auxiliar para o user
	 */
	private static JLabel user;
	
	/**
	 * label auxiliar para o comentário
	 */
	private static JLabel comentario;
	
	/**
	 * label auxiliar para o estabelecimento
	 */
	private static JLabel estabelecimento;
	
	/**
	 * label auxiliar para o prato
	 */
	private static JLabel prato;
	
	/**
	 * string que guarda o caminho da foto
	 */
	private static String caminhoFoto;
	
	/**
	 * automático
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * instancia do controlador da fotografia
	 */
	private static ControllerFotografia controlFoto;
	
	/**
	 * instancia da interface de pesquisa
	 */
	@SuppressWarnings("unused")
	private static InterfacePesquisa framePrincipal;
	
	/**
	 * instancia do controlador de pesquisa
	 */
	private static ControllerPesquisa controllerPesquisa;
	
	/**
	 * construtor da classe: cria a interface gráfica e as sentinelas da janela
	 */
	
	/**
	 * auxiliar para indicar se pode-se carregar uma imagem
	 */
	private static boolean ver=false;
	
	private static boolean goodForUpload=false;
	
	/**
	 * Construtor: cria a interface da fotografia e as sentinelas da janela
	 */
	public InterfaceFotografia() {
		setTitle("Eat&Drink - Adicionar fotografia");
		getContentPane().setLayout(null);
		
		JLabel lblColocadoPor = new JLabel("Colocado por:");
		lblColocadoPor.setBounds(25, 11, 67, 14);
		getContentPane().add(lblColocadoPor);
		
		user = new JLabel("");
		user.setBounds(102, 11, 148, 14);
		getContentPane().add(user);
		
		lblEm = new JLabel("Coment\u00E1rio:");
		lblEm.setBounds(33, 36, 59, 14);
		getContentPane().add(lblEm);
		
		comentario = new JLabel("Data");
		comentario.setBounds(102, 36, 148, 14);
		getContentPane().add(comentario);
		
		JLabel lblEstabelecimento = new JLabel("Estabelecimento:");
		lblEstabelecimento.setBounds(10, 61, 82, 14);
		getContentPane().add(lblEstabelecimento);
		
		estabelecimento = new JLabel("Bar do Toni");
		estabelecimento.setBounds(102, 61, 148, 14);
		getContentPane().add(estabelecimento);
		
		JLabel lblPrato = new JLabel("Prato:");
		lblPrato.setBounds(62, 86, 30, 14);
		getContentPane().add(lblPrato);
		
		prato = new JLabel("Menu Especial");
		prato.setBounds(102, 86, 148, 14);
		getContentPane().add(prato);
		
		
		btnApagar = new JButton("Apagar");
		btnApagar.setBounds(10, 128, 89, 23);
		getContentPane().add(btnApagar);
		btnApagar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				delete();
			}
			
		});
		
		btnUpload = new JButton("Upload");
		btnUpload.setBounds(260, 128, 89, 23);
		getContentPane().add(btnUpload);
		btnUpload.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
                uploadFotografia();
                InterfacePesquisa.notFocused=false;
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
					if(!ver){
		                try {
		                	fotoPanel.removeAll();
		                	file = fc.getSelectedFile();
		            		BufferedImage img=ImageIO.read(file);
		            		Image resized = img.getScaledInstance(fotoPanel.getWidth(), fotoPanel.getHeight(), Image.SCALE_SMOOTH);
		            		ImageIcon icon=new ImageIcon(resized);
		            		
		            		JLabel fotoLabel = new JLabel(icon);
		            		fotoLabel.setHorizontalAlignment(JLabel.CENTER);
	
		            		fotoPanel.add(fotoLabel, new GridBagConstraints());
		            		fotoPanel.revalidate(); 
		            		goodForUpload=true;
		            	}
		            	catch(IOException e) {
		            		JOptionPane.showMessageDialog(null, "Erro! Imagem não encontrada!");
		            		InterfacePesquisa.notFocused=false;
		            		frame.dispose();
		            	}
		                System.out.println("[Fotografia] Caminho da imagem: "+file);
					}
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
	

	/**
	 * Método estatico que inicializa a classe, os seus atributos. Usado para ver/eliminar uma fotografia
	 * @param controll - recebe a instancia do controlador de pesquisa
	 * @param frameP - recebe a instancia da interface de pesquisa
	 * @param email - email do utilizador
	 * @param e - estabelecimento do comentário
	 * @param p - prato do comentário
	 * @param c - comentário
	 * @param caminho - caminho da foto
	 */
	public static void init2(ControllerPesquisa controll, InterfacePesquisa frameP, final String email, final String e, final String p, final String c, final String caminho) {
		controllerPesquisa=controll;
		framePrincipal=frameP;
		ver=true;
        controlFoto = new ControllerFotografia();
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new InterfaceFotografia();
					frame.setVisible(true);
					frame.setSize(375, 200);
					frame.setLocation(475, 175);
					frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					estabelecimento.setText(e);
					prato.setText(p);
					comentario.setText(c);
					btnUpload.setEnabled(false);
					user.setText(email);
					caminhoFoto=caminho;
					frame.setResizable(false);
					BufferedImage img=ImageIO.read(new File(caminhoFoto));
					Image resized = img.getScaledInstance(fotoPanel.getWidth(), fotoPanel.getHeight(), Image.SCALE_SMOOTH);
					ImageIcon icon=new ImageIcon(resized);
            		JLabel fotoLabel = new JLabel(icon);
            		fotoLabel.setHorizontalAlignment(JLabel.CENTER);
            		fotoPanel.add(fotoLabel, new GridBagConstraints());
            		fotoPanel.revalidate(); 
            		frame.addWindowListener(new java.awt.event.WindowAdapter() {
            		    @Override
            		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            		    	InterfacePesquisa.notFocused=false;
            		    	frame.dispose();
            		    }
            		});
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro! Imagem não encontrada!");
            		InterfacePesquisa.notFocused=false;
            		frame.dispose();
				}
			}
		});
    }
	
	/**
	 * Método estatico que inicializa a classe, os seus atributos. Usado para adicionar uma fotografia
	 * @param controll - recebe a instancia do controlador de pesquisa
	 * @param frameP - recebe a instancia da interface de pesquisa
	 * @param email - email do utilizador
	 * @param e - estabelecimento do comentário
	 * @param p - prato do comentário
	 * @param c - comentário
	 */
    public static void init(ControllerPesquisa controll, InterfacePesquisa frameP, final String email, final String e, final String p, final String c) {
    	controllerPesquisa=controll;
    	framePrincipal=frameP;
    	ver=false;
        controlFoto = new ControllerFotografia();
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new InterfaceFotografia();
					frame.setVisible(true);
					frame.setSize(375, 200);
					frame.setLocation(475, 175);
					frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					estabelecimento.setText(e);
					btnApagar.setEnabled(false);
					frame.setResizable(false);
					prato.setText(p);
					comentario.setText(c);
					user.setText(email);
					frame.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					    	InterfacePesquisa.notFocused=false;
					    	frame.dispose();
					    }
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
    
    /**
     * carregar/inserir fotografia no comentário - base de dados
     */
    public void uploadFotografia(){
    	if(goodForUpload){
	    	@SuppressWarnings("unused")
			int comentID=0;
	    	String email = (String) InterfacePesquisa.getTable().getModel().getValueAt(InterfacePesquisa.getTable().getSelectedRow(), 0);
	    	String coment = (String) InterfacePesquisa.getTable().getModel().getValueAt(InterfacePesquisa.getTable().getSelectedRow(), 4);
	    	controlFoto.uploadFotografia(controllerPesquisa, estabelecimento.getText(), prato.getText(), email, coment, file);
	    	InterfacePesquisa.notFocused=false;
			frame.dispose();
    	}else{
    		JOptionPane.showMessageDialog(null, "Carrege uma imagem primeiro!");
    	}
    }

    /**
     * eliminar foto existente no comentário
     */
    public void delete(){
    	controlFoto.deleteFotografia(user.getText(), comentario.getText(), estabelecimento.getText(), prato.getText());
		InterfacePesquisa.notFocused=false;
		frame.dispose();
    }
}

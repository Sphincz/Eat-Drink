package Controller;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InterfacePesquisa extends JFrame {
	
	
	public InterfacePesquisa() {
		getContentPane().setLayout(null);
		
		JLabel lblUtilizador = new JLabel("Utilizador:");
		lblUtilizador.setBounds(20, 26, 50, 14);
		getContentPane().add(lblUtilizador);
		
		textField = new JTextField();
		textField.setBounds(80, 23, 147, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEstabelecimento = new JLabel("Estabelecimento:");
		lblEstabelecimento.setBounds(261, 26, 82, 14);
		getContentPane().add(lblEstabelecimento);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(353, 23, 170, 20);
		getContentPane().add(textField_1);
		
		JLabel lblPrato = new JLabel("Prato:");
		lblPrato.setBounds(40, 61, 30, 14);
		getContentPane().add(lblPrato);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(80, 58, 147, 20);
		getContentPane().add(textField_2);
		
		JLabel lblAvaliao = new JLabel("Avalia\u00E7\u00E3o >=");
		lblAvaliao.setBounds(271, 61, 68, 14);
		getContentPane().add(lblAvaliao);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(353, 58, 78, 20);
		getContentPane().add(spinner);
		
		JLabel lblFotografia = new JLabel("Fotografia:");
		lblFotografia.setBounds(449, 61, 58, 14);
		getContentPane().add(lblFotografia);
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setBounds(502, 56, 21, 23);
		getContentPane().add(checkBox);
		
		JLabel lblComentrio = new JLabel("Coment\u00E1rio:");
		lblComentrio.setBounds(10, 95, 61, 14);
		getContentPane().add(lblComentrio);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(80, 92, 147, 20);
		getContentPane().add(textField_3);
	
	}
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private ControllerPesquisa controlPesquisa;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public void execute() {
		controlPesquisa = new ControllerPesquisa();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacePesquisa frame = new InterfacePesquisa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
    public void createComentario(){
        controlPesquisa.createComentario();
    }
    
    public void addFotografia(){
        controlPesquisa.addFotografia();
    }

	/**
	 * Create the frame.
	 */
	public void initWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
}

package Controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class InterfacePesquisa extends JFrame {
	
	
	public InterfacePesquisa() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		getContentPane().setLayout(null);
		
		JLabel lblUtilizador = new JLabel("Utilizador:");
		lblUtilizador.setBounds(22, 26, 48, 14);
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
		
		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setBounds(291, 95, 52, 14);
		getContentPane().add(lblPesquisar);
		
		JButton btnEstabelecimento = new JButton("Estabelecimento");
		btnEstabelecimento.setBounds(353, 91, 110, 23);
		getContentPane().add(btnEstabelecimento);
		
		JButton btnPrato = new JButton("Prato");
		btnPrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPrato.setBounds(463, 91, 60, 23);
		getContentPane().add(btnPrato);
		
		JButton btnNewButton = new JButton("Ver fotografia");
		btnNewButton.setBounds(22, 478, 170, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnVer = new JButton("Ver coment\u00E1rio");
		btnVer.setBounds(22, 444, 170, 23);
		getContentPane().add(btnVer);
		
		JButton btnAdicionarComentrio = new JButton("Adicionar coment\u00E1rio");
		btnAdicionarComentrio.setBounds(227, 444, 170, 23);
		getContentPane().add(btnAdicionarComentrio);
		
		JButton btnAdicionarFotografia = new JButton("Adicionar fotografia");
		btnAdicionarFotografia.setBounds(227, 478, 170, 23);
		getContentPane().add(btnAdicionarFotografia);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(463, 478, 60, 23);
		getContentPane().add(btnSair);

		String[] columnNames = {"Utilizador",
                "Estabelecimento",
                "Prato",
                "Avaliação",
                "Comentário",
                "Fotografia"};

		Object[][] data = {
				{"João Camexias", "Toni",
					"Snowboarding", new Integer(5), "É uma merda!",  new Boolean(false)},
					{"Adolfo Dias", "Toni",
						"Rowing", new Integer(3), "É uma merda!", new Boolean(true)},
						{"Oscar Alho", "Toni",
							"Knitting", new Integer(2), "É uma merda!", new Boolean(false)},
							{"Jagodes Temivel", "Toni",
								"Speed reading", new Integer(20), "É uma merda!", new Boolean(true)},
								{"E eu", "Toni",
									"Pool", new Integer(10), "É uma merda!", new Boolean(false)}
		};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 133, 490, 289);
		getContentPane().add(scrollPane);
		

		table = new JTable(data, columnNames);
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
	}
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private ControllerPesquisa controlPesquisa;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

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
					frame.setSize(550, 550);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
}

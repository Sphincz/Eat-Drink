package Controller;

import APIs.APIUtilizadores;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import Comentarios.InterfaceComentario;
import Fotografia.InterfaceFotografia;

public class InterfacePesquisa extends JFrame implements APIUtilizadores{
	
	
	public InterfacePesquisa() {
		setTitle("Eat&Drink - Pesquisar");
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
		
		user = new JTextField();
		user.setBounds(80, 23, 200, 20);
		getContentPane().add(user);
		user.setColumns(10);
		
		JLabel lblEstabelecimento = new JLabel("Estabelecimento:");
		lblEstabelecimento.setBounds(334, 29, 82, 14);
		getContentPane().add(lblEstabelecimento);
		
		estabelecimento = new JTextField();
		estabelecimento.setColumns(10);
		estabelecimento.setBounds(426, 26, 185, 20);
		getContentPane().add(estabelecimento);
		
		JLabel lblPrato = new JLabel("Prato:");
		lblPrato.setBounds(40, 61, 30, 14);
		getContentPane().add(lblPrato);
		
		prato = new JTextField();
		prato.setColumns(10);
		prato.setBounds(80, 58, 200, 20);
		getContentPane().add(prato);
		
		JLabel lblAvaliao = new JLabel("Avaliação >=");
		lblAvaliao.setBounds(344, 61, 68, 14);
		getContentPane().add(lblAvaliao);
		
		JLabel lblFotografia = new JLabel("Fotografia:");
		lblFotografia.setBounds(530, 61, 58, 14);
		getContentPane().add(lblFotografia);
		
		fotografia = new JCheckBox("");
		fotografia.setBounds(590, 57, 21, 23);
		getContentPane().add(fotografia);
		
		JLabel lblComentrio = new JLabel("Comentário:");
		lblComentrio.setBounds(10, 95, 61, 14);
		getContentPane().add(lblComentrio);
		
		comentario = new JTextField();
		comentario.setColumns(10);
		comentario.setBounds(80, 92, 200, 20);
		getContentPane().add(comentario);
		
		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setBounds(364, 95, 52, 14);
		getContentPane().add(lblPesquisar);
		
		SpinnerModel model = new SpinnerNumberModel(3, 0, 5, 1);
		avaliacao = new JSpinner(model);
		avaliacao.setBounds(426, 57, 82, 20);
		getContentPane().add(avaliacao);
		
		JButton btnEstabelecimento = new JButton("Estabelecimento");
		btnEstabelecimento.setBounds(426, 91, 110, 23);
		btnEstabelecimento.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ControllerPesquisa controllerPes = new ControllerPesquisa();
				controllerPes.searchEstabelecimento(user.getText(), estabelecimento.getText(), prato.getText(), 
						(int) avaliacao.getValue(), fotografia.isSelected(), comentario.getText());
			}	
		});
		getContentPane().add(btnEstabelecimento);
		
		JButton btnPrato = new JButton("Prato");
		btnPrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPrato.setBounds(547, 91, 64, 23);
		getContentPane().add(btnPrato);
		
		JButton btnNewButton = new JButton("Ver fotografia");
		btnNewButton.setBounds(22, 478, 170, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnVer = new JButton("Ver comentário");
		btnVer.setBounds(22, 444, 170, 23);
		getContentPane().add(btnVer);
		
		JButton btnAdicionarComentrio = new JButton("Adicionar comentário");
		btnAdicionarComentrio.setBounds(220, 444, 170, 23);
		btnAdicionarComentrio.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				InterfaceComentario.init();
			}
		});
		getContentPane().add(btnAdicionarComentrio);
		
		JButton btnAdicionarFotografia = new JButton("Adicionar fotografia");
		btnAdicionarFotografia.setBounds(220, 478, 170, 23);
		btnAdicionarFotografia.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				InterfaceFotografia.init();
			}
		});
		getContentPane().add(btnAdicionarFotografia);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(551, 478, 60, 23);
		btnSair.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		getContentPane().add(btnSair);
		
		String[] columnNames = {"Utilizador",
                "Estabelecimento",
                "Prato",
                "Avaliação",
                "Comentário",
                "Fotografia"};

		
		Object[][] data = {
				{"João Ribeiro", "Toni",
					"Snowboarding", new Integer(5), "Bom!",  new Boolean(true)},
					{"Adolfo Rodrigues", "Toni",
						"Rowing", new Integer(3), "Bom!", new Boolean(true)},
						{"Oscar Dias", "Toni",
							"Knitting", new Integer(2), "Bom!", new Boolean(false)},
							{"Jagodes Temivel", "Toni",
								"Speed reading", new Integer(20), "Bom!", new Boolean(true)},
								{"E eu", "Toni",
									"Pool", new Integer(10), "Bom!", new Boolean(false)}
		};

		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
			private static final long serialVersionUID = 1L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		        //all cells false
		        return false;
		    }
			@Override
			public Class<?> getColumnClass(int columnIndex) {
			    if (columnIndex == 5)
			        return Boolean.class;
			    return super.getColumnClass(columnIndex);
			}
		};
		table = new JTable(tableModel);
		table.setFillsViewportHeight(true);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 133, 590, 289);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);

	}
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private ControllerPesquisa controlPesquisa;
	private JTextField user;
	private JTextField estabelecimento;
	private JTextField prato;
	private JTextField comentario;
	private JSpinner avaliacao;
	private JCheckBox fotografia;
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
					frame.setSize(650, 550);
					frame.setLocation(350, 75);
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

    @Override
    public boolean uploadFotografiaUtilizador(String emailUtilizador) {
        InterfaceUpload upload = new InterfaceUpload();
        upload.init();
        return true;
    }
}

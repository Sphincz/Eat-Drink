package Controller;

import APIs.APIUtilizadores;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import Comentarios.ComentarioEstabelecimento;
import Comentarios.ComentarioPrato;
import Comentarios.InterfaceComentario;
import Pratos.Prato;
import Suporte.TipoComentario;
import Suporte.TipoFotografia;
import Utilizador.Utilizador;

import javax.swing.JTextField;

public class InterfacePesquisa extends JFrame implements APIUtilizadores{
	
	private static final long serialVersionUID = 1L;
	private String PROJECT_NAME = "Eat&Drink";
	private JPanel contentPane;
	private ControllerPesquisa controlPesquisa;
	private JComboBox<String> user;
	private static JComboBox<String> estabelecimento;
	private JComboBox<String> prato;
	private JSpinner avaliacao;
	private JCheckBox fotografia;
	private static JTable table;
	private JTextField comentario;
	private static InterfacePesquisa frame;
	private static DefaultTableModel tableModel;
	private static String[] columnNames = {"Utilizador", "Estabelecimento", "Prato", "Avalia��o", "Coment�rio", "Fotografia"};
	public static TipoComentario tipoComentario;
	private ControllerPesquisa controllerPes = new ControllerPesquisa();
	private Vector<String> v;
	private Vector<String> v1;
	private Vector<String> v3;
	public static boolean notFocused=false;

	public InterfacePesquisa() {
		setTitle(PROJECT_NAME+" - Pesquisar");
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
		
		ControllerPesquisa controllUsers = new ControllerPesquisa();
		ArrayList<Utilizador> listaU = controllUsers.getAllUsers();
		v3 = new Vector<String>();
		v3.add("*");
		for (int i = 0; i < listaU.size(); i++) {
			v3.add(listaU.get(i).getNome());
		}
		user = new JComboBox<String>(v3);
		user.setBounds(80, 23, 200, 20);
		getContentPane().add(user);
		
		JLabel lblEstabelecimento = new JLabel("Estabelecimento:");
		lblEstabelecimento.setBounds(334, 29, 82, 14);
		getContentPane().add(lblEstabelecimento);
		

		ArrayList<Estabelecimento> listaE = controllerPes.setWindowData();
		v = new Vector<String>();
		v.add("*");
		for (int i = 0; i < listaE.size(); i++) {
			v.add(listaE.get(i).getDesignacao());
		}
		estabelecimento = new JComboBox<String>(v);
		estabelecimento.setBounds(426, 26, 185, 20);
		getContentPane().add(estabelecimento);
		
		JLabel lblPrato = new JLabel("Prato:");
		lblPrato.setBounds(40, 61, 30, 14);
		getContentPane().add(lblPrato);
		
		ArrayList<Prato> listaP = controllerPes.setWindowDataPratos();
		v1 = new Vector<String>();
		v1.add("*");
		for (int i = 0; i < listaP.size(); i++) {
			v1.add(listaP.get(i).getDescricao());
		}
		prato = new JComboBox<String>(v1);
		prato.setBounds(80, 58, 200, 20);
		getContentPane().add(prato);
		
		JLabel lblAvaliao = new JLabel("Avalia��o >=");
		lblAvaliao.setBounds(344, 61, 68, 14);
		getContentPane().add(lblAvaliao);
		
		JLabel lblFotografia = new JLabel("Fotografia:");
		lblFotografia.setBounds(530, 61, 58, 14);
		getContentPane().add(lblFotografia);
		
		fotografia = new JCheckBox("");
		fotografia.setBounds(590, 57, 21, 23);
		getContentPane().add(fotografia);
		
		JLabel lblComentrio = new JLabel("Coment�rio:");
		lblComentrio.setBounds(10, 95, 61, 14);
		getContentPane().add(lblComentrio);
		
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
				ControllerPesquisa controlPes2 = new ControllerPesquisa();
				controlPes2.searchEstabelecimento(user.getSelectedItem().toString(), estabelecimento.getSelectedItem().toString(), 
						prato.getSelectedItem().toString(), (int) avaliacao.getValue(), fotografia.isSelected(), comentario.getText());
				tipoComentario = TipoComentario.ESTABELECIMENTO;
			}	
		});
		getContentPane().add(btnEstabelecimento);
		
		JButton btnPrato = new JButton("Prato");
		btnPrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controllerPes.searchPrato(user.getSelectedItem().toString(), estabelecimento.getSelectedItem().toString(), 
						prato.getSelectedItem().toString(), (int) avaliacao.getValue(), fotografia.isSelected(), comentario.getText());
				tipoComentario = TipoComentario.PRATO;
			}
		});
		btnPrato.setBounds(547, 91, 64, 23);
		getContentPane().add(btnPrato);
		
		comentario = new JTextField();
		comentario.setBounds(80, 92, 200, 20);
		getContentPane().add(comentario);
		comentario.setColumns(10);
		
		JButton btnNewButton = new JButton("Ver fotografia");
		btnNewButton.setBounds(22, 478, 170, 23);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1 && !notFocused) {
					if(table.getModel().getValueAt(table.getSelectedRow(), 5).equals(true)){
						viewFotografia();
						notFocused=true;
					}else{
						JOptionPane.showMessageDialog(null, "Coment�rio sem foto");
					}
				}else{
					if(notFocused) JOptionPane.showMessageDialog(null, "J� est� a visualizar uma fotografia");
					else JOptionPane.showMessageDialog(null, "Por favor, seleccione um coment�rio v�lido.");
				}
			}
		});
		
		JButton btnVer = new JButton("Ver coment�rio");
		btnVer.setBounds(22, 444, 170, 23);
		getContentPane().add(btnVer);
		btnVer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1 && !notFocused) {
					notFocused=true;
					ControllerPesquisa controller = new ControllerPesquisa();
					controller.viewComentario( frame,
							tipoComentario,
							table.getModel().getValueAt(table.getSelectedRow(),
									0).toString(),
							table.getModel().getValueAt(table.getSelectedRow(),
									3).toString(),
							table.getModel().getValueAt(table.getSelectedRow(),
									4).toString(), table.getModel().getValueAt(table.getSelectedRow(),
											1).toString(), table.getModel().getValueAt(table.getSelectedRow(),
													2).toString());
				}else{
					if(notFocused)JOptionPane.showMessageDialog(null, "J� est� a visualizar um coment�rio");
					else JOptionPane.showMessageDialog(null, "Por favor, seleccione um coment�rio v�lido");
				}
			}

		});

		
		JButton btnAdicionarComentrio = new JButton("Adicionar coment�rio");
		btnAdicionarComentrio.setBounds(220, 444, 170, 23);
		btnAdicionarComentrio.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					if(!notFocused){
						notFocused=true;
						InterfaceComentario.init(frame, true, tipoComentario, "", "", "", "", "");
					}else{
						if(notFocused)JOptionPane.showMessageDialog(null, "J� est� a adicionar um coment�rio");
					}
					
				
			}
		});
		getContentPane().add(btnAdicionarComentrio);
		
		JButton btnAdicionarFotografia = new JButton("Adicionar fotografia");
		btnAdicionarFotografia.setBounds(220, 478, 170, 23);
		btnAdicionarFotografia.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1 && !notFocused){
					if(table.getModel().getValueAt(table.getSelectedRow(), 5).equals(false)){
						addFotografia();
						notFocused=true;
					}else{
							JOptionPane.showMessageDialog(null, "Coment�rio j� tem fotografia", PROJECT_NAME+" - Selec��o inv�lida", JOptionPane.INFORMATION_MESSAGE);
					}
				}else
					if(notFocused) JOptionPane.showMessageDialog(null, "J� est� a adicionar uma fotografia");
					else JOptionPane.showMessageDialog(null, "Para adicionar uma fotografia, por favor selecione um coment�rio.", PROJECT_NAME+" - Selec��o inv�lida", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		getContentPane().add(btnAdicionarFotografia);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(551, 478, 60, 23);
		getContentPane().add(btnSair);
		btnSair.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		tableModel = new DefaultTableModel(null, columnNames) {
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
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 133, 590, 289);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);

	}

	/**
	 * Launch the application.
	 */
	public void execute() {
		controlPesquisa = new ControllerPesquisa();
		EventQueue.invokeLater(new Runnable() {
			

			public void run() {
				try {
					frame = new InterfacePesquisa();
					frame.setVisible(true);
					frame.setSize(650, 550);
					frame.setLocation(350, 75);
					frame.setResizable(false);
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
    
    public ControllerPesquisa getControllerPes() {
		return controllerPes;
	}

	public void addFotografia(){
    	controlPesquisa = new ControllerPesquisa();
    	controlPesquisa.addFotografia(frame, table.getModel().getValueAt(table.getSelectedRow(), 0).toString(), table.getModel().getValueAt(table.getSelectedRow(), 1).toString(), table.getModel().getValueAt(table.getSelectedRow(), 2).toString(), table.getModel().getValueAt(table.getSelectedRow(), 4).toString());
    }
    
    private void viewFotografia() {
    	ControllerPesquisa control = new ControllerPesquisa();
    	control.viewFotografia(frame, table.getModel().getValueAt(table.getSelectedRow(), 0).toString(), table.getModel().getValueAt(table.getSelectedRow(), 1).toString(), table.getModel().getValueAt(table.getSelectedRow(), 2).toString(), table.getModel().getValueAt(table.getSelectedRow(), 4).toString());
		
	}
    

	public static JTable getTable() {
		return table;
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
	
	

    public static TipoComentario getTipoComentario() {
		return tipoComentario;
	}

	@Override
    public boolean uploadFotografiaUtilizador(String emailUtilizador) {
        InterfaceUpload upload = new InterfaceUpload();
        upload.init();
        return true;
    }


	public static void preencherPesquisa(ArrayList<Estabelecimento> listaEstabelecimentos, ArrayList<ComentarioEstabelecimento> listaComentariosEstabelecimento) {
		tableModel.setRowCount(0);
		int i;
		for (i = 0; i < listaComentariosEstabelecimento.size(); i++) {
			boolean hasFoto=false;
			if(listaComentariosEstabelecimento.get(i).getIdFoto()>0){
				hasFoto=true;
			}
			Object[] data = {
				listaComentariosEstabelecimento.get(i).getUserID(),
				getEstabelecimentoNomeByComentID(listaEstabelecimentos, listaComentariosEstabelecimento.get(i).getIdEstabelecimento()),
				" ---- ", listaComentariosEstabelecimento.get(i).getNota(),
				listaComentariosEstabelecimento.get(i).getComentario(),
				hasFoto
			};
			
			tableModel.addRow(data);
		}
		System.out.println("[E&D] Pesquisa por estabelecimentos. Encontrados "+i+" resultados");
		
	}


	private static Object getEstabelecimentoNomeByComentID(ArrayList<Estabelecimento> listaEstabelecimentos, int idEstabelecimento) {
		for (int i = 0; i < listaEstabelecimentos.size(); i++) {
			if(listaEstabelecimentos.get(i).getId()==idEstabelecimento){
				return listaEstabelecimentos.get(i).getDesignacao();
			}
		}
		return "---";
	}


	public static void preencherPesquisaPratos(ArrayList<Prato> listaPratos, ArrayList<ComentarioPrato> listComentariosPrato, ArrayList<Estabelecimento> listaEstabelecimentos) {
		tableModel.setRowCount(0);
		int i;
		for (i = 0; i < listComentariosPrato.size(); i++) {
			boolean hasFoto=false;
			if(listComentariosPrato.get(i).getIdFoto()>0){
				hasFoto=true;
			}
			Object[] data = {
					listComentariosPrato.get(i).getEmail(),
					estabelecimento.getSelectedItem().toString(),
					getPratoNomeByComentID(listaPratos, listComentariosPrato.get(i).getId()),
					listComentariosPrato.get(i).getNota(),
				listComentariosPrato.get(i).getComentario(),
				hasFoto
			};
		tableModel.addRow(data);
		}
		System.out.println("[E&D] Pesquisa por pratos. Encontrados "+i+" resultados");
	}


	private static Object getPratoNomeByComentID(ArrayList<Prato> listaPratos, int id) {
		for (int i = 0; i < listaPratos.size(); i++) {
			if(listaPratos.get(i).getId()==id){
				return listaPratos.get(i).getDescricao();
			}
		}
		return "---";
	}

	public Vector<String> getV() {
		return v;
	}

	public Vector<String> getV1() {
		return v1;
	}

	public Vector<String> getV3() {
		return v3;
	}

	@Override
	public void viewFotografia(int idFotografia, TipoFotografia tipo) {
		//n�o implementado por n�o estar especificado
	}

	
	
}

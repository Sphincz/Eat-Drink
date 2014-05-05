package Comentarios;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.InterfacePesquisa;
import Suporte.TipoComentario;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;

/**
 * Classe InterfaceComentario.
 * Esta classe cria o GUI necessario para adicionar/ver/remover comentarios.
 *
 * @author Nuno Coelho, Antonio Raimundo, Jose Serro, Diogo Peres
 */
public class InterfaceComentario extends JFrame{
	
	/** a serialVersionUID auto-adicionada. */
	private static final long serialVersionUID = 1L;
	
	/** o controlador do comentario. */
	private static ControllerComentario controlComent;
	
	/** a janela/frame. */
	private static InterfaceComentario frame;
	
	/** o estabelecimento. */
	private static JLabel estabelecimento;
	
	/** o username. */
	private static JLabel username;
	
	/** o prato. */
	private static JLabel prato;
	
	/** a avaliacao. */
	private static JSpinner avaliacao;
	
	/** o comentario. */
	private static JTextArea comentario;
	
	/** o botao de apagar. */
	private static JButton btnApagar;
	
	/** o botao para gravar alteraes. */
	private static JButton btnGravarAlteraes;
	
	/** o tipo de comentario. */
	private static TipoComentario tipo;
	
	/** a janela/frame principal. */
	@SuppressWarnings("unused")
	private static InterfacePesquisa framePrincipal;
	
	/** variavel que verifica se se trada de um comentario novo */
	private static boolean novo;
	
	/** a lista de estabelecimentos. */
	private JComboBox<String> estabelecimentos;
	
	/** a lista de pratos. */
	private JComboBox<String> pratos;
	
	/** a lista de users. */
	private JComboBox<String> users;

    /**
     * Instancia a classe e cria o GUI de adicionar/ver comentario.
     * Todos os Swing Components estao definidos e identificados.
     * Sao exibidas mensagens de aviso/erro/informacao caso o utilizador execute alguma operacao que nao e esperada.
     *
     * @param controll o controlador do comentario
     * @param frameP a janela/frame principal
     */
    public InterfaceComentario(ControllerComentario controll, InterfacePesquisa frameP){
    	controlComent=controll;
    	getContentPane().setLayout(null);
    	
    	JLabel lblColocadoPor = new JLabel("Colocado por:");
    	lblColocadoPor.setBounds(35, 20, 67, 14);
    	getContentPane().add(lblColocadoPor);
    	
    	username = new JLabel("Sphincz");
    	username.setBounds(112, 20, 400, 14);
    	users = new JComboBox<String>(frameP.getV3());
    	users.setBounds(112, 20, 200, 14);
    	if(!novo)getContentPane().add(username);
    	else getContentPane().add(users);
    	
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
    	estabelecimentos = new JComboBox<String>(frameP.getV());
    	estabelecimentos.setBounds(112, 70, 80, 14);
    	estabelecimento.setBounds(112, 70, 54, 14);
    	if(!novo)getContentPane().add(estabelecimento);
    	else getContentPane().add(estabelecimentos);
    	estabelecimentos.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!estabelecimentos.getSelectedItem().equals("*")){
					pratos.setSelectedIndex(0);
					pratos.setEnabled(false);
				}else{
					pratos.setEnabled(true);
				}
			}
    		
    	});
    	
    	JLabel lblPrato = new JLabel("Prato:");
    	lblPrato.setBounds(200, 70, 46, 14);
    	getContentPane().add(lblPrato);
    	
    	
    	prato = new JLabel("Menu Especial");
    	prato.setBounds(230, 70, 67, 14);
    	pratos =  new JComboBox<String>(frameP.getV1());
    	pratos.setBounds(232, 70, 82, 14);
    	if(!novo) getContentPane().add(prato);
    	else getContentPane().add(pratos);
    	pratos.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!pratos.getSelectedItem().equals("*")){
					estabelecimentos.setSelectedIndex(0);
					estabelecimentos.setEnabled(false);
				}else{
					estabelecimentos.setEnabled(true);
				}
			}
    		
    	});
    	
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
    	if(novo) btnGravarAlteraes.setText("Adicionar novo");
    	btnGravarAlteraes.setBounds(10, 253, 119, 23);
    	getContentPane().add(btnGravarAlteraes);
    	btnGravarAlteraes.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int aux=0;
				if(novo){
					if(users.getSelectedItem().equals("*")){
						JOptionPane.showMessageDialog(null, "Por favor, seleccione um utilizador válido");
						aux=1;
					}else{
						if(pratos.getSelectedItem().equals("*") && estabelecimentos.getSelectedItem().equals("*")){
							JOptionPane.showMessageDialog(null, "Por favor, seleccione um estabelecimento OU um prato e um estabelecimento.");
							aux=1;
						}
					}
				}
				if(aux==0){
					boolean isSaved=false;
					avaliacao.setValue(avaliacao.getValue());
					if(novo){
						if(pratos.getSelectedItem().equals("*")){
							tipo=TipoComentario.ESTABELECIMENTO;
						}else{
							tipo=TipoComentario.PRATO;
						}
						isSaved=save(tipo, estabelecimentos.getSelectedItem().toString(), pratos.getSelectedItem().toString(), users.getSelectedItem().toString(), comentario.getText(), Integer.parseInt(avaliacao.getValue().toString()));
						
					}else{
						isSaved=save(tipo, estabelecimento.getText(), prato.getText(), username.getText(), comentario.getText(), Integer.parseInt(avaliacao.getValue().toString()));
					}
					if(isSaved && !novo){
						JOptionPane.showMessageDialog(null, "Alterações feitas. Comentário gravado.");
					}else{
						if(!isSaved){
							JOptionPane.showMessageDialog(null, "Erro!");
						}else{
							JOptionPane.showMessageDialog(null, "Alterações feitas. Comentário adicionado com sucesso.");
						}
					}
					InterfacePesquisa.notFocused=false;
					frame.dispose();
				}
				
			}
    	});
    	
    	btnApagar = new JButton("Apagar");
    	btnApagar.setBounds(171, 253, 67, 23);
    	getContentPane().add(btnApagar);
    	btnApagar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				delete(tipo, estabelecimento.getText(), prato.getText(), username.getText(), comentario.getText(), Integer.parseInt(avaliacao.getValue().toString()));
				
			}
    		
    	});
    	if(novo) btnApagar.setEnabled(false);
    	JButton btnSair = new JButton("Sair");
    	btnSair.setBounds(280, 253, 51, 23);
    	btnSair.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				InterfacePesquisa.notFocused=false;
		    	frame.dispose();
		    	}
    	});
    	getContentPane().add(btnSair);
        //InterfaceComentario.controlComent=controlComent;
    }
    
    /**
     * Inicia a GUI de adicionar/ver comentario.
     * Aqui sao ajustados os parametros referentes a janela/frame da GUI de comentarios.
     *
     * @param frameP a janela/frame principal
     * @param novoComent o novo comentario
     * @param t o tipo de comentario
     * @param name o nome do user
     * @param a a avaliacao
     * @param c o comentario
     * @param p o prato
     * @param e o estabelecimento
     */
    public static void init(final InterfacePesquisa frameP, final boolean novoComent, final TipoComentario t, final String name, final String a, final String c, final String p, final String e) {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					novo=novoComent;
					frame = new InterfaceComentario(controlComent, frameP);
					frame.setVisible(true);
					frame.setSize(375, 325);
					frame.setLocation(475, 175);
					frame.setResizable(false);
					frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					estabelecimento.setText(e);
					username.setText(name);
					prato.setText(p);
					comentario.setText(c);
					tipo=t;
					framePrincipal=frameP;
					if(!novo){
						frame.setTitle("Eat&Drink - Ver/Editar comentario");
						avaliacao.setValue(Integer.parseInt(a));
					}else{
						frame.setTitle("Eat&Drink - Adicionar comentario");
					}
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
     * Insere um comentario.
     * Estabelece comunicacao com a classe controladora dos comentarios (ControllerComentario).
     *
     * @param tipo o tipo de comentario
     * @param e o estabelecimento
     * @param p o prato
     * @param user o user
     * @param comentario o comentario
     * @param nota a avaliacao
     * @return true, se bem sucedido
     */
    public static boolean save(TipoComentario tipo, String e, String p, String user, String comentario, int nota){
    	ControllerComentario controlComent = new ControllerComentario();
    	return controlComent.save(tipo, e, p, user, comentario, nota);
    }
    
    /**
     * Elimina um comentario.
     * Estabelece comunicacao com a classe controladora dos comentarios (ControllerComentario).
     *
 	 * @param tipo o tipo de comentario
     * @param e o estabelecimento
     * @param p o prato
     * @param user o user
     * @param coment o comentario
     * @param nota a avaliacao
     *
     */
    public void delete(TipoComentario tipo, String e, String p, String user, String coment, int nota){
    	ControllerComentario controlComent = new ControllerComentario();
    	boolean apagou = controlComent.delete(tipo, p, e, user, coment, nota);
        if(apagou){
        	JOptionPane.showMessageDialog(null, "Comentário apagado com sucesso!");
        	InterfacePesquisa.notFocused=false;
	    	frame.dispose();
        }else{
        	JOptionPane.showMessageDialog(null, "Erro!");
        	InterfacePesquisa.notFocused=false;
	    	frame.dispose();
        }
    }
    
}

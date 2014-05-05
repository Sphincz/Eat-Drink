package BaseDados;

import Comentarios.ComentarioEstabelecimento;
import Comentarios.ComentarioPrato;
import Controller.ControllerPesquisa;
import Controller.ControllerUpload;
import Controller.Estabelecimento;
import Controller.InterfacePesquisa;
import Fotografia.Fotografia;
import Pratos.Prato;
import Suporte.TipoComentario;
import Utilizador.Utilizador;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Classe DBConnector.
 * Esta classe efectua operaçoes de pesquisa, introducao e remocao de conteudo na base de dados
 *
 * @author Nuno Coelho, Antonio Raimundo, Jose Serro, Diogo Peres
 */


public class DBConnector {
	
	/** O resultado de uma query. */
	private ResultSet result; 
	
	/** Uma query de acesso a base de dados. */
	private Statement statement;
	
	/** O URL para estabelecer ligacao a base de dados. localhost2638 significa que o servidor da base de dados e local, ligado na porta 2638. O nome da base de dados e EatDrink. */
	private String dburl = "jdbc:sqlanywhere:Tds:localhost:2638?eng=EatDrink";
	
	/** O username e password de acesso a base de dados */
	private String user = "dba", password = "sql";
	
	/** A ligacao a base de dados. */
	private Connection con;
	
	/**
	 * Construtor que cria e estabelece uma ligacao ao servidor da base de dados.
	 */
	public DBConnector() {
        // Connect to Sybase Database
        try {
			con = DriverManager.getConnection(dburl, user, password);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Base de dados desligada ou username e/ou password inválidos!");
			System.exit(0);
			
		}
	}

	

    /**
     * Insere um comentario ao estabelecimento se ja existir algum nesse estabelecimento. 
     * Caso nao exista, cria um novo comentario para o mesmo estabelecimento.
     *
     * @param estabelecimento o estabelecimento
     * @param user o user
     * @param comentario o comentario
     * @param nota a avaliacao
     * @return true, se bem sucedido
     */
    public boolean inserirComentarioEstabelecimento(String estabelecimento, String user, String comentario, int nota) {
    	try {
			statement = con.createStatement();
			result = statement.executeQuery("SELECT Estabelecimento.idEstabelecimento, ComentarioAoEstabelecimento.email, ComentarioAoEstabelecimento.comentario, ComentarioAoEstabelecimento.nota FROM ComentarioAoEstabelecimento, Estabelecimento WHERE Estabelecimento.designacao='"+estabelecimento+"' AND Estabelecimento.idEstabelecimento=ComentarioAoEstabelecimento.idEstabelecimento AND ComentarioAoEstabelecimento.email='"+user+"'");
         	if(result.next()){//update
         		int id=Integer.parseInt(result.getString("idEstabelecimento"));
         		result = statement.executeQuery("UPDATE ComentarioAoEstabelecimento SET ComentarioAoEstabelecimento.comentario='"+comentario+"', ComentarioAoEstabelecimento.nota="+nota+" WHERE "+id+"=ComentarioAoEstabelecimento.idEstabelecimento AND ComentarioAoEstabelecimento.email='"+user+"' AND ComentarioAoEstabelecimento.comentario='"+result.getString("comentario")+"'");
         		return true;
         	}else{//insert
         		result = statement.executeQuery("SELECT Estabelecimento.idEstabelecimento FROM Estabelecimento WHERE Estabelecimento.designacao='"+estabelecimento+"'");
         		if(result.next()){
         			System.out.println("ha estabelecimento");
         			int id=Integer.parseInt(result.getString("idEstabelecimento"));
         			result = statement.executeQuery("SELECT Utilizador.email FROM Utilizador WHERE Utilizador.nome='"+user+"'");
         			if(result.next()){
         				System.out.println("inseriu E");
         				String email = result.getString("email");
                 		result = statement.executeQuery("INSERT INTO ComentarioAoEstabelecimento VALUES ("+id+", '"+email+"', '"+comentario+"', "+nota+", 1)");
                 		return true;
         			}
         			
         		}
         	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }
    
    /**
     * Insere um comentario ao prato se ja existir algum nesse prato. 
     * Caso nao exista, cria um novo comentario para o mesmo prato.
     *
     * @param prato o prato
     * @param user o user
     * @param comentario o comentario
     * @param nota a avaliacao
     * @return true, se bem sucedido
     */
    public boolean inserirComentarioPrato(String prato, String user, String comentario, int nota) {
    	try {
			statement = con.createStatement();
			result = statement.executeQuery("SELECT Prato.idPrato, ComentarioAoPrato.email, ComentarioAoPrato.comentario, ComentarioAoPrato.nota FROM ComentarioAoPrato, Prato WHERE Prato.descricao='"+prato+"' AND Prato.idPrato=ComentarioAoPrato.idPrato AND ComentarioAoPrato.email='"+user+"'");
         	if(result.next()){
         		int id=Integer.parseInt(result.getString("idPrato"));
         		result = statement.executeQuery("UPDATE ComentarioAoPrato SET ComentarioAoPrato.comentario='"+comentario+"', ComentarioAoPrato.nota="+nota+" WHERE "+id+"=ComentarioAoPrato.idPrato AND ComentarioAoPrato.email='"+user+"' AND ComentarioAoPrato.comentario='"+result.getString("comentario")+"'");
         		return true;
         	}else{
         		result = statement.executeQuery("SELECT Prato.idPrato FROM Prato WHERE Prato.descricao='"+prato+"'");
         		if(result.next()){
         			int id=Integer.parseInt(result.getString("idPrato"));
         			result = statement.executeQuery("SELECT Utilizador.email FROM Utilizador WHERE Utilizador.nome='"+user+"'");
         			if(result.next()){
         				String email = result.getString("email");
                 		result = statement.executeQuery("INSERT INTO ComentarioAoPrato VALUES ('"+email+"', "+id+",'"+comentario+"', "+nota+")");
                 		return true;
         			}
         		}
         	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }

    /**
     * Insere uma fotografia na base de dados.
     * Verifica tambem se o comentario e feito a um estabelecimento ou prato.
     *
     * @param controller o controlador
     * @param estabelecimento o estabelecimento
     * @param prato o prato
     * @param email o email do user
     * @param coment o comentario
     * @param foto o ficheiro da fotografia
     */
    public void saveFoto(ControllerPesquisa controller, String estabelecimento, String prato, String email, String coment, File foto) {
    	 try{
         	statement = con.createStatement();
         	if(InterfacePesquisa.tipoComentario == TipoComentario.ESTABELECIMENTO){
         		int id=controller.getEstabelecimentoByName(estabelecimento);
         		result = statement.executeQuery("INSERT INTO Fotografia (idEstabelecimento, emailUtilizador, idPrato, localizacao) VALUES ("+id+", '"+email+"', NULL, 'img/"+foto.getName()+"')");
         	}else{
         		int id=controller.getPratoByName(prato);
         		result = statement.executeQuery("INSERT INTO Fotografia (idEstabelecimento, emailUtilizador, idPrato, localizacao) VALUES (NULL, '"+email+"',"+id+", 'img/"+foto.getName()+"')");
         	}
         	ControllerUpload.copyFileToSystem(foto);
 	        statement.close();
 	        con.close();
         
         } catch (SQLException e) {
         	e.printStackTrace();
         }
    }

    /**
     * Procura um comentario ao prato.
     *
     * @param prato o prato
     * @param user o user
     * @param coment o comentario
     * @param nota a avaliacao
     * @return true, se bem sucedido
     */
    public boolean findComent(String prato, String user, String coment, int nota) {
    	try {
			statement = con.createStatement();
			result = statement.executeQuery("SELECT ComentarioAoPrato.idPrato FROM Prato, ComentarioAoPrato WHERE ComentarioAoPrato.email='"+user+"' AND ComentarioAoPrato.nota="+nota+" AND ComentarioAoPrato.comentario='"+coment+"' AND Prato.descricao='"+prato+"'");
			if(result.next()){
				return true;
			}
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }

    /**
     * Elimina um comentario ao prato.
     *
     * @param prato o prato
     * @param user o user
     * @param coment o comentario
     * @param nota a avaliacao
     */
    public void destroyComent(String prato, String user, String coment, int nota) {
    	try {
			statement = con.createStatement();
			result = statement.executeQuery("SELECT Prato.idPrato FROM Prato WHERE Prato.descricao='"+prato+"'");
			if(result.next()){
				result = statement.executeQuery("DELETE ComentarioAoPrato WHERE ComentarioAoPrato.idPrato="+result.getString("idPrato")+" AND ComentarioAoPrato.comentario='"+coment+"' AND ComentarioAoPrato.nota="+nota+" AND ComentarioAoPrato.email='"+user+"'");
			}
		} catch (SQLException e) {
		e.printStackTrace();
		}
    }

    /**
     * Procura uma fotografia.
     *
     * @param fotografia a fotografia
     * @param email o email do user
     * @param comentario o comentario
     * @param estabelecimento o estabelecimento
     * @param prato o prato
     * @return true, se bem sucedido
     */
    public boolean findFoto(Fotografia fotografia, String email, String comentario, String estabelecimento, String prato) {
    	try {
    		statement = con.createStatement();
    		if(prato.equals(" ---- ")){
    			System.out.println("estabelecimentos");
        		result = statement.executeQuery("SELECT Fotografia.idFotografia FROM Fotografia, ComentarioAoEstabelecimento, Estabelecimento WHERE Estabelecimento.designacao='"+estabelecimento+"' AND Estabelecimento.idEstabelecimento=ComentarioAoEstabelecimento.idEstabelecimento AND ComentarioAoEstabelecimento.email='"+email+"' AND Estabelecimento.idEstabelecimento = Fotografia.idEstabelecimento");
    		
    		}else{
    			System.out.println("pratos");
        		result = statement.executeQuery("SELECT Fotografia.idFotografia FROM Fotografia, ComentarioAoPrato, Prato WHERE Prato.descricao='"+prato+"' AND Prato.idPrato=ComentarioAoPrato.idPrato AND ComentarioAoPrato.email='"+email+"' AND ComentarioAoPrato.idPrato=Fotografia.idPrato");
    		}
    		if(result.next()){
    			fotografia.setId(Integer.parseInt(result.getString("idFotografia")));
    			return true;
    		}
    		
    		statement.close();
 	        con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }

    /**
     * Elimina uma fotografia da base de dados.
     * Antes de fazer o DELETE da base de dados, mete todos os campos a NULL,
     * para evitar erros de dependencias de chaves estrangeiras.
     *
     * @param idFoto o id da fotografia
     */
    public void destroyFoto(int idFoto) {
    	try{
        	statement = con.createStatement();
        	System.out.println("id foto: "+idFoto);
        	result = statement.executeQuery("UPDATE Fotografia SET Fotografia.emailutilizador=NULL, Fotografia.idPrato=NULL, Fotografia.idEstabelecimento=NULL WHERE Fotografia.idFotografia="+idFoto);
	        result = statement.executeQuery("DELETE Fotografia WHERE Fotografia.idFotografia="+idFoto);
	        statement.close();
	        con.close();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }

    /**
     * Procura um estabelecimento.
     *
     * @param controller o controlador
     * @param user o user
     * @param estabelecimento o estabelecimento
     * @param prato o prato
     * @param avaliacao a avaliacao
     * @param fotografia se tem fotografia
     * @param comentario o comentario
     */
    public void findEstabelecimentos(ControllerPesquisa controller, String user, String estabelecimento, String prato, int avaliacao, boolean fotografia, String comentario) {
        try{
        	statement = con.createStatement();
	        result = statement.executeQuery("SELECT Estabelecimento.idEstabelecimento, Estabelecimento.rating, Utilizador.nome, Estabelecimento.designacao, Estabelecimento.rating FROM Estabelecimento, Utilizador");
	        
	        while (result.next()) {
	        	new Estabelecimento(controller, Integer.parseInt(result.getString("idEstabelecimento")), result.getString("nome"), result.getString("designacao"), result.getString("rating"));
	        }
	        
	        statement.close();
	        con.close();
        
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }

    /**
     * Procura comentarios aos estabelecimentos.
     * Esta classe interpreta as opcoes introduzidas pelo utilizador no sistema,
     * e faz uma procura filtrada segundo os criterios do utilizador.
     * Se o utilizador seleccionar a opçao '*' significa que quer ver todos os resultados
     * que existem para o termo que pretende pesquisar.
     * Se o utilizador introduzir p.ex 'Bom' na opcao de Comentario, ira filtrar todos os resultados
     * e procurar todos os comentarios que tenham a palavra 'Bom'.
     * Quanto mais o utilizador especificar, mais exacta sera a pesquisa.
     *
     * @param controller o controlador
     * @param nome o nome do user
     * @param estabelecimento o estabelecimento
     * @param listaEstabelecimentos a lista de estabelecimentos
     * @param avaliacao a avaliacao
     * @param fotografia se tem fotografia
     * @param comentario o comentario
     */
    public void findComentarios(ControllerPesquisa controller, String nome, String estabelecimento, ArrayList<Estabelecimento> listaEstabelecimentos, int avaliacao, boolean fotografia, String comentario) {
    	try{
    		statement = con.createStatement();
    		ArrayList<String> whereCase = new ArrayList<String>();
    		if(!estabelecimento.equals("*")){
    			whereCase.add(" WHERE "+controller.getEstabelecimentoByName(estabelecimento)+"=ComentarioAoEstabelecimento.idEstabelecimento AND Estabelecimento.idEstabelecimento=ComentarioAoEstabelecimento.idEstabelecimento");
    		}else{
    			whereCase.add("");
    		}
    		if(!nome.equals("*")){
    			if(whereCase.get(0).equals(""))
    			whereCase.add(" WHERE '"+nome+"'=Utilizador.nome AND ComentarioAoEstabelecimento.email=Utilizador.email");
    			else whereCase.add(" AND '"+nome+"'=Utilizador.nome AND ComentarioAoEstabelecimento.email=Utilizador.email");
    		}else{
    			whereCase.add("");
    		}
    		if(!comentario.isEmpty()){
    			if(whereCase.get(1).equals("") && whereCase.get(0).equals(""))
    			whereCase.add(" WHERE ComentarioAoEstabelecimento.comentario LIKE '%"+comentario+"%'");
    			else{
    				if(whereCase.get(1).equals(""))
    				whereCase.add(" AND ComentarioAoEstabelecimento.comentario LIKE '%"+comentario+"%'");
    			}
    		}else{
    			whereCase.add("");
    		}
    		ComentarioEstabelecimento e=null;
    		if(whereCase.get(0).equals("") && whereCase.get(1).equals("") && whereCase.get(2).equals("")){
    			result = statement.executeQuery("SELECT ComentarioAoEstabelecimento.email, ComentarioAoEstabelecimento.nota, ComentarioAoEstabelecimento.comentario, ComentarioAoEstabelecimento.idEstabelecimento FROM ComentarioAoEstabelecimento WHERE ComentarioAoEstabelecimento.nota>="+avaliacao);
    			while (result.next()) {
		        	e = new ComentarioEstabelecimento(controller, Integer.parseInt(result.getString("idEstabelecimento")), result.getString("email"), result.getString("comentario"), result.getString("nota"));
		        }
    			result = statement.executeQuery("SELECT Fotografia.idFotografia FROM Fotografia, Estabelecimento, ComentarioAoEstabelecimento WHERE Estabelecimento.idEstabelecimento="+e.getIdEstabelecimento()+" AND Fotografia.idEstabelecimento=ComentarioAoEstabelecimento.idEstabelecimento");
    		}else{
    			result = statement.executeQuery("SELECT ComentarioAoEstabelecimento.email, ComentarioAoEstabelecimento.nota, ComentarioAoEstabelecimento.comentario, ComentarioAoEstabelecimento.idEstabelecimento FROM Utilizador, ComentarioAoEstabelecimento, Estabelecimento"
        			+ ""+whereCase.get(0)+whereCase.get(1)+whereCase.get(2)+" AND ComentarioAoEstabelecimento.nota>="+avaliacao);
	    		while (result.next()) {
		        	e = new ComentarioEstabelecimento(controller, Integer.parseInt(result.getString("idEstabelecimento")), result.getString("email"), result.getString("comentario"), result.getString("nota"));
		        }
	    		
    		}
        	
        	
	        statement.close();
	        con.close();
        
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }

    /**
     * Procura um prato.
     *
     * @param estabelecimento the estabelecimento
     * @param prato the prato
     * @param fotografia the fotografia
     * @return the array list
     */
    public ArrayList<Prato> findPratos(String estabelecimento, String prato, boolean fotografia) {
    	ArrayList<Prato> resultList = new ArrayList<Prato>();
    	try{
        	statement = con.createStatement();
        	result = statement.executeQuery("SELECT * FROM Prato");
        	while(result.next()){
        		Prato p = new Prato(Integer.parseInt(result.getString("idPrato")), result.getString("descricao"), result.getString("preco"), Integer.parseInt(result.getString("tipoDePrato")), result.getString("rating"));
        		resultList.add(p);
        	}
	        statement.close();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    	try {
			statement = con.createStatement();
			for (int i = 0; i < resultList.size(); i++) {
	    		result = statement.executeQuery("SELECT * FROM menuDoEstabelecimento WHERE menuDoEstabelecimento.idPrato="+resultList.get(i).getId());
	    		if(result.next()) resultList.get(i).setIDEstabelecimento(result.getString("idEstabelecimento"));
			}
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
    	return resultList;
    }

    /**
     * Procura todos os comentarios.
     * Esta classe interpreta as opcoes introduzidas pelo utilizador no sistema,
     * e faz uma procura filtrada segundo os criterios do utilizador.
     * Se o utilizador seleccionar a opçao '*' significa que quer ver todos os resultados
     * que existem para o termo que pretende pesquisar.
     * Se o utilizador introduzir p.ex 'Bom' na opcao de Comentario, ira filtrar todos os resultados
     * e procurar todos os comentarios que tenham a palavra 'Bom'.
     * Quanto mais o utilizador especificar, mais exacta sera a pesquisa.
     *
     * @param controller the controller
     * @param nome the nome
     * @param prato the prato
     * @param estabelecimento the estabelecimento
     * @param avaliacao the avaliacao
     * @param fotografia the fotografia
     * @param comentario the comentario
     * @return the array list
     */
    public ArrayList<ComentarioPrato> findAllComents(ControllerPesquisa controller,String nome, String prato, String estabelecimento, int avaliacao, boolean fotografia, String comentario) {
    	ArrayList<ComentarioPrato> comentarios = new ArrayList<ComentarioPrato>();
    	try{
    		statement = con.createStatement();
    		ArrayList<String> whereCase = new ArrayList<String>();
    		if(!estabelecimento.equals("*")){
    			whereCase.add(" WHERE "+controller.getEstabelecimentoByName(estabelecimento)+"=Estabelecimento.idEstabelecimento AND Estabelecimento.idEstabelecimento=menuDoEstabelecimento.idEstabelecimento AND menuDoEstabelecimento.idPrato = Prato.idPrato AND Prato.idPrato = ComentarioAoPrato.idPrato");
    			System.out.println("Pesquisa apenas por pratos do estabelecimento!");
    		}else{
    			whereCase.add("");
    		}
    		if(!nome.equals("*")){
    			if(whereCase.get(0).equals(""))
    			whereCase.add(" WHERE '"+nome+"'=Utilizador.nome AND ComentarioAoPrato.email=Utilizador.email");
    			else whereCase.add(" AND '"+nome+"'=Utilizador.nome AND ComentarioAoPrato.email=Utilizador.email");
    		}else{
    			whereCase.add("");
    		}
    		if(!comentario.isEmpty()){
    			if(whereCase.get(1).equals("") && whereCase.get(0).equals(""))
    			whereCase.add(" WHERE ComentarioAoPrato.comentario LIKE '%"+comentario+"%'");
    			else{
    				if(whereCase.get(1).equals(""))
    				whereCase.add(" AND ComentarioAoPrato.comentario LIKE '%"+comentario+"%'");
    				else whereCase.add("");
    			}
    		}else{
    			whereCase.add("");
    		}
    		if(!prato.equals("*")){
    			System.out.println("Pesquisa apenas por pratos!");
    			if(whereCase.get(2).equals("") && whereCase.get(1).equals("") && whereCase.get(0).equals(""))
    			whereCase.add(" WHERE '"+prato+"'=Prato.descricao AND Prato.idPrato=ComentarioAoPrato.idPrato");
    			else{
    				if(!whereCase.get(2).equals("") || !whereCase.get(1).equals("") || !whereCase.get(0).equals(""))
    					whereCase.add(" AND '"+prato+"'=Prato.descricao AND Prato.idPrato=ComentarioAoPrato.idPrato AND menuDoEstabelecimento.idPrato=ComentarioAoPrato.idPrato");
    				else{
    					whereCase.add("");
    				}
    			}
    		}else{
    			whereCase.add("");
    		}
    		if(whereCase.get(0).equals("") && whereCase.get(1).equals("") && whereCase.get(2).equals("") && whereCase.get(3).equals("")){
    			result = statement.executeQuery("SELECT DISTINCT ComentarioAoPrato.email, ComentarioAoPrato.nota, ComentarioAoPrato.comentario, ComentarioAoPrato.idPrato FROM ComentarioAoPrato  WHERE ComentarioAoPrato.nota>="+avaliacao);
    			while (result.next()) {
    				ComentarioPrato e = new ComentarioPrato(Integer.parseInt(result.getString("idPrato")), result.getString("email"), result.getString("comentario"), result.getString("nota"));
    				comentarios.add(e);
    			}
    		}else{
    			result = statement.executeQuery("SELECT DISTINCT ComentarioAoPrato.email, ComentarioAoPrato.nota, ComentarioAoPrato.comentario, ComentarioAoPrato.idPrato FROM Utilizador, ComentarioAoPrato, Prato, Estabelecimento, menuDoEstabelecimento"
        			+ ""+whereCase.get(0)+whereCase.get(1)+whereCase.get(2)+whereCase.get(3)+"AND ComentarioAoPrato.nota>="+avaliacao);
	    		while (result.next()) {
	    			ComentarioPrato e = new ComentarioPrato(Integer.parseInt(result.getString("idPrato")), result.getString("email"), result.getString("comentario"), result.getString("nota"));
	    			comentarios.add(e);
	    		}
    		}
        	
        	
	        statement.close();
	        con.close();
        
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    	return comentarios;
    }

    /**
     * Elimina uma fotografia.
     *
     * @param file o ficheiro da fotografia
     * @return 0
     */
    public int destroyFoto(File file) {
        //throw new UnsupportedOperationException("Not yet implemented");
        //devolver ID
    	return 0;
    }



	/**
	 * Insere uma fotografia.
	 *
	 * @param file o ficheiro da fotografia
	 * @return 0
	 */
	public int saveFoto(File file) {
		return 0;
	}



	/**
	 * Procura todos os estabelecimentos.
	 *
	 * @return lista de todos os estabelecimentos existentes
	 */
	public ArrayList<Estabelecimento> findAllE() {
		ArrayList<Estabelecimento> lista = new ArrayList<Estabelecimento>();
		try{
        	statement = con.createStatement();
	        result = statement.executeQuery("SELECT * FROM Estabelecimento");
	        
	        while (result.next()) {
	        	Estabelecimento e = new Estabelecimento(Integer.parseInt(result.getString("idEstabelecimento")), result.getString("email"), result.getString("designacao"), result.getString("rating"));
	        	lista.add(e);
	        }
	        
	        statement.close();
	        con.close();
        
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		return lista;
	}



	/**
	 * Procura todos os pratos.
	 *
	 * @return a lista todos os pratos.
	 */
	public ArrayList<Prato> findAllPratos() {
		ArrayList<Prato> lista = new ArrayList<Prato>();
		try{
        	statement = con.createStatement();
	        result = statement.executeQuery("SELECT * FROM Prato");
	        
	        while (result.next()) {
	        	Prato e = new Prato(Integer.parseInt(result.getString("idPrato")), result.getString("descricao"), result.getString("preco"), Integer.parseInt(result.getString("tipoDePrato")), result.getString("rating"));
	        	lista.add(e);
	        }
	        
	        statement.close();
	        con.close();
        
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		return lista;
	}



	/**
	 * Procura todos os utilizadores.
	 *
	 * @return lista de todos os utilizadores.
	 */
	public ArrayList<Utilizador> findAllUsers() {
		ArrayList<Utilizador> lista = new ArrayList<Utilizador>();
		try{
        	statement = con.createStatement();
	        result = statement.executeQuery("SELECT * FROM Utilizador");
	        
	        while (result.next()) {
	        	Utilizador e = new Utilizador(result.getString("email"), Integer.parseInt(result.getString("idFotografia")), result.getString("nome"), result.getString("escola"));
	        	lista.add(e);
	        }
	        
	        statement.close();
	        con.close();
        
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		return lista;
	}



	/**
	 * Procura todos os comentarios com fotografia aos estabelecimentos.
	 *
	 * @param comentarioEstabelecimento o comentario ao estabelecimento
	 * @param listaComentariosEstabelecimento a lista comentarios ao estabelecimento
	 * @param listaEstabelecimentos a lista de estabelecimentos
	 */
	public void findFotoComents(
			ComentarioEstabelecimento comentarioEstabelecimento,
			ArrayList<ComentarioEstabelecimento> listaComentariosEstabelecimento, ArrayList<Estabelecimento> listaEstabelecimentos) {
		
		try {
			statement = con.createStatement();
			for (int i = 0; i < listaEstabelecimentos.size(); i++) {
				result = statement.executeQuery("SELECT Fotografia.idFotografia FROM Fotografia, Estabelecimento, ComentarioAoEstabelecimento WHERE Estabelecimento.idEstabelecimento="+listaEstabelecimentos.get(i).getId()+" AND Estabelecimento.idEstabelecimento=ComentarioAoEstabelecimento.idEstabelecimento AND Fotografia.idEstabelecimento=ComentarioAoEstabelecimento.idEstabelecimento");
				if(result.next()){
					for (int j = 0; j < listaComentariosEstabelecimento.size(); j++) {
						if(listaComentariosEstabelecimento.get(j).getIdEstabelecimento()==listaEstabelecimentos.get(i).getId())
							listaComentariosEstabelecimento.get(j).setFotografiaID(Integer.parseInt(result.getString("idFotografia")));
					}
				}	
			}
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	/**
	 * Procura todos os comentarios com fotografia aos pratos.
	 *
	 * @param comentarioPrato o comentario ao prato
	 * @param listComentariosPrato a lista comentarios ao prato
	 * @param listaPratos a lista de pratos
	 */
	public void findFotoComentsPlates(ComentarioPrato comentarioPrato,
			ArrayList<ComentarioPrato> listComentariosPrato,
			ArrayList<Prato> listaPratos) {
		try {
			statement = con.createStatement();
			for (int i = 0; i < listaPratos.size(); i++) {
				result = statement.executeQuery("SELECT Fotografia.idFotografia FROM Fotografia, Prato, ComentarioAoPrato WHERE Prato.idPrato="+listaPratos.get(i).getId()+" AND Prato.idPrato=ComentarioAoPrato.idPrato AND Fotografia.idPrato=ComentarioAoPrato.idPrato");
				if(result.next()){
					for (int j = 0; j < listComentariosPrato.size(); j++) {
						if(listComentariosPrato.get(j).getId()==listaPratos.get(i).getId())
							listComentariosPrato.get(j).setFotografiaID(Integer.parseInt(result.getString("idFotografia")));
					}
				}	
			}
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Procura fotografia do comentario ao estabelecimento.
	 * Retorna a localizacao/path/caminho do ficheiro da fotografia.
	 * 
	 * @param email o email do user
	 * @param estabelecimento o estabelecimento
	 * @param comentario o comentario
	 * @return string que contem a localizacao/path da fotografia
	 */
	public String findFotoForComentEstabelecimento(String email, String estabelecimento, String comentario) {
		String r="";
		try {
			statement = con.createStatement();
			result = statement.executeQuery("SELECT Fotografia.localizacao FROM Fotografia, Estabelecimento, ComentarioAoEstabelecimento WHERE Estabelecimento.designacao='"+estabelecimento+"' AND Estabelecimento.idEstabelecimento=ComentarioAoEstabelecimento.idEstabelecimento AND ComentarioAoEstabelecimento.email='"+email+"'  AND Fotografia.idEstabelecimento IS NOT NULL");
			if(result.next()){
				return r=result.getString("localizacao");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}



	/**
	 * Procura fotografia do comentario ao prato.
	 * Retorna a localizacao/path/caminho do ficheiro da fotografia.
	 *
	 * @param email o email do user
	 * @param prato o prato
	 * @param comentario o comentario
	 * @return string que contem a localizacao/path da fotografia
	 */
	public String findFotoForComentPrato(String email, String prato, String comentario) {
		String r="";
		try {
			statement = con.createStatement();
			result = statement.executeQuery("SELECT Fotografia.localizacao FROM Fotografia, Prato, ComentarioAoPrato WHERE Prato.descricao='"+prato+"' AND Prato.idPrato=ComentarioAoPrato.idPrato AND ComentarioAoPrato.email='"+email+"' AND Fotografia.idPrato IS NOT NULL");
			if(result.next()){
				return r=result.getString("localizacao");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}



	/**
	 * Procura um comentario ao estabelecimento.
	 * Retorna verdadeiro se existir um comentario que corresponda aos argumentos passados.
	 *
	 * @param estabelecimento o estabelecimento
	 * @param user o user
	 * @param coment o comentario
	 * @param nota a avaliacao
	 * @return true, se bem sucedido
	 */
	public boolean findComentE(String estabelecimento, String user, String coment, int nota) {
		try {
			statement = con.createStatement();
			result = statement.executeQuery("SELECT ComentarioAoEstabelecimento.idEstabelecimento FROM Estabelecimento, ComentarioAoEstabelecimento WHERE ComentarioAoEstabelecimento.email='"+user+"' AND ComentarioAoEstabelecimento.nota="+nota+" AND ComentarioAoEstabelecimento.comentario='"+coment+"' AND Estabelecimento.designacao='"+estabelecimento+"'");
			if(result.next()){
				return true;
			}
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
	}



	/**
	 * Elimina um comentario ao estabelecimento.
	 * Remove um comentario ao estabelecimento da base de dados.
	 *
	 * @param estabelecimento o estabelecimento
	 * @param user o user
	 * @param coment o comentario
	 * @param nota a avalicao
	 */
	public void destroyComentE(String estabelecimento, String user, String coment, int nota) {
		try {
			statement = con.createStatement();
			result = statement.executeQuery("SELECT Estabelecimento.idEstabelecimento FROM Estabelecimento WHERE Estabelecimento.designacao='"+estabelecimento+"'");
			if(result.next()){
				result = statement.executeQuery("DELETE ComentarioAoEstabelecimento WHERE ComentarioAoEstabelecimento.idEstabelecimento="+result.getString("idEstabelecimento")+" AND ComentarioAoEstabelecimento.comentario='"+coment+"' AND ComentarioAoEstabelecimento.nota="+nota+" AND ComentarioAoEstabelecimento.email='"+user+"'");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
    
}

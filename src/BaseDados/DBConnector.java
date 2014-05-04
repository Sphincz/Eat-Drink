/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author Nuno
 */


public class DBConnector {
	
	private ResultSet result; 
	private Statement statement;
	private String dburl = "jdbc:sqlanywhere:Tds:localhost:2638?eng=EatDrink";
	private String user = "dba", password = "sql";
	private Connection con;
	
	public DBConnector() {
        // Connect to Sybase Database
        try {
			con = DriverManager.getConnection(dburl, user, password);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Base de dados desligada ou username e/ou password inválidos!");
			System.exit(0);
			
		}
	}

	

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }
    
    public boolean inserirComentarioPrato(String prato, String user, String comentario, int nota) {
    	try {
			statement = con.createStatement();
			result = statement.executeQuery("SELECT Prato.idPrato, ComentarioAoPrato.email, ComentarioAoPrato.comentario, ComentarioAoPrato.nota FROM ComentarioAoPrato, Prato WHERE Prato.descricao='"+prato+"' AND Prato.idPrato=ComentarioAoPrato.idPrato AND ComentarioAoPrato.email='"+user+"'");
         	if(result.next()){//update
         		int id=Integer.parseInt(result.getString("idPrato"));
         		result = statement.executeQuery("UPDATE ComentarioAoPrato SET ComentarioAoPrato.comentario='"+comentario+"', ComentarioAoPrato.nota="+nota+" WHERE "+id+"=ComentarioAoPrato.idPrato AND ComentarioAoPrato.email='"+user+"' AND ComentarioAoPrato.comentario='"+result.getString("comentario")+"'");
         		return true;
         	}else{//insert
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }

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

    public boolean findComent(int idComent) {
        //throw new UnsupportedOperationException("Not yet implemented");
    	return true;
    }

    public void destroyComent(int idComent) {
    }

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	
    	return false;
    }

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

    public void findEstabelecimentos(ControllerPesquisa controller, String user, String estabelecimento, String prato, int avaliacao, boolean fotografia, String comentario) {
        try{
        	statement = con.createStatement();
	        result = statement.executeQuery("SELECT Estabelecimento.idEstabelecimento, Estabelecimento.rating, Utilizador.nome, Estabelecimento.designacao, Estabelecimento.rating FROM Estabelecimento, Utilizador");
	        
	        while (result.next()) {
	        	Estabelecimento e = new Estabelecimento(controller, Integer.parseInt(result.getString("idEstabelecimento")), result.getString("nome"), result.getString("designacao"), result.getString("rating"));
	        }
	        
	        statement.close();
	        con.close();
        
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }

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

    public int destroyFoto(File file) {
        //throw new UnsupportedOperationException("Not yet implemented");
        //devolver ID
    	return 0;
    }



	public int saveFoto(File file) {
		// TODO Auto-generated method stub
		return 0;
	}



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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}



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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	public String findFotoForComentEstabelecimento(String email,
			String estabelecimento, String comentario) {
		String r="";
		try {
			statement = con.createStatement();
			result = statement.executeQuery("SELECT Fotografia.localizacao FROM Fotografia, Estabelecimento, ComentarioAoEstabelecimento WHERE Estabelecimento.designacao='"+estabelecimento+"' AND Estabelecimento.idEstabelecimento=ComentarioAoEstabelecimento.idEstabelecimento AND ComentarioAoEstabelecimento.email='"+email+"'");
			if(result.next()){
				return r=result.getString("localizacao");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return r;
	}



	public String findFotoForComentPrato(String email, String prato,
			String comentario) {
		String r="";
		try {
			statement = con.createStatement();
			result = statement.executeQuery("SELECT Fotografia.localizacao FROM Fotografia, Prato, ComentarioAoPrato WHERE Prato.descricao='"+prato+"' AND Prato.idPrato=ComentarioAoPrato.idPrato AND ComentarioAoPrato.email='"+email+"'");
			if(result.next()){
				return r=result.getString("localizacao");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return r;
	}
    
}

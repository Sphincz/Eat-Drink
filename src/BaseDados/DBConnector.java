/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDados;

import Comentarios.ComentarioEstabelecimento;
import Comentarios.ComentarioPrato;
import Controller.ControllerPesquisa;
import Controller.Estabelecimento;
import Controller.InterfacePesquisa;
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

	

    public boolean inserirComentarioEstabelecimento(int id, String comentario, int nota) {
        //throw new UnsupportedOperationException("Not yet implemented");
    	return true;
        //se insistir atualiza
    }
    
    public boolean inserirComentarioPrato(int id, String comentario, int nota) {
        //throw new UnsupportedOperationException("Not yet implemented");
        //se insistir atualiza
    	return true;
    }

    public void saveFoto(String email, String coment, File foto) {
    	 try{
         	statement = con.createStatement();
         	if(InterfacePesquisa.tipoComentario == TipoComentario.ESTABELECIMENTO){
         		result = statement.executeQuery("INSERT INTO Fotografia (idEstabelecimento,emailUtilizador, idPrato, localizacao) VALUES ("+1+", '"+"pfl@iscte.pt"+"',"+1+", '"+"wffwf"+"')");
         	}else{
         		result = statement.executeQuery("INSERT INTO Fotografia (idEstabelecimento,emailUtilizador, idPrato, localizacao) VALUES ("+1+", '"+"pfl@iscte.pt"+"',"+1+", '"+"wffwf"+"')");
         	}
 	        
 	        
 	        
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
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean findFoto(int idFoto) {
        //throw new UnsupportedOperationException("Not yet implemented");
    	return true;
    }

    public void destroyFoto(int idFoto) {
       // throw new UnsupportedOperationException("Not yet implemented");
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
    			whereCase.add(" WHERE '"+comentario+"'=ComentarioAoEstabelecimento.comentario");
    			else{
    				if(whereCase.get(1).equals(""))
    				whereCase.add(" AND '"+comentario+"'=ComentarioAoEstabelecimento.comentario");
    			}
    		}else{
    			whereCase.add("");
    		}
    		System.out.println("->"+whereCase.get(0)+whereCase.get(1)+whereCase.get(2));
    		if(whereCase.get(0).equals("") && whereCase.get(1).equals("") && whereCase.get(2).equals("")){
    			result = statement.executeQuery("SELECT ComentarioAoEstabelecimento.email, ComentarioAoEstabelecimento.nota, ComentarioAoEstabelecimento.comentario, ComentarioAoEstabelecimento.idEstabelecimento FROM ComentarioAoEstabelecimento");
    			while (result.next()) {
		        	ComentarioEstabelecimento e = new ComentarioEstabelecimento(controller, Integer.parseInt(result.getString("idEstabelecimento")), result.getString("email"), result.getString("comentario"), result.getString("nota"));
		        	System.out.println("Comentario: "+e.getComentario());
		        }
    		}else{
    			result = statement.executeQuery("SELECT ComentarioAoEstabelecimento.email, ComentarioAoEstabelecimento.nota, ComentarioAoEstabelecimento.comentario, ComentarioAoEstabelecimento.idEstabelecimento FROM Utilizador, ComentarioAoEstabelecimento, Estabelecimento"
        			+ ""+whereCase.get(0)+whereCase.get(1)+whereCase.get(2));
	    		while (result.next()) {
		        	ComentarioEstabelecimento e = new ComentarioEstabelecimento(controller, Integer.parseInt(result.getString("idEstabelecimento")), result.getString("email"), result.getString("comentario"), result.getString("nota"));
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
        	if(estabelecimento == null || estabelecimento.isEmpty() && prato == null || prato.isEmpty()){//sem nada
        		result = statement.executeQuery("SELECT * FROM Prato");
        		while(result.next()){
        			Prato p = new Prato(Integer.parseInt(result.getString("idPrato")), result.getString("descricao"), result.getString("preco"), Integer.parseInt(result.getString("tipoDePrato")), result.getString("rating"));
        			resultList.add(p);
        			System.out.println("bla");
        		}
        	}else{
        		if(estabelecimento == null || estabelecimento.isEmpty() && prato != null || !prato.isEmpty()){//sem estabelecimento
        			result = statement.executeQuery("SELECT * FROM Prato WHERE Prato.descricao='"+prato+"'");
	        			while(result.next()){
	        			Prato p = new Prato(Integer.parseInt(result.getString("idPrato")), result.getString("descricao"), result.getString("preco"), Integer.parseInt(result.getString("tipoDePrato")), result.getString("rating"));
	        			resultList.add(p);
	        			System.out.println("bla");
        			}
        		}else{
        			if(estabelecimento != null || !estabelecimento.isEmpty() && prato == null || prato.isEmpty()){
        				result = statement.executeQuery("SELECT * FROM Prato, menuDoEstabelecimento, Estabelecimento WHERE menuDoEstabelecimento.idEstabelecimento=Estabelecimento.idEstabelecimento AND Estabelecimento.designacao='"+estabelecimento+"'");//sem prato
        				while(result.next()){	
        					Prato p = new Prato(Integer.parseInt(result.getString("idPrato")), result.getString("descricao"), result.getString("preco"), Integer.parseInt(result.getString("tipoDePrato")), result.getString("rating"));
	            			resultList.add(p);
	            			System.out.println("bla");
        				}
        			}//tudo
        			if(estabelecimento != null || !estabelecimento.isEmpty() && prato != null || !prato.isEmpty()){//sem estabelecimento
        				result = statement.executeQuery("SELECT * FROM Prato, menuDoEstabelecimento, Estabelecimento WHERE menuDoEstabelecimento.idEstabelecimento=Estabelecimento.idEstabelecimento AND Estabelecimento.designacao='"+estabelecimento+"' AND Prato.descricao='"+prato+"'");
        				while(result.next()){
	        				Prato p = new Prato(Integer.parseInt(result.getString("idPrato")), result.getString("descricao"), result.getString("preco"), Integer.parseInt(result.getString("tipoDePrato")), result.getString("rating"));
	            			resultList.add(p);
	            			System.out.println("bla");
        				}
        			}
        		}
        	}
	        statement.close();
	        con.close();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    	return resultList;
    }

    public ArrayList<ComentarioPrato> findAllComents(String username, ArrayList<Prato> listaPratos, int avaliacao, boolean fotografia, String comentario) {
    	ArrayList<ComentarioPrato> comentarios = new ArrayList<ComentarioPrato>();
    	try{
        	statement = con.createStatement();
        	for (int i = 0; i < listaPratos.size(); i++) {
        		result = statement.executeQuery("SELECT ComentarioAoPrato.email, ComentarioAoPrato.nota, ComentarioAoPrato.comentario, ComentarioAoPrato.idPrato, ComentarioAoPrato.nota FROM ComentarioAoPrato WHERE"
        				+ " "+listaPratos.get(i).getId()+"=ComentarioAoPrato.idPrato");
        		System.out.println(""+i);
        		while (result.next()) {
        			ComentarioPrato e = new ComentarioPrato(Integer.parseInt(result.getString("idPrato")), result.getString("email"), result.getString("comentario"), result.getString("nota"));
    	        	comentarios.add(e);
        			System.out.println(e.getComentario());
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
    
}

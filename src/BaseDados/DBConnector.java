/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDados;

import Controller.Estabelecimento;
import Pratos.Prato;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

    public boolean inserirComentarioEstabelecimento(int id, String comentario, int nota) {
        throw new UnsupportedOperationException("Not yet implemented");
        //se insistir atualiza
    }
    
    public boolean inserirComentarioPrato(int id, String comentario, int nota) {
        throw new UnsupportedOperationException("Not yet implemented");
        //se insistir atualiza
    }

    public void saveFoto(int id, File foto) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean findComent(int idComent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void destroyComent(int idComent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean findFoto(int idFoto) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void destroyFoto(int idFoto) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void findEstabelecimentos(String user, String estabelecimento, String prato, int avaliacao, boolean fotografia, String comentario) {
        try{
        	statement = con.createStatement();
	        result = statement.executeQuery("SELECT GETDATE()");
	        
	        if (result.next()) {
	            Date currentDate = result.getDate(1); // get first column returned
	            System.out.println("Current Date from Sybase is : "+currentDate);
	        }
	        
	        result.close();
	        statement.close();
	        con.close();
        
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
        //for
        //Estabelecimento e = new Estabalecimento(id, nome, prato, fotografia);
    }

    public void findComentarios(int userID, ArrayList<Estabelecimento> listaEstabelecimentos, int avaliacao, boolean fotografia, String comentario) {
        throw new UnsupportedOperationException("Not yet implemented");
        //for
        //ComentarioEstabelecimento c = new ComentarioEstabelecimento(userID, comentarioID, comentario);
    }

    public void findPratos(String estabelecimento, Prato prato, boolean fotografia) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void findAllComents(int userID, ArrayList<Prato> listaPratos, int avaliacao, boolean fotografia, String comentario) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int destroyFoto(File file) {
        throw new UnsupportedOperationException("Not yet implemented");
        //devolver ID
    }
    
}

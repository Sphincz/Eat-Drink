/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDados;

import Comentarios.ComentarioEstabelecimento;
import Controller.ControllerPesquisa;
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
        //throw new UnsupportedOperationException("Not yet implemented");
    	return true;
        //se insistir atualiza
    }
    
    public boolean inserirComentarioPrato(int id, String comentario, int nota) {
        //throw new UnsupportedOperationException("Not yet implemented");
        //se insistir atualiza
    	return true;
    }

    public void saveFoto(int id, File foto) {
        throw new UnsupportedOperationException("Not yet implemented");
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
	        result = statement.executeQuery("SELECT Estabelecimento.idEstabelecimento, Estabelecimento.rating, Utilizador.nome, Estabelecimento.designacao, Estabelecimento.rating FROM Estabelecimento, Utilizador WHERE '"+user+"'=Utilizador.nome AND Estabelecimento.email = Utilizador.email"
	        		+ " OR Estabelecimento.designacao='"+estabelecimento+"' OR Estabelecimento.rating = "+avaliacao+" ");
	        
	        while (result.next()) {
	        	Estabelecimento e = new Estabelecimento(controller, Integer.parseInt(result.getString("idEstabelecimento")), result.getString("nome"), result.getString("designacao"), result.getString("rating"));
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

    public void findComentarios(ControllerPesquisa controller, String userID, ArrayList<Estabelecimento> listaEstabelecimentos, int avaliacao, boolean fotografia, String comentario) {
    	try{
        	statement = con.createStatement();
        	for (int i = 0; i < listaEstabelecimentos.size(); i++) {
        		result = statement.executeQuery("SELECT ComentarioAoEstabelecimento.email, ComentarioAoEstabelecimento.nota, ComentarioAoEstabelecimento.comentario, ComentarioAoEstabelecimento.idEstabelecimento FROM Utilizador, ComentarioAoEstabelecimento, Estabelecimento WHERE"
        				+ " "+listaEstabelecimentos.get(i).getId()+"=ComentarioAoEstabelecimento.idEstabelecimento AND '"+userID+"'=Utilizador.nome");
        		System.out.println(""+i);
        		while (result.next()) {
    	        	ComentarioEstabelecimento e = new ComentarioEstabelecimento(controller, Integer.parseInt(result.getString("idEstabelecimento")), result.getString("email"), result.getString("comentario"), result.getString("nota"));
    	        	System.out.println(e.getComentario());
    	        }
        	}
	        result.close();
	        statement.close();
	        con.close();
        
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    	
    	
    	//throw new UnsupportedOperationException("Not yet implemented");
        //for
        //ComentarioEstabelecimento c = new ComentarioEstabelecimento(userID, comentarioID, comentario);
    }

    public void findPratos(String estabelecimento, Prato prato, boolean fotografia) {
       // throw new UnsupportedOperationException("Not yet implemented");
    }

    public void findAllComents(String userID, ArrayList<Prato> listaPratos, int avaliacao, boolean fotografia, String comentario) {
        //throw new UnsupportedOperationException("Not yet implemented");
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
    
}

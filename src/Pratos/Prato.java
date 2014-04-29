/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pratos;

import BaseDados.DBConnector;
import Controller.ControllerPesquisa;
import java.util.ArrayList;

/**
 *
 * @author Nuno
 */
public class Prato {

    public ArrayList<Prato> findAll(String estabelecimento, Prato prato, boolean fotografia) {
        DBConnector db = new DBConnector();
        db.findPratos(estabelecimento, prato, fotografia);
        return null;
    }
    
}

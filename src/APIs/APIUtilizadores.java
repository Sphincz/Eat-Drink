/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package APIs;

import Suporte.TipoFotografia;

/**
 *
 * @author Nuno
 */
public interface APIUtilizadores {
    public boolean uploadFotografiaUtilizador(String emailUtilizador);
    public void viewFotografia(int idFotografia, TipoFotografia tipo);
}

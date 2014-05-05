package APIs;

import Suporte.TipoFotografia;

/**
 * Interface APIUtilizadores.
 *
 * @author Nuno Coelho, Antonio Raimundo, Jose Serro, Diogo Peres
 */
public interface APIUtilizadores {
    
    /**
     * Upload da fotografia do utilizador.
     *
     * @param emailUtilizador o email do utilizador
     * @return true, se bem sucedido
     */
    public boolean uploadFotografiaUtilizador(String emailUtilizador);
    
    /**
     * Ver fotografia.
     *
     * @param idFotografia o id da fotografia
     * @param tipo o tipo de fotografia
     */
    public void viewFotografia(int idFotografia, TipoFotografia tipo);
}

package APIs;

/**
 * Interface APIEstabelecimentos.
 *
 * @author Nuno Coelho, Antonio Raimundo, Jose Serro, Diogo Peres
 */
public interface APIEstabelecimentos {
    
    /**
     * Ver todos os comentarios ao prato.
     *
     * @param idPrato id prato
     * @param idEstabelecimento id estabelecimento
     */
    public void viewAllComentarioAoPrato(int idPrato, int idEstabelecimento);
    
    /**
     * Ver todos os comentarios ao estabelecimento.
     *
     * @param idEstabelecimento id estabelecimento
     */
    public void viewAllComentarioAoEstabelecimento(int idEstabelecimento);
}

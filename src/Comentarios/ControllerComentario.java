package Comentarios;

import Suporte.TipoComentario;

/**
 * Classe ControllerComentario.
 * Esta classe controla as operacoes que sao necessarias para adicionar/ver/remover comentarios dos 
 * pratos/estabelecimentos.
 *
 * @author Nuno Coelho, Antonio Raimundo, Jose Serro, Diogo Peres
 */
public class ControllerComentario {
    
    /** o comentario ao prato. */
    private ComentarioPrato comentPrato = new ComentarioPrato();
    
    /** o comentario ao estabelecimento. */
    private ComentarioEstabelecimento comentEstabelecimento = new ComentarioEstabelecimento();
    
    /**
     * Insere um comentario na base de dados.
     * O metodo verifica qual o tipo de comentario em questao (prato/estabelecimento) e insere-o 
     * na base de dados. Este metodo chama a classe que trata o comentario, consoante o tipo.
     *
     * @param tipo o tipo de comentario
     * @param e o estabelecimento
     * @param p o prato
     * @param user o user
     * @param comentario o comentario
     * @param nota a avaliacao
     * @return true, se bem sucedido
     */
    public boolean save(TipoComentario tipo, String e, String p, String user, String comentario, int nota) {
        if(tipo.equals(TipoComentario.ESTABELECIMENTO)){
        	System.out.println("passou");
            return comentEstabelecimento.save(e, user, comentario, nota);
        }else{
            return comentPrato.save(p, user, comentario, nota);
        }
    }

    /**
     * Elimina um comentario na base de dados.
     * O metodo verifica qual o tipo de comentario em questao (prato/estabelecimento) e elimina-o 
     * na base de dados. Este metodo chama a classe que trata o comentario, consoante o tipo.
     *
     * @param tipo o tipo de comentario
     * @param e o estabelecimento
     * @param p o prato
     * @param user o user
     * @param comentario o comentario
     * @param nota a avaliacao
     * @return true, se bem sucedido
     */
    public boolean delete(TipoComentario tipo, String prato, String estabelecimento, String user, String coment, int nota) {
        boolean encontrou=false;
        if(tipo.equals(TipoComentario.ESTABELECIMENTO)){
        	encontrou = comentEstabelecimento.find(estabelecimento, user, coment, nota);
        } else {
        	encontrou = comentPrato.find(prato, user, coment, nota);
        }
        if(encontrou && tipo.equals(TipoComentario.ESTABELECIMENTO)){
        	comentEstabelecimento.destroy(estabelecimento, user, coment, nota);
        	return true;
        } else {
        	if(encontrou && tipo.equals(TipoComentario.PRATO)){
        		comentPrato.destroy(prato, user, coment, nota);
        		return true;
        	}
        }
        return false;  
    } 
}

package Controller;

import java.io.File;
import javax.swing.JFrame;

/**
 * Classe InterfaceUpload.
 * Esta classe cria as funcoes necessarias para fazer upload de uma fotografia.
 *
 * @author Nuno Coelho, Antonio Raimundo, Jose Serro, Diogo Peres
 */
public class InterfaceUpload extends JFrame{
	
	/** a serialVersionUID auto-gerada. */
	private static final long serialVersionUID = 1L;

	/**
	 * Executa o metodo upload().
	 */
	public void init(){//pseudo-construtor
        upload();//ABRIR CARREGAR FOTOGRAFIA DO Utilizador
        
    }

    /**
     * Upload de uma fotografia.
     * Esta classe comunica com o controlador de upload para fazer upload de uma fotografia
     *
     * @return true, se bem sucedido
     */
    private boolean upload() {//ABRIR CARREGAR FOTOGRAFIA DO Utilizador
        File file = null; //imagem carregada aqui
        ControllerUpload controller = new ControllerUpload();
        boolean result = controller.uploadFotografiaUtilizador(file);
        return result;
    }
}

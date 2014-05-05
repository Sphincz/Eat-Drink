package Controller;

import Fotografia.Fotografia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JOptionPane;

/**
 * Classe ControllerUpload.
 * Esta classe controla as operacoes que sao necessarias no envio de fotografias para o sistema.
 *
 * @author Nuno Coelho, Antonio Raimundo, Jose Serro, Diogo Peres
 */
public class ControllerUpload {

	//NÃO IMPLEMENTADO POIS NÃO HÁ FORMULARIO/INTERFACE NO RELATORIO DE ESPECIFICAÇÃO
    /**
	 * Upload fotografia do utilizador.
	 *
	 * @param file o ficheiro da fotografia
	 * @return true, se bem sucedido
	 */
	public boolean uploadFotografiaUtilizador(File file) {
        Fotografia fotoPerfil = new Fotografia();
        fotoPerfil.save(file);
        throw new UnsupportedOperationException("Not yet implemented");
    }

	/**
	 * Copia o ficheiro da fotografia para o sistema.
	 *
	 * @param foto o ficheiro da fotografia
	 */
	public static void copyFileToSystem(File foto) {
		File tempFile = new File("img/"+foto.getName()+"");
		try {
			Files.copy(foto.toPath(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Foto inserida!");
	}
    
}

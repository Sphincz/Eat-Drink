/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Fotografia.Fotografia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;

import javax.swing.JOptionPane;

/**
 *
 * @author Nuno
 */
public class ControllerUpload {

	//NÃO IMPLEMENTADO POIS NÃO HÁ FORMULARIO/INTERFACE NO RELATORIO DE ESPECIFICAÇÃO
    public boolean uploadFotografiaUtilizador(File file) {
        Fotografia fotoPerfil = new Fotografia();
        fotoPerfil.save(file);
        throw new UnsupportedOperationException("Not yet implemented");
    }

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

package src.main.model;

import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ScannerData {

    public static boolean isEqual(File secretaryFile, File uploadFile) {

        if (secretaryFile == null || uploadFile == null) {
            return false;
        }
    
        if (!secretaryFile.exists() || !uploadFile.exists()) {
            return false;
        }

        try {
            BufferedImage bdSecretary = ImageIO.read(secretaryFile);
            BufferedImage uploadImage = ImageIO.read(uploadFile);

            if (bdSecretary == null) {
            System.err.println("No se pudo leer la imagen de secretaria: " + secretaryFile.getAbsolutePath());
            
            }
            if (uploadImage == null) {
            System.err.println("No se pudo leer la imagen subida: " + uploadFile.getAbsolutePath());
            
            }

            if (bdSecretary.getWidth() != uploadImage.getWidth() ||  bdSecretary.getHeight() != uploadImage.getHeight()) {
                return false;
            }

            for (int y = 0; y < uploadImage.getHeight(); y++) {
                for (int x = 0; x < uploadImage.getWidth(); x++) {
                    if (uploadImage.getRGB(x, y) != bdSecretary.getRGB(x, y)) return false;
                }
            }

            return true;

        } catch(IOException exception) {

            System.err.println("Ocurrio un error manipulando las imagenes" + exception);
            return false;

        }

    }
}

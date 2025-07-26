package src.main.utils;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    public static void guardarDatos(String rutaArchivo, String datos) throws IOException {
        FileWriter writer = new FileWriter(rutaArchivo); 
        writer.write(datos);
        writer.close();
    }
}

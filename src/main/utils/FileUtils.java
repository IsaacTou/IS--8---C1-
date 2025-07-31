package src.main.utils;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileUtils {

    public static void guardarDatos(String rutaArchivo, String datos) throws IOException {
        FileWriter writer = new FileWriter(rutaArchivo); 
        writer.write(datos);
        writer.close();
    }

    public static String leerDatos(String rutaArchivo) throws IOException {
        File file = new File(rutaArchivo);
        if (!file.exists()) {
            return null;
        }
        
        Scanner scanner = new Scanner(file);
        String contenido = scanner.hasNext() ? scanner.next() : null;
        scanner.close();
        return contenido;
    }
}
package src.main.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;


public class UCVDataReader {
    private String ruteString;
    private String userType;
    private final int IDENTIFICATION_ID = 0;
    private final int UCV_USER_TYPE = 1;
    private final int FACE_ID_USER = 2;

    //Constructor para pruebas UNITARIAS
    public UCVDataReader (String ruteString) {
        this.ruteString = ruteString;
        this.userType = "";
    }


    public UCVDataReader () {
        ruteString = "src/main/data/UCVDataBase.txt";
        userType = "";
    }

    public boolean findCi(String ci) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruteString))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[IDENTIFICATION_ID].equals(ci)) {
                    this.userType = parts[UCV_USER_TYPE];
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return false;
    }

    public File findBdImage(String ci) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruteString))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[IDENTIFICATION_ID].equals(ci)) {
                    this.userType = parts[UCV_USER_TYPE];
                    String file = "src/main/data/FaceID/" + parts[FACE_ID_USER];
                    File bdImage = new File(file);
                    return bdImage; 
                }
            }
            return null;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }
    }

    public String getUserType() {
        return this.userType;
    }
    
}

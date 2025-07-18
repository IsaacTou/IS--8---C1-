package src.main.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class UCVDataReader {
    private String ruteString;
    private String userType;
    private static int IDENTIFICATION_ID = 0;
    private static int UCV_USER_TYPE = 1;

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

    public String getUserType() {
        return this.userType;
    }
    
}

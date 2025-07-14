package src.main.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ucvDataReader {
    private String ruteString;
    private String userType;
    public ucvDataReader () {
        ruteString = "src/main/data/UCVDataBase.txt";
        userType = "";
    }

    public boolean findCi(String cI) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruteString))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(cI)) {
                    this.userType = parts[1];
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return false;
    }

    public String getuserType() {
        return this.userType;
    }
    
}

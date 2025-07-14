package src.main.model;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class SCUDataManager {

    private String ruteString;
    private static int USER_TAKEN = 0;
    private static int ALREADY_REGISTER = 2;
    private static int NOT_INVALID_OPERATIONS = 3;
    
    public SCUDataManager() {
        ruteString = "src/main/data/SCUDataBase.txt";
    }

    public void createAccount (String cI, String user, String userType, String key) {
        File file = new File(ruteString);
        boolean isEmpty = (file.length() == 0);

        try (FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw)) {

            if (!isEmpty) bw.newLine();

            bw.write(cI + ",");
            bw.write(user + ",");
            bw.write(userType + ",");
            bw.write(key);
       
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public int invalidOperation(String cI, String user) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruteString))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(cI)) {
                    return ALREADY_REGISTER;
                }
                if (parts[1].equals(user)) {
                    return USER_TAKEN;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return NOT_INVALID_OPERATIONS;
    }

}

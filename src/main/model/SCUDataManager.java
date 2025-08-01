package src.main.model;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class SCUDataManager {

    private String ruteString;
    private String loginTarget;

    private final int USER_TAKEN = 0;
    private final int ALREADY_REGISTER = 2;
    private final int NOT_INVALID_OPERATIONS = 3;
    
    public SCUDataManager() {
        ruteString = "src/main/data/SCUDataBase.txt";
        loginTarget = "";
    }
    //Constructor para la prueba UNITARIA
    public SCUDataManager(String r) {
        ruteString = r;
        loginTarget = "";
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
            bw.write(key + ",");
            bw.write("0"); // Balance inicial (Wallet)
       
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

    public boolean userExist(String data) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruteString))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equals(data)) {
                    this.loginTarget = line;
                    return true;
                } else if (parts[0].equals(data)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return false;
    }

    public boolean correctPassword(String user, String password) {

        String[] parts = this.loginTarget.split(",");
        if (parts[1].equals(user)) {
            if (parts[3].equals(password)) {
                return true;
            }
        }
        return false;

    }

    public boolean discount(String amount, String ci) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(ruteString));

		    for (int i = 0; i < lines.size(); i++) {
			    String line = lines.get(i);
			    if (line.startsWith(ci)) {

				    String[] parts = line.split(",", -1);
                    float discount = Float.parseFloat(parts[parts.length - 1])
				    - Float.parseFloat(amount);

                    if (discount < 0.0) {
                        return false;
                    }

				    parts[parts.length - 1] = Float.toString(discount);
                    
				    lines.set(i, String.join(",", parts));
				    Files.write(Paths.get(ruteString), lines);
                    return true;		
                }
            } 
            
            return false; 
        } catch(IOException x) {
			x.printStackTrace();
            return false;
        }

    }
    

    public String getCi() {
        String[] parts = this.loginTarget.split(",");
        return parts[0];
    }

    public String getUser() {
        String[] parts = this.loginTarget.split(",");
        return parts[1];
    }

    public String getUserType() {
        String[] parts = this.loginTarget.split(",");
        return parts[2];
    }

    public String getWallet() {
        String[] parts = this.loginTarget.split(",");
        return parts[4];
    }

}

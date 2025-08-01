package src.main.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;

public class MenuData {
    
    private static MenuData instance;
    private Map<String, Menu> menusData;
    private CCBData ccbData;

    private MenuData() {
        this.menusData = new HashMap<>();
    } 

    public static MenuData getInstance() {
        if (instance == null) {
            instance = new MenuData();
        }
        return instance;
    }

    public void loadMenus(String userType) {
        
        menusData.clear();
        this.ccbData = new CCBData("src/main/data/CCBData.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/data/MenusData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String title = parts[0];
                    String timeRange = parts[1];
                    String price = ccbData.getCCBByTypeUser(userType) + " Bs";
                    String[] items = parts[2].split(",");
                    String imageName = parts[3];
                    menusData.put(title, new Menu(items, timeRange, price, imageName));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer MenusData.txt: " + e.getMessage());
        }
    }

    public void saveMenusToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/data/MenusData.txt"))) {
            for (Map.Entry<String, Menu> entry : menusData.entrySet()) {
                Menu menu = entry.getValue();
                writer.write(entry.getKey() + "|" + 
                            menu.getTimeRange() + "|" + 
                            String.join(",", menu.getItems()) + "|" + 
                            menu.getDataImageName());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar los menús: " + e.getMessage());
            //JOptionPane.showMessageDialog(this, "Error al guardar los menús: " + e.getMessage());
        }
    }

    public Map<String, Menu> getMenusData() {
        return menusData;
    }
}

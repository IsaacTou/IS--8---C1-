package src.main.view.pages;

import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import src.main.view.components.*;

@SuppressWarnings("serial")
public class MenuView extends JFrame {
    
    private final Color PANEL_BG = new Color(4, 113, 166); 
    private final Color CONTENT_BG = new Color(240, 240, 240); 
    private final Color INFO_BG = new Color(220, 220, 220); 
    private boolean isAdmin;
    private JTabbedPane tabbedPane;
    private Map<String, MenuData> menusData = new HashMap<>();
    
    private class MenuData {
        String[] items;
        String timeRange;
        String price;
        String imageName;
        
        public MenuData(String[] items, String timeRange, String price, String imageName) {
            this.items = items;
            this.timeRange = timeRange;
            this.price = price;
            this.imageName = imageName;
        }
    }

 
    
    public MenuView(boolean isAdmin) {
        this.isAdmin = isAdmin;
        setSize(850, 650);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("SGCU - Menús Disponibles");
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(PANEL_BG);
        getContentPane().add(mainPanel);

        JLabel mainTitle = new JLabel("MENÚS DEL DÍA", SwingConstants.CENTER);
        mainTitle.setFont(new Font("Sans Serif", Font.BOLD, 28));
        mainTitle.setForeground(Color.WHITE);
        mainTitle.setBorder(new EmptyBorder(15, 0, 15, 0));
        mainPanel.add(mainTitle, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Sans Serif", Font.BOLD, 16));
        tabbedPane.setBackground(new Color(3, 93, 146));
        tabbedPane.setForeground(Color.WHITE);
        
        loadMenus();

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        BackButton backButton = new BackButton();
        backButton.setPreferredSize(new Dimension(150, 40));
        backButton.addActionListener(e -> dispose());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(PANEL_BG);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadMenus() {
        menusData.put("Desayuno", new MenuData(
            new String[]{"Café con leche", "Tostadas con huevo", "Jugo de naranja", "Fruta fresca"},
            "7:00 AM - 9:30 AM",
            "180.00 Bs",
            "breakfast.png"
        ));
        
        menusData.put("Almuerzo", new MenuData(
            new String[]{"Sopa del día", "Ensalada", "Jugo de Naranja", "Pan"},
            "12:00 PM - 2:30 PM",
            "180.00 Bs",
            "lunch.png"
        ));
        
        menusData.put("Cena", new MenuData(
            new String[]{"Crema de verduras", "Sándwich mixto", "Yogur", "Té o café"},
            "6:30 PM - 8:30 PM",
            "180.00 Bs",
            "dinner.png"
        ));
        
        // Cargar los menús desde el mapa
        for (Map.Entry<String, MenuData> entry : menusData.entrySet()) {
            MenuData data = entry.getValue();
            addMenuTab(entry.getKey(), data.items, data.timeRange, data.price, data.imageName);
        }
    }

    private void saveMenusToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/data/MenusData.txt"))) {
            for (Map.Entry<String, MenuData> entry : menusData.entrySet()) {
                MenuData data = entry.getValue();
                writer.write(entry.getKey() + "|" + 
                            data.timeRange + "|" + 
                            data.price + "|" + 
                            String.join(",", data.items) + "|" + 
                            data.imageName);
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar los menús: " + e.getMessage());
        }
    }

    private void addMenuTab(String title, String[] items, String timeRange, String price, String imageName) {
        JPanel menuPanel = createMenuPanel(items, timeRange, price, imageName);
        
        if (isAdmin) {
            JButton editButton = new JButton("Editar " + title);
            editButton.addActionListener(e -> {
             
                final String[] finalItems = items;
                final String finalTimeRange = timeRange;
                final String finalPrice = price;
                final String finalImageName = imageName;
                
                openEditDialog(title, finalItems, finalTimeRange, finalPrice, finalImageName);
            });
            menuPanel.add(editButton, BorderLayout.SOUTH);
        }
        
        tabbedPane.addTab(title, menuPanel);
    }

    private JPanel createMenuPanel(String[] items, String timeRange, String price, String imageName) {
        JPanel containerPanel = new JPanel(new BorderLayout(10, 10));
        containerPanel.setBackground(CONTENT_BG);
        containerPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel infoPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        infoPanel.setBackground(INFO_BG);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            new EmptyBorder(10, 15, 10, 15)
        ));

        JLabel timeLabel = new JLabel("⏰ " + timeRange);
        timeLabel.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        timeLabel.setForeground(Color.BLACK);
        
        JLabel priceLabel = new JLabel("💰 " + price);
        priceLabel.setFont(new Font("Sans Serif", Font.BOLD, 14));
        priceLabel.setForeground(new Color(0, 150, 0));

        infoPanel.add(timeLabel);
        infoPanel.add(priceLabel);
        containerPanel.add(infoPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout(15, 0));
        contentPanel.setBackground(CONTENT_BG);
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));

        ImageIcon icon = new ImageIcon("src/assets/" + imageName);
        Image scaledImage = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setBorder(new EmptyBorder(0, 0, 0, 15));
        contentPanel.add(imageLabel, BorderLayout.WEST);

        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(CONTENT_BG);

        JLabel itemsTitle = new JLabel("🍽️ Incluye:");
        itemsTitle.setFont(new Font("Sans Serif", Font.BOLD, 16));
        itemsTitle.setForeground(Color.BLACK);
        itemsTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        itemsPanel.add(itemsTitle);
        itemsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        for (String item : items) {
            JLabel itemLabel = new JLabel("• " + item);
            itemLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
            itemLabel.setForeground(Color.BLACK);
            itemLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            itemsPanel.add(itemLabel);
            itemsPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        }

        contentPanel.add(itemsPanel, BorderLayout.CENTER);
        containerPanel.add(contentPanel, BorderLayout.CENTER);

        return containerPanel;
    }

    private void openEditDialog(String currentTitle, String[] items, String timeRange, String price, String imageName) {
        JDialog editDialog = new JDialog(this, "Editar Menú: " + currentTitle, true);
        editDialog.setLayout(new BorderLayout());
        editDialog.setSize(500, 500);
        editDialog.setLocationRelativeTo(this);

        final String[] newImageName = {imageName};
        final String[] newTitle = {currentTitle};
        final String[] newTime = {timeRange};
        final String[][] newItems = {items.clone()};

        JPanel formPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

     
        JTextField titleField = new JTextField(currentTitle);
        formPanel.add(createLabeledField("Nombre del menú:", titleField));

        
        JTextField timeField = new JTextField(timeRange);
        formPanel.add(createLabeledField("Horario:", timeField));

     
        JTextArea itemsArea = new JTextArea(String.join("\n", items), 5, 20);
        itemsArea.setLineWrap(true);
        formPanel.add(createLabeledField("Items (uno por línea):", new JScrollPane(itemsArea)));

      
        JPanel imagePanel = new JPanel(new BorderLayout());
        JLabel currentImageLabel = new JLabel("Imagen actual: " + newImageName[0]);
        JButton changeImageBtn = new JButton("Cambiar Imagen");
        
        changeImageBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(editDialog);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    Path destination = Paths.get("src/assets/" + selectedFile.getName());
                    Files.copy(selectedFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
                    newImageName[0] = selectedFile.getName();
                    currentImageLabel.setText("Imagen actual: " + newImageName[0]);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(editDialog, "Error al cambiar la imagen: " + ex.getMessage());
                }
            }
        });
        
        imagePanel.add(currentImageLabel, BorderLayout.NORTH);
        imagePanel.add(changeImageBtn, BorderLayout.SOUTH);
        formPanel.add(imagePanel);

     
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton saveButton = new JButton("Guardar Cambios");
        JButton cancelButton = new JButton("Cancelar");

        saveButton.addActionListener(e -> {
            newTitle[0] = titleField.getText();
            newTime[0] = timeField.getText();
            newItems[0] = itemsArea.getText().split("\n");
        
            if (!newTitle[0].equals(currentTitle)) {
                menusData.remove(currentTitle);
            }
            menusData.put(newTitle[0], new MenuData(newItems[0], newTime[0], price, newImageName[0]));
            
       
            tabbedPane.removeAll();
            for (Map.Entry<String, MenuData> entry : menusData.entrySet()) {
                MenuData data = entry.getValue();
                addMenuTab(entry.getKey(), data.items, data.timeRange, data.price, data.imageName);
            }
            saveMenusToFile();
            
            editDialog.dispose();
            JOptionPane.showMessageDialog(this, "Menú actualizado correctamente");
        });

        cancelButton.addActionListener(e -> editDialog.dispose());
        buttonPanel.add(saveButton);
buttonPanel.add(cancelButton);

        editDialog.add(formPanel, BorderLayout.CENTER);
        editDialog.add(buttonPanel, BorderLayout.SOUTH);
        editDialog.setVisible(true);
    }

    private JPanel createLabeledField(String label, Component field) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(label), BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }
}
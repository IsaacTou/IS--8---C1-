package src.main.view.pages;

import java.awt.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class EditMenuDialog extends JDialog { // Cambia JFrame por JDialog

    private JTextField titleField;
    private JTextField timeField;
    private JTextArea itemsArea;
    private JButton saveButton; 
    private JButton cancelButton; 
    private String[] newImageName;

    public EditMenuDialog(String currentTitle, String[] items, String timeRange, String price, String imageName) {
        
        super((Frame) null, "Editar Menú: " + currentTitle, true); // Llama al constructor de JDialog
        setLayout(new BorderLayout());
        setSize(500, 500);
        setLocationRelativeTo(null);

        this.newImageName = new String[] { imageName };

        JPanel formPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        titleField = new JTextField(currentTitle);
        formPanel.add(createLabeledField("Nombre del menú:", titleField));

        timeField = new JTextField(timeRange);
        formPanel.add(createLabeledField("Horario:", timeField));

        itemsArea = new JTextArea(String.join("\n", items), 5, 20);
        itemsArea.setLineWrap(true);
        formPanel.add(createLabeledField("Items (uno por línea):", new JScrollPane(itemsArea)));

        JPanel imagePanel = new JPanel(new BorderLayout());
        JLabel currentImageLabel = new JLabel("Imagen actual: " + newImageName[0]);
        JButton changeImageBtn = new JButton("Cambiar Imagen");

        changeImageBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    Path destination = Paths.get("src/assets/" + selectedFile.getName());
                    Files.copy(selectedFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
                    newImageName[0] = selectedFile.getName();
                    currentImageLabel.setText("Imagen actual: " + newImageName[0]);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al cambiar la imagen: " + ex.getMessage());
                }
            }
        });

        imagePanel.add(currentImageLabel, BorderLayout.NORTH);
        imagePanel.add(changeImageBtn, BorderLayout.SOUTH);
        formPanel.add(imagePanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        saveButton = new JButton("Guardar Cambios");
        saveButton.setActionCommand("SAVE");
        cancelButton = new JButton("Cancelar");

        cancelButton.addActionListener(e -> {
            setVisible(false);
            dispose();
        });


        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createLabeledField(String label, Component field) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(label), BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    public void setController(ActionListener controller) {
        saveButton.addActionListener(controller);
    }

    public void reply() { JOptionPane.showMessageDialog(this, "Menú actualizado correctamente");}

    public String getTittle() {
        return titleField.getText();
    }

    public String getTimeField() {
        return timeField.getText();
    }

    public String[] getItems() {
        return itemsArea.getText().split("\n");
    }

    public String getImageName() {
        return newImageName[0];
    }
}
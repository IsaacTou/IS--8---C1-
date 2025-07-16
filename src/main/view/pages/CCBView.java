package src.main.view.pages;


import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import src.main.view.components.*;

@SuppressWarnings("serial")
public class CCBView extends JFrame {
    
    private JPanel mainPanel;
    private JPanel formPanel;
    private JTextField cfTxt, cvTxt, nbTxt, mermaTxt;
    private JButton saveButton;
    private CalculateButton calculateButton;
    private BackButton backButton;

    public CCBView() {
        setSize(1000, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("SGCU - Costo Cubierto de Bandeja");
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
     
        mainPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("src/assets/register_background.png");
                g.drawImage(icon.getImage(), 0, 0, 300, getHeight(), this);
                g.drawImage(icon.getImage(), getWidth() - 300, 0, 300, getHeight(), this);
            }
        };
        mainPanel.setPreferredSize(new Dimension(1000, 700));
        getContentPane().add(mainPanel);

   
        formPanel = new JPanel(null);
        formPanel.setOpaque(false);
        formPanel.setBounds(300, 30, 400, 620);
        mainPanel.add(formPanel);

        
        JLabel title = new JLabel("CÁLCULO CCB", SwingConstants.CENTER);
        title.setBounds(0, 10, 400, 40);
        title.setFont(new Font("Sans Serif", Font.BOLD, 28));
        title.setForeground(new Color(51, 51, 51));
        formPanel.add(title);

        int fieldWidth = 300;
        int xPos = (400 - fieldWidth) / 2;
        int yPos = 70;
        int spacing = 80;

   
        addCenteredField("Costos fijos totales (Bs):", xPos, yPos, fieldWidth);
        cfTxt = createCenteredTextField(xPos, yPos + 25, fieldWidth);

     
        addSeparator(xPos, yPos + 70, fieldWidth);

        yPos += spacing;

        
        addCenteredField("Costos variables (%):", xPos, yPos, fieldWidth);
        cvTxt = createCenteredTextField(xPos, yPos + 25, fieldWidth);

        yPos += spacing;

        
        addCenteredField("Cantidad de bandejas:", xPos, yPos, fieldWidth);
        nbTxt = createCenteredTextField(xPos, yPos + 25, fieldWidth);

        yPos += spacing;

        
        addCenteredField("Porcentaje de merma (%):", xPos, yPos, fieldWidth);
        mermaTxt = createCenteredTextField(xPos, yPos + 25, fieldWidth);

        yPos += 90;

     
        int buttonWidth = 200;
        int buttonX = (400 - buttonWidth) / 2;
        int buttonHeight = 45;

        saveButton = createStyledButton("Guardar Datos", buttonX, yPos, buttonWidth, buttonHeight);
        yPos += 60;
        calculateButton = createStyledCalculateButton(buttonX, yPos, buttonWidth, buttonHeight);
        yPos += 60;
        backButton = createStyledBackButton(buttonX, yPos, buttonWidth, buttonHeight);
    }

    private void addCenteredField(String label, int x, int y, int width) {
        JLabel jLabel = new JLabel(label, SwingConstants.CENTER);
        jLabel.setBounds(x, y, width, 20);
        jLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
        jLabel.setForeground(new Color(51, 51, 51));
        formPanel.add(jLabel);
    }

    private void addSeparator(int x, int y, int width) {
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBounds(x, y, width, 2);
        separator.setForeground(new Color(4, 113, 166));
        formPanel.add(separator);
    }

    private JTextField createCenteredTextField(int x, int y, int width) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, 30);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        formPanel.add(textField);
        return textField;
    }

    private JButton createStyledButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(new Color(4, 113, 166));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Sans Serif", Font.BOLD, 16));
        formPanel.add(button);
        return button;
    }

    private CalculateButton createStyledCalculateButton(int x, int y, int width, int height) {
        CalculateButton button = new CalculateButton();
        button.setText("Calcular");
        button.setBounds(x, y, width, height);
        button.setBackground(new Color(4, 113, 166));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Sans Serif", Font.BOLD, 16));
        formPanel.add(button);
        return button;
    }

    private BackButton createStyledBackButton(int x, int y, int width, int height) {
        BackButton button = new BackButton();
        button.setText("Volver");
        button.setBounds(x, y, width, height);
        button.setBackground(new Color(4, 113, 166));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Sans Serif", Font.BOLD, 16));
        formPanel.add(button);
        return button;
    }

  
    public String getCostosFijos() { return cfTxt.getText(); }
    public String getCostosVariables() { return cvTxt.getText(); }
    public String getNumeroBandejas() { return nbTxt.getText(); }
    public String getPorcentajeMerma() { return mermaTxt.getText(); }

    public void setController(ActionListener controller) {
        saveButton.addActionListener(controller);
        calculateButton.addActionListener(controller);
        backButton.addActionListener(controller);
    }
}

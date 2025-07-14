
package src.main.view;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import java.awt.event.ActionListener;

public class Register extends JFrame {
    
    JPanel panel;
    JPanel imageBackground;
    JButton button;
    int boxXPosition = 520;
    int ciBoxYPosition = 230;
    int userBoxYPosition = 280;
    int keyBoxYPosition = 330;
    JTextField ciBox;
    JTextField userBox;
    JTextField keyBox;


    public Register() {
        setSize(850,650); // (ancho, largo)
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cuando cierre la ventana el programa finalizara
        setTitle("prueba"); // titulo de la ventana
        setLocationRelativeTo(null); // centra la localizacion de la pantalla 
        addPanel();
        addImage();
    }

    private void addPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel); // agregamos el panel a la ventana
        JLabel title = new JLabel("REGISTRO");
        title.setBounds(415, 132, 350, 40);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Sans Serif", Font.BOLD, 48));
        //title.setBackground(new Color(192, 192, 192, 128)); // RGB (gris claro) con alfa 128
        panel.add(title);
        addDescriptions();
        addButton();
        addciBox();
        adduserBox();
        addkeyBox();
    }
    
    private void addImage() {
        imageBackground = new JPanel();
        imageBackground.setLayout(null);
        this.getContentPane().add(panel);
        imageBackground.setSize(320,650);
        imageBackground.setBackground(new Color(4,113,166));
        JLabel imageLabel = new JLabel(new ImageIcon("src/main/images/registro-fondo.png"));
        imageLabel.setBounds(0, 0, 320, 650);
        imageBackground.add(imageLabel);
        panel.add(imageBackground);
    }

    private void addDescriptions() {
        int xPosition = 440;
        JLabel ciLabel = new JLabel("CI:");
        ciLabel.setBounds(xPosition, 230, 200, 20);
        ciLabel.setFont(new Font("Sans Serif", Font.PLAIN,18));
        panel.add(ciLabel);

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(xPosition, 280, 200, 20);
        userLabel.setFont(new Font("Sans Serif", Font.PLAIN,18));
        panel.add(userLabel);

        JLabel keyLabel = new JLabel("Clave:");
        keyLabel.setBounds(xPosition, 330, 200, 20);
        keyLabel.setFont(new Font("Sans Serif", Font.PLAIN,18));
        panel.add(keyLabel);
    }

    private void addciBox() {
        ciBox = new JTextField();
        ciBox.setBounds(boxXPosition, ciBoxYPosition, 200, 20);
        panel.add(ciBox);

    }

    private void adduserBox() {
        userBox = new JTextField();
        userBox.setBounds(boxXPosition, userBoxYPosition, 200, 20);
        panel.add(userBox);

    }

    private void addkeyBox() {
        keyBox = new JTextField();
        keyBox.setBounds(boxXPosition,keyBoxYPosition,200,20);
        panel.add(keyBox);
    }

    private void addButton() {
        button = new JButton("Registrar");
        button.setBounds(boxXPosition, 410, 127, 28);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(4,113,166));
        button.setForeground(Color.WHITE);
        button.setActionCommand("REGISTRO");
        panel.add(button);

    }

    public String getCi () {
        return ciBox.getText();
    }

    public String getUser () {
        return userBox.getText();
    }

    public String getKey () {
        return keyBox.getText();
    }

    public void setControlador(ActionListener controlador) {
        button.addActionListener(controlador);
    }


}

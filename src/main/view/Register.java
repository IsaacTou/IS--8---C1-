
package src.main.view;
import java.awt.Color;

import javax.swing.*;

public class Register extends JFrame {
    
    JPanel panel;
    JButton button;
    JTextField ciBox;
    JTextField userBox;
    JTextField keyBox;


    public Register() {
        setSize(850,650); // (ancho, largo)
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cuando cierre la ventana el programa finalizara
        setTitle("prueba"); // titulo de la ventana
        setLocationRelativeTo(null); // centra la localizacion de la pantalla 
        addPanel();


    }

    private void addPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel); // agregamos el panel a la ventana
        addButton();
        addciBox();
        adduserBox();
        addkeyBox();
    }
    
    private void addciBox() {
        ciBox = new JTextField();
        ciBox.setBounds(10, 30, 200, 20);
        panel.add(ciBox);

    }

    private void adduserBox() {
        userBox = new JTextField();
        userBox.setBounds(10, 100, 200, 20);
        panel.add(userBox);

    }

    private void addkeyBox() {
        keyBox = new JTextField();
        keyBox.setBounds(10,200,200,20);
        panel.add(keyBox);
    }

    private void addButton() {
        button = new JButton("Registrar");
        button.setBounds(10, 250, 130, 20);
        button.setForeground(Color.BLUE);
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

}

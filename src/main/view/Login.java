package src.main.view;
import javax.swing.*;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    JPanel panel;
    JTextField userBox;
    JPasswordField keyBox;
    JButton button;


    public Login() {
        setSize(850,650); // (ancho, largo)
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cuando cierre la ventana el programa finalizara
        setTitle("Sistema del comedor universitario SCU - Login"); // titulo de la ventana
        setLocationRelativeTo(null); // centra la localizacion de la pantalla 
        addPanel();

    }

    public void addPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        addUserBox();
        addKeyBox();
        addButton();

    }

    public void addUserBox() {
        userBox = new JTextField();
        userBox.setBounds(10,20,200,50);
        panel.add(userBox);
    }

    public void addKeyBox () {
        keyBox = new JPasswordField();
        keyBox.setBounds(10,100,200,50);
        panel.add(keyBox);
    }

    public void addButton () {
        button = new JButton("Iniciar sesion");
        button.setBounds(10,200,200,50);
        button.setActionCommand("LOGUEAR");
        panel.add(button);
    }

    public String getUser() {
        return userBox.getText();
    }

    public String getKey() {
        return new String(keyBox.getPassword());

    }

    public void setController(ActionListener controller) {
        button.addActionListener(controller);
    }

    public void confirm(String message) {
        JOptionPane.showMessageDialog(null,
                                      message,
                                      "Operacion exitosa",
                                      JOptionPane.DEFAULT_OPTION);
    }
    
    public void warning(String message) {
        JOptionPane.showMessageDialog(null,
                                      message,
                                      "Operacion fallida",
                                      JOptionPane.WARNING_MESSAGE);
    }
    
}

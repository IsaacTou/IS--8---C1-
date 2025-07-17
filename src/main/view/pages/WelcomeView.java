package src.main.view.pages;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import src.main.view.components.ImagePanel;
import src.main.view.components.RoundedPanel;

@SuppressWarnings("serial")
public class WelcomeView extends JFrame {

    private JPanel panel;
    private JButton loginButton;
    private JButton registerButton;

    public WelcomeView() {
        setTitle("SGCU - Bienvenido");
        setSize(850, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addPanel();
        addImage();
    }

    private void addPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);

        addHeader();
        addContentBox();
    }

    private void addImage() {
        ImagePanel imagePanel = new ImagePanel("register_background.png", 320, 650, 0, 0);
        panel.add(imagePanel);
    }

    private void addHeader() {
        Color darkBlue = new Color(0, 51, 102);
        // Título dividido en dos líneas
        JLabel title1 = new JLabel("Servicio de Comedor");
        title1.setFont(new Font("Sans Serif", Font.BOLD, 38));
        title1.setBounds(330, 60, 400, 30);
        title1.setForeground(darkBlue);

        JLabel title2 = new JLabel("Universitario");
        title2.setFont(new Font("Sans Serif", Font.BOLD, 38));
        title2.setBounds(330, 100, 400, 30);
        title2.setForeground(darkBlue);

        JLabel subtitle = new JLabel("Universidad Central de Venezuela");
        subtitle.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        subtitle.setForeground(new Color(80, 80, 80));
        subtitle.setBounds(335, 135, 400, 20);

        // Logo a la derecha del texto
        ImageIcon logoIcon = new ImageIcon("src/assets/ucv_logo.png");
        Image scaledLogo = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setBounds(730, 60, 80, 80); // Alineado a la derecha

        // Agregar al panel
        panel.add(title1);
        panel.add(title2);
        panel.add(subtitle);
        panel.add(logoLabel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(4, 113, 166));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Sans Serif", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public void setController(ActionListener controller) {
        loginButton.setActionCommand("GO_TO_LOGIN");
        registerButton.setActionCommand("GO_TO_REGISTER");
        loginButton.addActionListener(controller);
        registerButton.addActionListener(controller);
    }

    private void addContentBox() {
        RoundedPanel whiteBox = new RoundedPanel(30); // 30 píxeles de borde redondeado
        whiteBox.setBackground(Color.WHITE); // Muy importante para que pinte blanco
        whiteBox.setLayout(null);
        whiteBox.setBounds(420, 250, 300, 200);
        panel.add(whiteBox);
    
        JLabel welcomeMessage = new JLabel("¡Bienvenido! ¿Qué desea hacer?");
        welcomeMessage.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        welcomeMessage.setBounds(20, 10, 280, 30);
        welcomeMessage.setForeground(new Color(0, 51, 102));
        whiteBox.add(welcomeMessage);
    
        loginButton = createStyledButton("Iniciar sesión");
        loginButton.setBounds(60, 60, 180, 40);
        whiteBox.add(loginButton);
    
        registerButton = createStyledButton("Registrarse");
        registerButton.setBounds(60, 120, 180, 40);
        whiteBox.add(registerButton);
    }



}

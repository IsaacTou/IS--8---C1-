package src.main.view.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class WelcomeView extends JFrame {

    private JButton loginButton;
    private JButton registerButton;

    public WelcomeView() {
        setTitle("Bienvenido a SGCU");
        setSize(850, 650); // mismo tamaño que las demás
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Fondo opcional
        getContentPane().setBackground(new Color(230, 240, 250));

        // Título
        JLabel title = new JLabel("Bienvenido a SGCU", SwingConstants.CENTER);
        title.setBounds(200, 50, 450, 60);
        title.setFont(new Font("Sans Serif", Font.BOLD, 36));
        title.setForeground(new Color(0, 70, 140));
        add(title);

        // Subtítulo
        JLabel subtitle = new JLabel("¿Qué deseas hacer?", SwingConstants.CENTER);
        subtitle.setBounds(250, 130, 350, 30);
        subtitle.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        subtitle.setForeground(new Color(60, 60, 60));
        add(subtitle);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 20, 20));
        buttonPanel.setBounds(300, 200, 250, 130);
        buttonPanel.setBackground(new Color(230, 240, 250));

        loginButton = createStyledButton("Iniciar sesión");
        registerButton = createStyledButton("Registrarse");

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        add(buttonPanel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Sans Serif", Font.BOLD, 16));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    public void setController(ActionListener controller) {
        loginButton.setActionCommand("GO_TO_LOGIN");
        registerButton.setActionCommand("GO_TO_REGISTER");
        loginButton.addActionListener(controller);
        registerButton.addActionListener(controller);
    }
}
package src.main.view.pages;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import src.main.view.components.LoginButton;
import src.main.view.components.BackButton;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class LoginView extends JFrame {
	JPanel panel;
	JTextField userBox;
	JPasswordField keyBox;
	LoginButton loginButton;
	BackButton backButton;
	JPanel imageBackground;
	int boxYPosition = 280; // Posicion Y de los cuadros de texto
	int boxXPosition = 550; // Posicion X de los cuadros de texto

	public LoginView() {
		setSize(850,650); // (ancho, largo)
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Cuando cierre la ventana el programa finalizara
		setTitle("SGCU - Login"); // titulo de la ventana
		setLocationRelativeTo(null); // centra la localizacion de la pantalla 
		addPanel();
	}

	public void addPanel() {
		panel = new JPanel();
		panel.setLayout(null);
		this.getContentPane().add(panel);
		JLabel title = new JLabel("INICIO DE SESION");
		title.setBounds(370, 132, 420, 40);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Sans Serif", Font.BOLD, 48));
		//title.setBackground(new Color(192, 192, 192, 128)); // RGB (gris claro) con alfa 128
		panel.add(title);
		addUserBox();
		addKeyBox();
		addButtons();
		addImage();
		addDescriptions();
	}

	public void addUserBox() {
		userBox = new JTextField();
		userBox.setBounds(boxXPosition,boxYPosition - 30,200,20);
		panel.add(userBox);
	}

	public void addKeyBox () {
		keyBox = new JPasswordField();
		keyBox.setBounds(boxXPosition,boxYPosition,200,20);
		panel.add(keyBox);
	}

	public void addButtons () {
		loginButton = new LoginButton();
		loginButton.setBounds(430 + 192,380,127,28);
		panel.add(loginButton);
		backButton = new BackButton();
		panel.add(backButton);
		backButton.setBounds(430, 380, 127, 28);
	}

	private void addDescriptions() {
		JLabel userLabel = new JLabel("Usuario:");
		userLabel.setBounds(430, boxYPosition-30, 200, 20);
		userLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		panel.add(userLabel);
		
		JLabel keyLabel = new JLabel("Contraseña:");
		keyLabel.setBounds(430, boxYPosition, 200, 20);
		keyLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		panel.add(keyLabel);
	}

	private void addImage() {
		// Crear el panel para la imagen de fondo
		imageBackground = new JPanel();
		imageBackground.setLayout(null);
		imageBackground.setSize(320, 650);
		imageBackground.setBackground(new Color(4, 113, 166)); // Color de fondo (opcional)
		imageBackground.setBounds(0, 0, 320, 650); // Establece el tamaño y posición del panel

		// Cargar la imagen desde la ruta especificada
		ImageIcon imageIcon = new ImageIcon("src\\assets\\register_background.png");
		JLabel imageLabel = new JLabel(imageIcon);
		imageLabel.setBounds(0, 0, 320, 650); // Ajustar el tamaño y posición de la imagen

		// Agregar la imagen al panel
		imageBackground.add(imageLabel);

		// Agregar el panel de imagen al panel principal
		panel.add(imageBackground);
	}

	public String getUser() {
		return userBox.getText();
	}

	public String getKey() {
		return new String(keyBox.getPassword());
	}

	public void setController(ActionListener controller) {
		loginButton.addActionListener(controller);
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

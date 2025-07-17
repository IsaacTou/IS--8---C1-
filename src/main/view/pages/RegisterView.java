package src.main.view.pages;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;
import src.main.view.components.*;

@SuppressWarnings("serial")
public class RegisterView extends JFrame {

	JPanel panel;
	RegisterButton registerButton;
	BackButton backButton;
	int boxXPosition = 520;
	int ciBoxYPosition = 230;
	int userBoxYPosition = 280;
	int keyBoxYPosition = 330;
	int xPosition = 440;
	JTextField ciBox;
	JTextField userBox;
	JPasswordField keyBox;


	public RegisterView() {
		setSize(850,650); // (ancho, largo)
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Cuando cierre la ventana el programa finalizara
		setTitle("SGCU - Registro"); // titulo de la ventana
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
		addButtons();
		addciBox();
		adduserBox();
		addkeyBox();
	}

	private void addImage() {
		ImagePanel imagePanel = new ImagePanel("register_background.png", 320, 650, 0, 0);
		panel.add(imagePanel);
	}

	private void addDescriptions() {

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
		keyBox = new JPasswordField();
		keyBox.setBounds(boxXPosition,keyBoxYPosition,200,20);
		panel.add(keyBox);
	}

	private void addButtons() {
		registerButton = new RegisterButton();
		registerButton.setBounds(xPosition + 192, 410, 127, 28);
		panel.add(registerButton);

		backButton = new BackButton();
		backButton.setBounds(xPosition, 410, 127, 28);
		panel.add(backButton);
	}

	public String getCi () {
		return ciBox.getText();
	}

	public String getUser () {
		return userBox.getText();
	}

	public String getKey () {
		return new String(keyBox.getPassword());
	}

	public void setController(ActionListener controller) {
		registerButton.addActionListener(controller);
		backButton.addActionListener(controller);
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

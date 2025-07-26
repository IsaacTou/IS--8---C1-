package src.main.view.pages;
import java.awt.*;
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

	private final int IMAGE_WIDTH = 320;
	private final int FORM_WIDTH = 350;
	private final int FORM_HEIGHT = 280;

	public RegisterView() {
		setSize(850, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("SGCU - Registro");
		setLocationRelativeTo(null);
		addPanel();
	}

	private void addPanel() {
		panel = new JPanel(null);
        	panel.setBackground(new Color(240, 240, 240));
		this.getContentPane().add(panel);
        	addImage();
        	addTitle();
        	addFormContainer();
	}

	private void addImage() {
		try {
			ImageIcon icon = new ImageIcon("src/assets/register_background.png");
			if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
				JLabel imageLabel = new JLabel(icon);
				imageLabel.setBounds(0, 0, IMAGE_WIDTH, 650);
				panel.add(imageLabel);
				panel.setComponentZOrder(imageLabel, panel.getComponentCount()-1);
			}
		} catch (Exception e) {
			System.err.println("Error al cargar la imagen: " + e.getMessage());
		}
	}

	private void addTitle() {
		JLabel title = new JLabel("REGISTRO");
		int titleWidth = 400;
		int titleX = IMAGE_WIDTH + (850 - IMAGE_WIDTH - titleWidth) / 2;

		title.setBounds(titleX, 120, titleWidth, 40);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Sans Serif", Font.BOLD, 32));
		title.setForeground(new Color(51, 51, 51));
		panel.add(title);
	}

	private void addButtons(JPanel container) {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		buttonPanel.setBounds(25, 220, 300, 50);

		buttonPanel.setOpaque(false);

		backButton = new BackButton();
		registerButton = new RegisterButton();

		buttonPanel.add(backButton);
		buttonPanel.add(registerButton);
		container.add(buttonPanel);
	}

	private void addFormContainer() {
		int formX = IMAGE_WIDTH + (850 - IMAGE_WIDTH - FORM_WIDTH) / 2;

		RoundedPanel formContainer = new RoundedPanel(20);
		formContainer.setBounds(formX, 180, FORM_WIDTH, FORM_HEIGHT);
		formContainer.setBackground(Color.WHITE);
		formContainer.setLayout(null);
		panel.add(formContainer);

		addRoundedInputField("C.I.:", 40, formContainer);
		addRoundedInputField("Usuario:", 100, formContainer);
		addRoundedInputField("Contraseña:", 160, formContainer);
		addButtons(formContainer);
	}

	private void addRoundedInputField(String labelText, int yPos, JPanel container) {
		RoundedPanel fieldPanel = new RoundedPanel(15);
		fieldPanel.setBackground(new Color(230, 230, 230)); 
		fieldPanel.setBounds(25, yPos, 300, 40);
		fieldPanel.setLayout(new BorderLayout());

		if (labelText.equals("Contraseña:")) {
			keyBox = new JPasswordField();
			keyBox.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
			keyBox.setOpaque(true);
			keyBox.setBackground(new Color(240, 240, 240)); 
			fieldPanel.add(keyBox, BorderLayout.CENTER);
		} else if (labelText.equals("Usuario:")) {
			userBox = new JTextField();
			userBox.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
			userBox.setOpaque(true);
			userBox.setBackground(new Color(240, 240, 240));
			fieldPanel.add(userBox, BorderLayout.CENTER);
		} else {
			ciBox = new JTextField();
			ciBox.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
			ciBox.setOpaque(true);
			ciBox.setBackground(new Color(240, 240, 240));
			fieldPanel.add(ciBox, BorderLayout.CENTER);
		}

		JLabel label = new JLabel(labelText);
		label.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		label.setBounds(25, yPos - 25, 200, 20);
		container.add(label);
		container.add(fieldPanel);
	}

	public String getCi() {
		return ciBox.getText();
	}

	public String getUser() {
		return userBox.getText();
	}

	public String getKey() {
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

package src.main.view.pages;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import src.main.view.components.*;

@SuppressWarnings("serial")
public class ScanMenuView extends JFrame {

	JPanel panel;
	ScanButton scanButton;
	BackButton backButton;
	int boxXPosition = 520;
	int userBoxYPosition = 280;
	int xPosition = 440;
	ImageButton imageBox;
	JTextField userBox;

	private final int IMAGE_WIDTH = 320;
	private final int FORM_WIDTH = 350;
	private final int FORM_HEIGHT = 220;

	public ScanMenuView() {
		setSize(850, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("SGCU - Escaneo");
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
				panel.setComponentZOrder(imageLabel, panel.getComponentCount() - 1);
			}
		} catch (Exception e) {
			System.err.println("Error al cargar la imagen: " + e.getMessage());
		}
	}

	private void addTitle() {
		JLabel title = new JLabel("ESCANEO");
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
		buttonPanel.setBounds(25, 160, 300, 50);
		buttonPanel.setOpaque(false);

		backButton = new BackButton();
		scanButton = new ScanButton();

		buttonPanel.add(backButton);
		buttonPanel.add(scanButton);
		container.add(buttonPanel);
	}

	private void addFormContainer() {
		int formX = IMAGE_WIDTH + (850 - IMAGE_WIDTH - FORM_WIDTH) / 2;

		RoundedPanel formContainer = new RoundedPanel(20);
		formContainer.setBounds(formX, 180, FORM_WIDTH, FORM_HEIGHT);
		formContainer.setBackground(Color.WHITE);
		formContainer.setLayout(null);
		panel.add(formContainer);

		addRoundedInputField("Usuario:", 40, formContainer);
		addRoundedInputField("Imágen:", 100, formContainer);
		addButtons(formContainer);
	}

	private void addRoundedInputField(String labelText, int yPos, JPanel container) {
		RoundedPanel fieldPanel = new RoundedPanel(15);
		fieldPanel.setBackground(new Color(230, 230, 230)); 
		fieldPanel.setBounds(25, yPos, 300, 40);
		fieldPanel.setLayout(new BorderLayout());

		if (labelText.equals("Usuario:")) {
			userBox = new JTextField();
			userBox.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
			userBox.setOpaque(true);
			userBox.setBackground(new Color(240, 240, 240));
			fieldPanel.add(userBox, BorderLayout.CENTER);
		} else if (labelText.equals("Imágen:")) {
			imageBox = new ImageButton();
			// imageBox.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
			imageBox.setOpaque(true);
			// imageBox.setBackground(new Color(240, 240, 240));
			fieldPanel.add(imageBox, BorderLayout.CENTER);
		}

		JLabel label = new JLabel(labelText);
		label.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		label.setBounds(25, yPos - 25, 200, 20);
		container.add(label);
		container.add(fieldPanel);
	}

	public String getUser() {
		return userBox.getText();
	}

	public void setController(ActionListener controller) {
		scanButton.addActionListener(controller);
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

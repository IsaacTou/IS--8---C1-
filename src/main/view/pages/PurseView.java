package src.main.view.pages;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import src.main.view.components.*;

@SuppressWarnings("serial")
public class PurseView extends JFrame {

	JPanel panel;
	ReloadButton reloadButton;
	BackButton backButton;
	JTextField amountBox, codeBox;
	JComboBox bankBox;
	JLabel balanceLabel;

	private final int IMAGE_WIDTH = 320;
	private final int FORM_WIDTH = 350;
	private final int FORM_HEIGHT = 280;

	public PurseView() {
		setSize(850,650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("SGCU - Ver Monedero");
		setLocationRelativeTo(null);
		addPanel();
	}

	private void addPanel() {
		panel = new JPanel(null);
		panel.setBackground(new Color(240, 240, 240));
		this.getContentPane().add(panel);
		addImage();
		// addTitle();
		addFormContainer();
	}

	public void addTitle(String balance) {
		JLabel title = new JLabel("RECARGAR");
		int titleWidth = 400;
		int titleX = IMAGE_WIDTH + (850 - IMAGE_WIDTH - titleWidth) / 2;

		title.setBounds(titleX, 120, titleWidth, 40);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Sans Serif", Font.BOLD, 32));
		title.setForeground(new Color(51, 51, 51));
		panel.add(title);

		balanceLabel = new JLabel("Saldo Actual: " + balance + "Bs");
		balanceLabel.setBounds(titleX, 120 + 24, titleWidth, 40);
		balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		balanceLabel.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		panel.add(balanceLabel);
	}
	public void refreshTitle(String balance) {
		balanceLabel.setText("Saldo Actual: " + balance + "Bs");
	}

	private void addFormContainer() {
		int formX = IMAGE_WIDTH + (850 - IMAGE_WIDTH - FORM_WIDTH) / 2;

		RoundedPanel formContainer = new RoundedPanel(20);
		formContainer.setBounds(formX, 180, FORM_WIDTH, FORM_HEIGHT);
		formContainer.setBackground(Color.WHITE);
		formContainer.setLayout(null);
		panel.add(formContainer);

		addRoundedInputField("Saldo a recargar:", 40, formContainer);
		addRoundedInputField("Banco:", 100, formContainer);
		addRoundedInputField("Código:", 160, formContainer);
		addButtons(formContainer);
	}

	private void addRoundedInputField(String labelText, int yPos, JPanel container) {
		RoundedPanel fieldPanel = new RoundedPanel(15);
		fieldPanel.setBackground(new Color(230, 230, 230)); 
		fieldPanel.setBounds(25, yPos, 300, 40);
		fieldPanel.setLayout(new BorderLayout());

		if (labelText.equals("Banco:")) {
			String s1[] = { "Banco de Venezuela", "Bancamiga", "Banco Mercantil", "Banesco" };
			bankBox = new JComboBox(s1);
			bankBox.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
			bankBox.setOpaque(true);
			bankBox.setBackground(new Color(240, 240, 240));
			fieldPanel.add(bankBox, BorderLayout.CENTER);
		} else if (labelText.equals("Código:")) {
			codeBox = new JTextField();
			codeBox.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
			codeBox.setOpaque(true);
			codeBox.setBackground(new Color(240, 240, 240));
			fieldPanel.add(codeBox, BorderLayout.CENTER);
		} else {
			amountBox = new JTextField();
			amountBox.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
			amountBox.setOpaque(true);
			amountBox.setBackground(new Color(240, 240, 240));
			fieldPanel.add(amountBox, BorderLayout.CENTER);
		}

		JLabel label = new JLabel(labelText);
		label.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		label.setBounds(25, yPos - 25, 200, 20);
		container.add(label);
		container.add(fieldPanel);
	}

	private void addButtons(JPanel container) {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		buttonPanel.setBounds(25, 220, 300, 50);
		buttonPanel.setOpaque(false);

		backButton = new BackButton();
		reloadButton = new ReloadButton();

		buttonPanel.add(backButton);
		buttonPanel.add(reloadButton);
		container.add(buttonPanel);
	}

	public String getCode() {
		return codeBox.getText();
	}

	public String getAmount() {
		return amountBox.getText();
	}

	public void setController(ActionListener controller) {
		reloadButton.addActionListener(controller);
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
}

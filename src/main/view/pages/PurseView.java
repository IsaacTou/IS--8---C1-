
package src.main.view.pages;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import src.main.view.components.*;
import java.awt.event.ActionListener;

public class PurseView extends JFrame {

	JPanel panel;
	JPanel imageBackground;
	Button reloadButton;
	int boxXPosition = 520;
	int amountBoxYPosition = 265;
	int bankBoxYPosition = 265 + 34;
	int codeBoxYPosition = 265 + 34 + 34;
	JTextField amountBox, codeBox;
	JComboBox bankBox;

	public PurseView() {
		setSize(850,650); // (ancho, largo)
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Cuando cierre la ventana el programa finalizara
		setTitle("SGCU - Ver Monedero"); // titulo de la ventana
		setLocationRelativeTo(null); // centra la localizacion de la pantalla 
		addPanel();
		addImage();
	}

	private void addPanel() {
		panel = new JPanel();
		panel.setLayout(null);
		this.getContentPane().add(panel);
		JLabel title = new JLabel("RECARGAR");
		title.setBounds(415, 132, 350, 40);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Sans Serif", Font.BOLD, 48));
		panel.add(title);
		addDescriptions();
		addButtons();
		addAmountBox();
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

	private void addDescriptions() {
		int xPosition = 500;

		double hardcodedBalance = 0.00;
		JLabel balanceLabel = new JLabel("Saldo Actual: " + hardcodedBalance + "Bs");

		balanceLabel.setBounds(xPosition, 230, 200, 20);
		balanceLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		panel.add(balanceLabel);
	}

	private void addAmountBox() {
		int xPosition = 450;

		JLabel amountLabel = new JLabel("Saldo a recargar: ");
		amountLabel.setBounds(xPosition, 230 + 34, 200, 20);
		amountLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		amountBox = new JTextField();
		amountBox.setBounds(boxXPosition + 80, amountBoxYPosition, 150, 20);
		panel.add(amountBox);
		panel.add(amountLabel);

		JLabel bankLabel = new JLabel("Banco: ");
		bankLabel.setBounds(xPosition, 230 + 68, 200, 20);
		bankLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		String s1[] = { "Banco de Venezuela", "Bancamiga", "Banco Mercantil", "Banesco" };
		bankBox = new JComboBox(s1);
		bankBox.setBounds(boxXPosition + 80, bankBoxYPosition, 150, 20);
		panel.add(bankBox);
		panel.add(bankLabel);

		JLabel codeLabel = new JLabel("Codigo: ");
		codeLabel.setBounds(xPosition, 230 + 68 + 34, 200, 20);
		codeLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		codeBox = new JTextField();
		codeBox.setBounds(boxXPosition + 80, codeBoxYPosition, 150, 20);
		panel.add(codeBox);
		panel.add(codeLabel);
	}

	private void addButtons() {
		reloadButton = new ReloadButton();
		reloadButton.setBounds(boxXPosition, 410, 127, 28);
		reloadButton.setActionCommand("RELOAD");
		panel.add(reloadButton);
	}

	public String getAmount() {
		return amountBox.getText();
	}

	public void setController(ActionListener controller) {
		reloadButton.addActionListener(controller);
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

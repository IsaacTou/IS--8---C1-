package src.main.view.pages;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;
import src.main.utils.Navigate;
import src.main.view.components.*;

@SuppressWarnings("serial")
public class PrincipalView extends JFrame {

	JPanel panel;
	JPanel separationPanel;
	// RoundedPanel centerPanel;
	ImagePanel imageBackground;
	ImagePanel centerBackground;
	ConsumeButton consume;
	CheckMenuButton checkMenu;
	CheckPurseButton checkPurse;
	LogoutButton logout;
	JLabel walletLabel;

	int boxXPosition = 520;
	int ciBoxYPosition = 230;
	int userBoxYPosition = 280;
	int keyBoxYPosition = 330;
	int xPosition = 440;

	public PrincipalView() {
		setSize(850,650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("SGCU"); 
		setLocationRelativeTo(null);
		addPanel();
		addSeparationPanel();
		// addCenterPanel();
		addButtons();
		addImage();
	}

	public void isAdmin () { addAdminButtons(); }

	public void userInfo(String user, String wallet) {
		JLabel title = new JLabel("¡Bienvenido/a " + user + "!");
		title.setBounds(0, 10, 220, 30); 
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		title.setForeground(new Color(0, 51, 102)); 
		walletLabel = new JLabel("Su saldo disponible es de: " + wallet);
		walletLabel.setBounds(0, 30, 220, 30); 
		walletLabel.setHorizontalAlignment(SwingConstants.CENTER);
		walletLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
		walletLabel.setForeground(new Color(0, 51, 102));
		panel.add(title); 
		panel.add(walletLabel);
	}

	public void refreshBalance(String wallet) {
		walletLabel.setText("Su saldo disponible es de: " + wallet);
	}

	private void addPanel() {
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 220, 650); // Panel izquierdo
		this.getContentPane().add(panel);
	}

	private void addSeparationPanel() {
		separationPanel = new JPanel();
		separationPanel.setLayout(null);
		separationPanel.setBounds(0, 200, 220, 50); 
		separationPanel.setBackground(new Color(40, 55, 71 ));
		JLabel title = new JLabel("APARTADOS");
		title.setBounds(0, 10, 220, 30); 
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Sans Serif", Font.BOLD, 20));
		title.setForeground(Color.WHITE); 
		separationPanel.add(title); 
		panel.add(separationPanel);
	}

	private void addImage() {
		imageBackground = new ImagePanel("principal_background.png",850, 650, 230, 0);
		this.getContentPane().add(imageBackground);
	}

	/*
	private void addCenterPanel() {
		centerPanel = new RoundedPanel(30); 
		centerPanel.setBackground(Color.WHITE); 
		centerPanel.setLayout(null);
		centerPanel.setBounds(420, 200, 200, 250);
		centerBackground = new ImagePanel("user_icon.png",100,100,50,50);
		centerPanel.add(centerBackground);

		this.getContentPane().add(centerPanel);
	}
    */

	private void addButtons() {
		/*
		consume = new ConsumeButton();
		consume.setBounds(40, 170, 120, 40);
		consume.setFont(new Font("Arial", Font.BOLD, 14));
		centerPanel.add(consume);
		*/

		checkPurse = new CheckPurseButton(); 
		checkPurse.setBounds(40, 60, 130, 25);
		checkPurse.setFont(new Font("Arial", Font.BOLD, 18));
		panel.add(checkPurse);

		checkMenu = new CheckMenuButton();
		checkMenu.setBounds(18, 300, 190, 50);
		checkMenu.setActionCommand("CONSULTAR_MENUS"); // Asegurar este comando
		panel.add(checkMenu);

		logout = new LogoutButton();
		logout.setBounds(30, 110, 160, 50);
		panel.add(logout);
	}

	public void setController(ActionListener controller) {
		checkPurse.addActionListener(controller);
		checkMenu.addActionListener(controller);
		logout.addActionListener(controller);
	}

	private void addAdminButtons() {
		CalculateCCBButton calculateCCBButton = new CalculateCCBButton();
		calculateCCBButton.setBounds(18, 350, 190, 50);
		calculateCCBButton.addActionListener(e -> {
			this.dispose();
			Navigate.getInstance().initCCBView();
		});

		ScanMenuButton scanMenuButton = new ScanMenuButton();
		scanMenuButton.setBounds(18, 350 + 50, 190, 50);
		scanMenuButton.addActionListener(e -> {
			this.dispose();
			Navigate.getInstance().initScanMenuView();
		});
		panel.add(calculateCCBButton);
		panel.add(scanMenuButton);
	}
}

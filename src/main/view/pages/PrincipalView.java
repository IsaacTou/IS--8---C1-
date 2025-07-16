package src.main.view.pages;
    
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import src.main.view.components.*;
import java.awt.event.ActionListener;


public class PrincipalView extends JFrame {
    
	JPanel panel;
    JPanel centerPanel;
	ImagePanel imageBackground;
    ImagePanel centerBackground;
    ConsumeButton consume;
    CheckMenuButton checkMenu;

	int boxXPosition = 520;
	int ciBoxYPosition = 230;
	int userBoxYPosition = 280;
	int keyBoxYPosition = 330;
	int xPosition = 440;


	public PrincipalView() {
		setSize(850,650); // (ancho, largo)
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Cuando cierre la ventana el programa finalizara
		setTitle("SGCU"); // titulo de la ventana
		setLocationRelativeTo(null); // centra la localizacion de la pantalla 
        setLayout(null);
		addPanel();
        addCenterPanel();
        addButtons();
		addImage();

	}

	private void addPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 220, 650); // Panel izquierdo
        this.getContentPane().add(panel);
        JLabel title = new JLabel("APARTADOS:");
	    title.setBounds(0, 0, 220, 350);
	    title.setHorizontalAlignment(SwingConstants.CENTER);
	    title.setFont(new Font("Sans Serif", Font.BOLD, 20));
        panel.add(title);
    }
    
    private void addImage() {
        imageBackground = new ImagePanel("principal_background.png",630, 650, 230, 0);
        this.getContentPane().add(imageBackground);
    }

    private void addCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setBounds(450, 200, 200, 250); // Posición y tamaño del panel central

        // Imagen pequeña dentro del panel central
        centerBackground = new ImagePanel("user.png",100,100,50,50);
        centerPanel.add(centerBackground);

        this.getContentPane().add(centerPanel);
    }

    private void addButtons() {
        consume = new ConsumeButton();
        consume.setBounds(40, 160, 120, 50);
        consume.setFont(new Font("Arial", Font.BOLD, 14));
        centerPanel.add(consume);

        checkMenu = new CheckMenuButton();
        checkMenu.setBounds(18,300,190,50);
        panel.add(checkMenu);


    }

    public void addAdminButtons() {

    }

}

package src.main.view.components;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

    public ImagePanel(String imageFileName, int width, int height, int x, int y) {
        String imagePath = "src/assets/";
        setLayout(null);
        setSize(width, height);
        setBackground(new Color(4, 113, 166)); // Color de fondo (opcional)
        setBounds(x, y, width, height); // Establece el tamaño y posición del panel

        // Cargar la imagen desde la ruta especificada
        ImageIcon imageIcon = new ImageIcon(imagePath + imageFileName);
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0, 0, width, height); // Ajustar el tamaño y posición de la imagen

        // Agregar la imagen al panel
        add(imageLabel);
    }
}

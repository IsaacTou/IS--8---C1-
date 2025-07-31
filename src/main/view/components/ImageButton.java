package src.main.view.components;
import java.awt.Color;

@SuppressWarnings("serial")
public class ImageButton extends Button {
	public ImageButton() {
		super("Cargar Imágen", new Color(4, 113, 166), 127, 28); //Color azul y tamaño por defecto
		setActionCommand("IMAGEN");
		setToolTipText("Cargar Imágen"); 
	}
}

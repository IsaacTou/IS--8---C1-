package src.main.view.components;
import java.awt.Color;

@SuppressWarnings("serial")
public class ScanButton extends Button {
	public ScanButton() {
		super("Escanear", new Color(4, 113, 166), 127, 28); //Color azul y tamaño por defecto
		setActionCommand("ESCANEAR");
		setToolTipText("Escanear"); 
	}
}

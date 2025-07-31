package src.main.view.components;
import java.awt.Color;

@SuppressWarnings("serial")
public class ScanMenuButton extends Button {
	public ScanMenuButton() {
		super("Escaneo", new Color(4, 113, 166), 127, 28);
		setActionCommand("ESCANEO");
		setToolTipText("Cargar y escanear fotografías");
	}
}

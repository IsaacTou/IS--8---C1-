package src.main.view.components;
import java.awt.Color;

@SuppressWarnings("serial")
public class ReloadButton extends Button {
    public ReloadButton() {
        super("Recargar", new Color(4, 113, 166), 127, 28); //Color azul y tamaño por defecto
		setActionCommand("RECARGAR");
		setToolTipText("Recargar saldo"); 
    }
}

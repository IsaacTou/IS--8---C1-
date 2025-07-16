package src.main.view.components;
import java.awt.Color;

@SuppressWarnings("serial")
public class CheckPurseButton extends Button {
	public CheckPurseButton() {
		super("Recargar", new Color(4, 113, 166), 127, 28);
		setActionCommand("OPEN_WALLET");
		setToolTipText("Recargar saldo"); 
	}
}


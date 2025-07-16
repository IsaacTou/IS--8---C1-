package src.main.view.components;
import java.awt.Color;

@SuppressWarnings("serial")
public class CalculateCCBButton extends Button {
    public CalculateCCBButton() {
        super("Calcular CCB", new Color(4, 113, 166), 127, 28);
        setActionCommand("CALCULAR_CCB");
        setToolTipText("Calcular costo");
    }
}

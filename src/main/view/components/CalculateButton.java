package src.main.view.components;
import java.awt.Color;

@SuppressWarnings("serial")
public class CalculateButton extends Button {
    public CalculateButton() {
        super("Calcular", new Color(4, 113, 166), 150, 40); // Texto y tamaño diferente
        setActionCommand("CALCULAR"); // Command diferente
        setToolTipText("Realizar cálculo CCB");
    }
}

package src.main.view.components;
import java.awt.Color;

@SuppressWarnings("serial")
public class ConsumeButton extends Button {
    public ConsumeButton() {
        super("Consumir", new Color(4, 113, 166), 127, 28); //Color azul y tamaño por defecto
        setActionCommand("PAGAR");
        setToolTipText("Poder pagar el Menú"); 
    }
}

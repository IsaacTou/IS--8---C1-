package src.main.view.components;
import java.awt.Color;

@SuppressWarnings("serial")
public class CheckMenuButton extends Button {
    public CheckMenuButton() {
        super("Consultar Menús", new Color(4, 113, 166), 127, 28); //Color azul y tamaño por defecto
        setActionCommand("Consultar Menús");
        setToolTipText("Acceder a los menús"); 
    }
}

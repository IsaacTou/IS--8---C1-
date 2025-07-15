package src.main.view.components;
import java.awt.Color;

// Botón de Volver Atrás
public class BackButton extends Button {
    public BackButton() {
        super("Volver", new Color(166, 4, 4), 127, 28); //Color rojo y tamaño por defecto
        setActionCommand("VOLVER"); // Establece el comando de acción
        setToolTipText("Volver a la pantalla anterior");
    }

}
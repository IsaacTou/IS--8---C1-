package src.main.view.components;
import java.awt.Color;

@SuppressWarnings("serial")
public class LogoutButton extends Button {
    public LogoutButton() {
        super("Cerrar Sesión", new Color(166, 4, 4), 127, 28); //Color rojo y tamaño por defecto
        setActionCommand("LOGOUT");
        setToolTipText("Salir del sistema"); 
    }
}

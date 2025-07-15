package src.main.view.components;
import java.awt.Color;

@SuppressWarnings("serial")
public class LoginButton extends Button {
    public LoginButton() {
        super("Ingresar", new Color(4, 113, 166), 127, 28); //Color azul y tamaño por defecto
        setActionCommand("LOGUEAR");
        setToolTipText("Entrar al sistema"); 
    }
}
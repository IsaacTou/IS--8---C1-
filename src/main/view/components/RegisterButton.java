package src.main.view.components;
import java.awt.Color;

// Botón de Registro
public class RegisterButton extends Button {
    public RegisterButton() {
        super("Registrar", new Color(4, 113, 166), 127, 28); //Color azul y tamaño por defecto
    }

    @Override
    protected void configurarAccion() {
        addActionListener(e -> {
            // Lógica de registro
            // Aquí iría tu código de validación de registro
        });
    }
}
package src.main.view.components;
import java.awt.Color;

// Botón de Recargar Monedero
public class ReloadButton extends Button {
    public ReloadButton() {
        super("Recargar", new Color(4, 113, 166), 127, 28); //Color azul y tamaño por defecto
    }

    @Override
    protected void configurarAccion() {
        addActionListener(e -> {
            // Lógica de recargar el monedero
        });
    }
}

package src.main.model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import src.main.utils.FileUtils;

public class CCBData {
    private String datos;
    private LocalDate fechaUltimaCarga;
    private int semanaUltimaCarga;

    public CCBData() {
        this.fechaUltimaCarga = null;
        this.semanaUltimaCarga = -1;
    }

    public boolean puedeCargar() {
        LocalDate hoy = LocalDate.now();
        int semanaActual = hoy.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());

        if (fechaUltimaCarga == null || semanaActual != semanaUltimaCarga) {
            return true;
        }
        return false;
    }

    public void guardarDatos(String datos, String rutaArchivo) throws IOException {
        if (puedeCargar()) {
            FileUtils.guardarDatos(rutaArchivo, datos);
            this.fechaUltimaCarga = LocalDate.now();
            this.semanaUltimaCarga = fechaUltimaCarga.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
        } else {
            throw new IOException("Ya se cargaron datos en la semana actual.");
        }
    }
}
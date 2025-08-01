package src.main.model;

import java.io.IOException;
import java.time.LocalDate;
import src.main.utils.FileUtils;

public class CCBData {
    private String datos;
    private LocalDate fechaUltimaCarga;
    private String rutaArchivo;

    public CCBData(String rutaArchivo) {
        this.fechaUltimaCarga = null;
        this.rutaArchivo = rutaArchivo;
        cargarDatosAnteriores();
    }

    private void cargarDatosAnteriores() {
        try {
            this.datos = FileUtils.leerDatos(this.rutaArchivo);
        } catch (IOException e) {
            this.datos = null;
        }
    }

    public boolean puedeCargar() {
        LocalDate hoy = LocalDate.now();
        return fechaUltimaCarga == null || !fechaUltimaCarga.equals(hoy);
    }

    public void guardarDatos(String datos, String rutaArchivo) throws IOException {
        if (puedeCargar()) {
            FileUtils.guardarDatos(rutaArchivo, datos);
            this.fechaUltimaCarga = LocalDate.now();
            this.datos = datos;
        } else {
            throw new IOException("Ya se cargaron datos para el día de hoy.");
        }
    }

    public float calcularCCB(float costosFijos, float costosVariables, 
                           int numeroBandejas, float porcentajeMerma) {
        float factorMerma = porcentajeMerma / 100f;
        return ((costosFijos + costosVariables) / numeroBandejas) * (1 + factorMerma);
    }

    public String getDatos() {
        return datos;
    }

    public String getCCBByTypeUser(String userType) {

        float CCB = Float.parseFloat(datos);
        if (userType.equals("admin") || userType.equals("Empleado")) {

            float amount = (CCB * 100) / 100;
            return amount + "";

        } else if (userType.equals("Profesor")) {

            float amount = (CCB * 70) / 100;
            return amount + "";

        } else if (userType.equals("Estudiante")) {

            float amount = (CCB * 25) / 100;
            return amount + "";

        } 
        return null;
    }

    public LocalDate getFechaUltimaCarga() {
        return fechaUltimaCarga;
    }

    public Float getCCBAnterior() {
        if (datos == null || datos.isEmpty()) {
            return null;
        }
        try {
            return Float.parseFloat(datos);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
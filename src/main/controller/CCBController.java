package src.main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import src.main.utils.Navigate;
import src.main.view.pages.CCBView;
import src.main.model.CCBData;

public class CCBController implements ActionListener {

    private CCBView view;
    private CCBData model;

    public CCBController(CCBView view) {
        this.view = view;
        this.model = new CCBData("src/main/data/CCBData.txt");
        mostrarCCBAnterior();  
    }

    private void mostrarCCBAnterior() {
        Float ccbAnterior = model.getCCBAnterior();
        view.setCCBValue(ccbAnterior);
    }

    public void initialize() {
        this.view.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("GUARDAR DATOS".equals(command)) {
            try {
                //Validar y convertir los datos
                float costosFijos = validarFloatPositivo(view.getCostosFijos(), "Costos fijos");
                float costosVariables = validarFloatPositivo(view.getCostosVariables(), "Costos variables");
                int cantidadBandejas = validarEnteroPositivo(view.getNumeroBandejas(), "Número de bandejas");
                float porcentajeMerma = validarPorcentajeMerma(view.getPorcentajeMerma());

                if (model.puedeCargar()) {
                    //Aquí Calculo el CCB y luego lo guardo
                    float ccb = model.calcularCCB(costosFijos, costosVariables, cantidadBandejas, porcentajeMerma);
                    JOptionPane.showMessageDialog(view, "Nuevo CCB calculado");
                    String datos = String.valueOf(ccb);
                    model.guardarDatos(datos, "src/main/data/CCBData.txt");
                    view.setCCBValue(ccb);
                } else {
                    JOptionPane.showMessageDialog(view, "Ya se cargaron datos para el día de hoy.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Error en los datos: " + ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(view, "Error al guardar los datos: " + ex.getMessage());
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            }
        } else if ("VOLVER".equals(command)) {
            view.dispose();
            Navigate.getInstance().initPrincipal();
        }
    }

    private float validarFloatPositivo(String valor, String nombreCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + nombreCampo + " no puede estar vacío.");
        }
        
        float num = Float.parseFloat(valor);
        if (num <= 0) {
            throw new IllegalArgumentException("El campo " + nombreCampo + " debe ser un número positivo mayor a cero.");
        }
        return num;
    }

    private int validarEnteroPositivo(String valor, String nombreCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + nombreCampo + " no puede estar vacío.");
        }
        
        int num;
        try {
            num = Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El campo " + nombreCampo + " debe ser un número entero.");
        }
        
        if (num <= 0) {
            throw new IllegalArgumentException("El campo " + nombreCampo + " debe ser un entero positivo mayor a cero.");
        }
        return num;
    }

    private float validarPorcentajeMerma(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El porcentaje de merma no puede estar vacío.");
        }
        
        float num = Float.parseFloat(valor);
        if (num < 0 || num > 100) {
            throw new IllegalArgumentException("El porcentaje de merma debe estar entre 0 y 100.");
        }
        return num;
    }
}
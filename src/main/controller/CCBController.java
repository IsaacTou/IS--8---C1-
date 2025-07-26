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
        this.model = new CCBData();
    }

    public void initialize() {
        this.view.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("GUARDAR DATOS".equals(command)) {
            String costosFijos = view.getCostosFijos();
            String costosVariables = view.getCostosVariables();
            String cantidadBandejas = view.getNumeroBandejas();
            String porcentajeMerma = view.getPorcentajeMerma();

            if (costosFijos.isEmpty() || costosVariables.isEmpty() ||
                cantidadBandejas.isEmpty() || porcentajeMerma.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Por favor, rellene todos los campos.");
                return;
            }

            String datos = costosFijos + "," + costosVariables + "," + cantidadBandejas + "," + porcentajeMerma;

            try {
                if (model.puedeCargar()) {
                    model.guardarDatos(datos, "src/main/data/CCBData.txt");
                    JOptionPane.showMessageDialog(view, "Datos guardados correctamente.");
                } else {
                    JOptionPane.showMessageDialog(view, "Ya se cargaron datos en la semana actual.");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(view, "Error al guardar los datos: " + ex.getMessage());
            }
        } else if ("VOLVER".equals(command)) {
            view.dispose();
            Navigate.getInstance().initPrincipal();
        }
    }
}

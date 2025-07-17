package src.main.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.main.utils.Navigate;
import src.main.view.pages.CCBView;

public class CCBController implements ActionListener {

    private CCBView view;

    public CCBController(CCBView view) {
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if ("CALCULAR".equals(command)) {
           
            System.out.println("Costos Fijos: " + view.getCostosFijos());
System.out.println("Costos Variables: " + view.getCostosVariables());
System.out.println("Número de bandejas: " + view.getNumeroBandejas()); 
System.out.println("Merma: " + view.getPorcentajeMerma());
        } 
        else if ("VOLVER".equals(command)) {
            view.dispose();
            Navigate.getInstance().initPrincipal();
        }
    }
}

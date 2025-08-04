package src.main.controller;
import src.main.view.pages.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import src.main.model.*;

public class PurseController implements ActionListener {

	private PurseView purseView;
	private PrincipalView principalView;

	public PurseController(PurseView purseView, PrincipalView principalView) {
		this.purseView = purseView;
		this.principalView = principalView;
	}

	public void initialize() {
		this.purseView.setController((ActionListener) this);
		purseView.addTitle(
			SesionUser.getInstance().getUser().getWallet()
		);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		if ("RECARGAR".equals(command)) {
			String amount = purseView.getAmount();
			String code = purseView.getCode();

			if (code.length() == 0 || amount.length() == 0) {
				JOptionPane.showMessageDialog(purseView, "Debe rellenar todos los campos");
				return;
			}
			try {
				Float.parseFloat(amount);
			}
			catch (NumberFormatException x) {
				JOptionPane.showMessageDialog(purseView, "La cantidad a recargar debe ser numérica");
				return;
			}
			if(Float.parseFloat(amount) <= 0) {
				JOptionPane.showMessageDialog(purseView, "La cantidad a recargar debe ser mayor que 0");
				return;
			}
			try {
				Integer.parseInt(code);
			}
			catch (NumberFormatException x) {
				JOptionPane.showMessageDialog(purseView, "El código debe ser numérico");
				return;
			}

			if (code.length() != 4) {
				JOptionPane.showMessageDialog(purseView, "El código debe ser de 4 dígitos");
				return;
			}
			if (SesionUser.getInstance().setWallet(amount) == 0) {
				JOptionPane.showMessageDialog(purseView, "Se hizo la recarga de manera exitosa");
				purseView.refreshTitle(SesionUser.getInstance().getUser().getWallet());
				principalView.refreshBalance(SesionUser.getInstance().getUser().getWallet());
			}
			else {
				JOptionPane.showMessageDialog(purseView, "Hubo un error al tratar de recargar el saldo");
			}
		}
		if ("VOLVER".equals(command)) {
			purseView.dispose();
		}
	}
}

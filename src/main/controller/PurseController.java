package src.main.controller;
import src.main.view.pages.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PurseController implements ActionListener {

	private PurseView purseView;

	public PurseController(PurseView purseView) {
		this.purseView = purseView;
	}

	public void initialize() {
		this.purseView.setController((ActionListener) this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		if ("RECARGAR".equals(command)) {
			//Para futuros incrementos
			//String amount = purseView.getAmount();
		}
		if ("VOLVER".equals(command)) {
			purseView.dispose();
		}
	}
	
}

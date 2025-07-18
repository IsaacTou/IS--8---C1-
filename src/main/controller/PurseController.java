package src.main.controller;
import src.main.view.pages.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PurseController implements ActionListener {

	private PurseView purseView;

	private final int USER_TAKEN = 0;
	private final int CI_NOT_FOUND = 1;
	private final int ALREADY_REGISTER = 2;
	private final int SUCCESS_OP = 3;

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
			String amount = purseView.getAmount();
		}
		if ("VOLVER".equals(command)) {
			purseView.dispose();
		}
	}
	
}

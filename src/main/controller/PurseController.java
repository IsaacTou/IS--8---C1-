package src.main.controller;
import src.main.view.pages.*;
import src.main.model.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import src.main.utils.*;

public class PurseController implements ActionListener {

	private PurseView purseView;

	private final int USER_TAKEN = 0;
	private final int CI_NOT_FOUND = 1;
	private final int ALREADY_REGISTER = 2;
	private final int SUCCESS_OP = 3;

	public PurseController(PurseView purseView) {
		this.purseView = purseView;
		this.purseView.setController((ActionListener) this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		if ("RECARGAR".equals(command)) {
			String amount = purseView.getAmount();
		}

	}
	public void reply(int messageId) {
		switch (messageId) {
			case USER_TAKEN:
			purseView.warning("Este nombre de usuario ya se encuentra ocupado, por favor intente otro.");
			break;
			case CI_NOT_FOUND:
			purseView.warning("Usted no se encuentra en el registro UCV, por favor visite secretaria.");
			break;
			case ALREADY_REGISTER:
			purseView.warning("Usted ya se encuentra registrado en el sistema.");
			break;
			case SUCCESS_OP:
			purseView.confirm("El registro ha sido exitoso");
			break;
			default:
			break;
		}
	}

}

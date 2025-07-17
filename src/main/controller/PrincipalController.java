package src.main.controller;
import src.main.view.pages.*;
import src.main.model.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import src.main.utils.*;

public class PrincipalController implements ActionListener {

	private PrincipalView principalView;

	public PrincipalController(PrincipalView principalView) {
		this.principalView = principalView;
		this.principalView.setController((ActionListener) this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		if ("OPEN_WALLET".equals(command)) {
			Navigate.getInstance().initPurse();
		}
	}

	public void reply(int messageId) {

	}
}

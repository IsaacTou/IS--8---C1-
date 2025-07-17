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
		principalView.userInfo(SesionUser.getInstance().getUser().getUser(),SesionUser.getInstance().getUser().getWallet());
		if(SesionUser.getInstance().getUser() != null && 
			SesionUser.getInstance().getUser().getUserType().equals("admin")) {
			principalView.isAdmin();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		if ("OPEN_WALLET".equals(command)) {
			Navigate.getInstance().initPurse();
		} else if ("LOGOUT".equals(command)) {
			SesionUser.getInstance().logout();
			principalView.dispose();
			Navigate.getInstance().initWelcomeView();

		}
	}

	public void reply(int messageId) {

	}
}

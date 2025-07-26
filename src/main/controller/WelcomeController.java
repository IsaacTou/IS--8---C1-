package src.main.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.main.utils.Navigate;
import src.main.view.pages.WelcomeView;

public class WelcomeController implements ActionListener {

	private WelcomeView view;

	public WelcomeController(WelcomeView view) {
		this.view = view;
		view.setVisible(true);
	}

	public void initialize() {
		view.setController(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "GO_TO_LOGIN":
				view.dispose();
				Navigate.getInstance().initLoginView();
				break;
			case "GO_TO_REGISTER":
				view.dispose();
				Navigate.getInstance().initRegisterView();
				break;
		}
	}
}

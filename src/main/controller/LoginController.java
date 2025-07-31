package src.main.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.main.model.*;
import src.main.utils.*;
import src.main.view.pages.LoginView;

public class LoginController implements ActionListener {

	private LoginView login;
	private SCUDataManager loguerManager;

	private final int FIELD_EMPTY = -1;
	private final int USER_DOES_NOT_EXIST = 0;
	private final int WRONG_PASSWORD = 1;
	private final int SUCCESS_OP = 2;

	public LoginController(LoginView login) {
		this.login = login;
	} 

	public void initialize() {
		this.login.setController((ActionListener) this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("LOGUEAR".equals(command)) {

			loguerManager = new SCUDataManager();

			String user = login.getUser();
			String key = login.getKey();

			if (fieldIsEmpty(user, key)) {
				reply(FIELD_EMPTY);
				return;
			}

			if(loguerManager.userExist(user)) {
				if (loguerManager.correctPassword(user, key)) {
					reply(SUCCESS_OP);
					User sesionUser = new User(loguerManager.getCi(), loguerManager.getUser(), loguerManager.getUserType(), loguerManager.getWallet());
					SesionUser.getInstance().setSesionUser(sesionUser);
					login.dispose();

					Navigate.getInstance().initPrincipal();
				} else {
					reply(WRONG_PASSWORD);
				}
			} else {
				reply(USER_DOES_NOT_EXIST);
			}


		}

		else if ("VOLVER".equals(command)) {
			login.dispose();  
			Navigate.getInstance().initWelcomeView(); 
		}


	}

	public void reply(int messageId) {
		switch (messageId) {
			case FIELD_EMPTY:
			login.warning("Por favor, complete todos los campos requeridos.");
			break;
			case USER_DOES_NOT_EXIST:
			login.warning("Este usuario no existe en el sistema");
			break;
			case WRONG_PASSWORD:
			login.warning("Contraseña incorrecta");
			break;
			case SUCCESS_OP:
			//login.confirm("Inicio de sesión exitoso");
			break;
			default:
			break;
		}
	}

	public boolean fieldIsEmpty(String user, String key) {
		return user.isEmpty() || key.isEmpty();
	}
}

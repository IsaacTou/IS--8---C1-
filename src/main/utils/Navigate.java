package src.main.utils;
import src.main.controller.*;
import src.main.view.pages.*;

public class Navigate {

	private static Navigate instance;

	private Navigate() {} 

	public static Navigate getInstance() {
		if (instance == null) {
			instance = new Navigate();
		}
		return instance;
	}

	public void initRegister() {
		RegisterView register = new RegisterView();
		new RegisterController(register).initialize();
		register.setVisible(true);     
	}

	public void initLogin() {
		LoginView login = new LoginView();
		new LoginController(login).initialize();
		login.setVisible(true);
	}

	public void initPurse() {
		PurseView purse = new PurseView();
		new PurseController(purse).initialize();
		purse.setVisible(true);
	}

	public void initPrincipal() {
		PrincipalView view = new PrincipalView();
		new PrincipalController(view).initialize();
		view.setVisible(true);
	}

	public void initCCBView() {
		CCBView ccbView = new CCBView();
		new CCBController(ccbView).initialize();
		ccbView.setVisible(true);
	}

	public void initLoginView() {
		LoginView login = new LoginView();
		new LoginController(login).initialize(); 
		login.setVisible(true); 
	}

	public void initRegisterView() {
		RegisterView register = new RegisterView();
		new RegisterController(register).initialize();
		register.setVisible(true);  
	}

	public void initWelcomeView() {
		WelcomeView welcomeView = new WelcomeView();
		new WelcomeController(welcomeView).initialize();
		welcomeView.setVisible(true);       
	}
	public void initMenuView() {
		MenuView menuView = new MenuView();
		new MenuViewController(menuView).initialize();;
		menuView.setVisible(true);
	}
}

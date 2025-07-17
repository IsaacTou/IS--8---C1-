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
        new RegisterController(register);
        register.setVisible(true);     
    }

    public void initLogin() {
        LoginView login = new LoginView();
        new LoginController(login);
        login.setVisible(true);
    }
    
    public void initPurse() {
        PurseView purse = new PurseView();
        new PurseController(purse);
        purse.setVisible(true);
    }

    public void initPrincipal() {
        PrincipalView view = new PrincipalView();
        new PrincipalController(view);
        view.setVisible(true);
    }

    public void initCCBView() {
        CCBView ccbView = new CCBView();
        new CCBController(ccbView);
        ccbView.setVisible(true);
    }

    public void initLoginView() {
        LoginView login = new LoginView();
        new LoginController(login);  
        login.setVisible(true); 
    }
    
    public void initRegisterView() {
        RegisterView register = new RegisterView();
        new RegisterController(register);  
        register.setVisible(true);  
    }
    
    public void initWelcomeView() {
        WelcomeView welcomeView = new WelcomeView();
        new WelcomeController(welcomeView);
        welcomeView.setVisible(true);       
    }
    
}

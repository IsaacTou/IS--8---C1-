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
        new RegisterController(register, this);
        register.setVisible(true);     
    }

    public void initLogin() {
        LoginView login = new LoginView();
        new LoginController(login, this);
        login.setVisible(true);
    }
    public void initPurse() {
        PurseView purse = new PurseView();
        new PurseController(purse, this);
        purse.setVisible(true);
    }

    
}

package src.main.utils;
import src.main.controller.*;
import src.main.view.*;

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
        Register register = new Register();
        new RegisterController(register, this);
        register.setVisible(true);     
    }

    public void initLogin() {
        Login login = new Login();
        new LoginController(login, this);
        login.setVisible(true);
    }
    
}

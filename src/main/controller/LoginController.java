package src.main.controller;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import src.main.view.pages.LoginView;
import src.main.utils.*;
import src.main.model.SCUDataManager;

public class LoginController implements ActionListener{

    private LoginView login;
    private Navigate navigate;
    private SCUDataManager loguerManager;

    private final int CAMP_EMPTY = -1;
    private final int USER_DOES_NOT_EXIST = 0;
    private final int WRONG_PASSWORD = 1;
    private final int SUCCESS_OP = 2;

    public LoginController(LoginView login, Navigate navigate) {
        this.login = login;
        this.navigate = navigate;
        this.login.setController((ActionListener) this);
    } 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("LOGUEAR".equals(command)) {

            loguerManager = new SCUDataManager();

            String user = login.getUser();
            String key = login.getKey();

            if (campIsEmpty(user, key)) {
                reply(CAMP_EMPTY);
                return;
            }

            if(loguerManager.userExist(user)) {
                if (loguerManager.correctPassword(user, key)) {
                    reply(SUCCESS_OP);
                    // SECCION QUE ACTIVE X CODIGO DE INICIO DE SESION (EN VEREMOS)
                } else {
                    reply(WRONG_PASSWORD);
                }
            } else {
                reply(USER_DOES_NOT_EXIST);
            }


        }

    }

    public void reply(int messageId) {
        switch (messageId) {
            case CAMP_EMPTY:
                login.warning("Por favor, complete todos los campos requeridos.");
                break;
            case USER_DOES_NOT_EXIST:
                login.warning("Este usuario no existe en el sistema");
                break;
            case WRONG_PASSWORD:
                login.warning("Contraseña incorrecta");
                break;
            case SUCCESS_OP:
                login.confirm("Inicio de sesión exitoso");
                break;
            default:
                break;
        }
    }

    public boolean campIsEmpty(String user, String key) {
        return user.isEmpty() || key.isEmpty();
    }
}

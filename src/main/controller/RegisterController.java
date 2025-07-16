package src.main.controller;
import src.main.view.pages.RegisterView;
import src.main.model.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import src.main.utils.*;

public class RegisterController implements ActionListener {

    
    private RegisterView register;
    private UCVDataReader ucvLector;
    private SCUDataManager scuManager;

    // INFORMACION (EVITANDO USAR NUMEROS MAGICOS)
    private final int CAMP_EMPTY = -1;
    private final int USER_TAKEN = 0;
    private final int CI_NOT_FOUND = 1;
    private final int ALREADY_REGISTER = 2;
    private final int SUCCESS_OP = 3;


    public RegisterController(RegisterView register) {
        this.register = register;
        this.register.setController((ActionListener) this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();
        
        if ("REGISTRAR".equals(command)) {

            String ci = register.getCi();
            String user = register.getUser();
            String key = register.getKey();

            if (campIsEmpty(ci, user, key)) {
                reply(CAMP_EMPTY);
                return; 
            }
        
            ucvLector = new UCVDataReader();
            scuManager = new SCUDataManager();

            boolean isCiFind = ucvLector.findCi(ci); 
            String userType = ucvLector.getUserType();
            
            if (!isCiFind) {

                reply(CI_NOT_FOUND);            

            } else {

                int reply = scuManager.invalidOperation(ci, user);
                if(reply == SUCCESS_OP) {  
                    scuManager.createAccount(ci,user,userType,key);
                    reply(SUCCESS_OP);
                    register.dispose();
                    Navigate.getInstance().initLogin();
                           
                } else {
                    reply(reply);
                }
            }

        }

    }

    public void reply(int messageId) {
        switch (messageId) {
            case USER_TAKEN:
                register.warning("Este nombre de usuario ya se encuentra ocupado, por favor intente otro.");
                break;
            case CI_NOT_FOUND:
                register.warning("Usted no se encuentra en el registro UCV, por favor visite secretaria.");
                break;
            case ALREADY_REGISTER:
                register.warning("Usted ya se encuentra registrado en el sistema.");
                break;
            case CAMP_EMPTY:
                register.warning("Por favor, complete todos los campos requeridos.");
                break;
            case SUCCESS_OP:
                register.confirm("El registro ha sido exitoso");
                break;
            default:
                break;
        }
    }

    public boolean campIsEmpty(String ci, String user, String key) {
        return ci.isEmpty() || user.isEmpty() || key.isEmpty();
    }

}

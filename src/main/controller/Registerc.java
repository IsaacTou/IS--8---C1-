package src.main.controller;
import src.main.view.*;
import src.main.model.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registerc implements ActionListener {

    private Register register;
    private ucvDataReader ucvLector;
    private scuDataManagment scuManager;
    private final int USER_TAKEN = 0;
    private final int CI_NOT_FOUND = 1;
    private final int ALREADY_REGISTER = 2;
    private final int SUCCESS_OP = 3;


    public Registerc() {
        register = new Register();
        register.setVisible(true);
        this.register.setController((ActionListener) this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();
        
        if (command == "REGISTRO") {

            String cI = register.getCi();
            String user = register.getUser();
            String key = register.getKey();
            ucvLector = new ucvDataReader();
            scuManager = new scuDataManagment();

            boolean isCiFind = ucvLector.findCi(cI); 
            String userType = ucvLector.getuserType();
            
            if (!isCiFind) {

                reply(CI_NOT_FOUND);            
                
            } else {

                int reply = scuManager.invalidOperation(cI, user);
                if(reply == SUCCESS_OP) {
                    scuManager.createAccount(cI,user,userType,key);
                    reply(SUCCESS_OP);
                } else {
                    reply(reply);
                }
            }

        }

    }

    public void reply(int messageId) {
        switch (messageId) {
            case USER_TAKEN:
                register.warning("Este usuario ya se encuentra tomado, intente otro username.");
                break;
            case CI_NOT_FOUND:
                register.warning("Usted no se encuentra en el registro UCV, por favor visite secretaria.");
                break;
            case ALREADY_REGISTER:
                register.warning("Usted ya se encuentra registrado en el sistema.");
                break;
            case SUCCESS_OP:
                register.confirm("El registro ha sido exitoso");
                break;
            default:
                break;
        }
    }

}

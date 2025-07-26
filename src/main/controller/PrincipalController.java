package src.main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.main.model.*;
import src.main.utils.*;
import src.main.view.pages.*;

public class PrincipalController implements ActionListener {
	

    private PrincipalView principalView;

    public PrincipalController(PrincipalView principalView) {
        this.principalView = principalView;
    }
    
    public void initialize() {
        this.principalView.setController((ActionListener) this);
        principalView.userInfo(
            SesionUser.getInstance().getUser().getUser(),
            SesionUser.getInstance().getUser().getWallet()
        );
        
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
        } else if ("CONSULTAR_MENUS".equals(command)) {
            boolean isAdmin = SesionUser.getInstance().getUser().getUserType().equals("admin");
            MenuView menuView = new MenuView(isAdmin);
            menuView.setVisible(true);
        }
    }
}
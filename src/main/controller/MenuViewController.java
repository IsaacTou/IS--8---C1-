package src.main.controller;
import src.main.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import src.main.utils.*;
import src.main.view.pages.MenuView;
import src.main.view.pages.EditMenuDialog;

public class MenuViewController implements ActionListener {

	private MenuView menuView;
    private EditMenuDialog editMenuDialog;
    private String userType;
    private boolean adminView;

	public MenuViewController(MenuView menuView) {

		this.menuView = menuView;
        this.adminView = SesionUser.getInstance().getUser().getUserType().equals("admin");
        userType = SesionUser.getInstance().getUser().getUserType();
        MenuData.getInstance().loadMenus(userType);

        //Cargar los MENU en la vista con los datos del modelo

        for (Map.Entry<String, Menu> entry : MenuData.getInstance().getMenusData().entrySet()) {
            Menu data = entry.getValue();
            menuView.addMenuTab(
            entry.getKey(), data.getItems(), data.getTimeRange(), data.getDataPrice(), data.getDataImageName(), adminView);
        }

	}

	public void initialize() {
		this.menuView.setController((ActionListener) this);
	}

	private String editingMenuTitle = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.startsWith("Editar")) {

            String menuName = command.substring("Editar".length());
            Menu data = MenuData.getInstance().getMenusData().get(menuName);

            if (data != null) {

                editingMenuTitle = menuName;
                editMenuDialog = new EditMenuDialog(menuName, data.getItems(), data.getTimeRange(), 
                data.getDataPrice(), data.getDataImageName());
                editMenuDialog.setController(this);
                editMenuDialog.setVisible(true);

            }

        } else if (command.equals("SAVE")) {

            if (editingMenuTitle != null && editMenuDialog != null) {
                String newTitle = editMenuDialog.getTittle();
                String newTime = editMenuDialog.getTimeField();
                String[] newItems = editMenuDialog.getItems();
                String newImageName = editMenuDialog.getImageName();

                Menu oldMenu = MenuData.getInstance().getMenusData().get(editingMenuTitle);
                String oldPrice = oldMenu != null ? oldMenu.getDataPrice() : "";


                if (!newTitle.equals(editingMenuTitle)) {
                    MenuData.getInstance().getMenusData().remove(editingMenuTitle);
                }
                MenuData.getInstance().getMenusData().put(newTitle, 
                new Menu(newItems, newTime, oldPrice, newImageName));

                menuView.tabbedPane.removeAll();

                menuView.editButtons.clear(); // Limpia la lista de botones

                for (Map.Entry<String, Menu> entry : MenuData.getInstance().getMenusData().entrySet()) {

                    Menu data = entry.getValue();
                    menuView.addMenuTab(entry.getKey(), data.getItems(), data.getTimeRange(), 
                    data.getDataPrice(), data.getDataImageName(), adminView);
                    
                }

                menuView.setController(this); 
                MenuData.getInstance().saveMenusToFile();
                editMenuDialog.reply();
                editMenuDialog.setVisible(false);
                editMenuDialog.dispose();
                editingMenuTitle = null;
            }   

        } else if (command.equals("VOLVER")) {
            menuView.dispose();
            Navigate.getInstance().initPrincipal();
        }
    }
}

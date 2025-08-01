package src.main.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.main.model.*;
import src.main.utils.*;
import src.main.view.pages.ScanMenuView;
import java.io.*;

public class ScanMenuController implements ActionListener {

	private CCBData ccbData;
	private ScanMenuView scanMenuView;
	private UCVDataReader ucvLector;
	private SCUDataManager scuManager;

	public ScanMenuController(ScanMenuView scanMenuView) {
		this.scanMenuView = scanMenuView;
	}

	public void initialize() {
		this.scanMenuView.setController((ActionListener) this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		if ("ESCANEAR".equals(command)) { // hacer el escaneo		

			File bdImage;
			File uploadImage;
			String ci = scanMenuView.getCi();
			String uploadImageDirection = scanMenuView.getImage();
			String userType;
			String amount;
			boolean userExist;
			boolean isEqual;
			boolean succesDiscount;

			ccbData = new CCBData("src/main/data/CCBData.txt");
			scuManager = new SCUDataManager();
			ucvLector = new UCVDataReader();

			if (uploadImageDirection == null) {
				scanMenuView.warning("Por favor, rellene todos los campos.");
				return;
			}

			uploadImage = new File(uploadImageDirection);

			if (ci.isEmpty()) {
				scanMenuView.warning("Por favor, rellene todos los campos.");
				scanMenuView.clearImage();
				uploadImage.delete();
				return;
			}

			userExist = scuManager.userExist(ci);
			
			if (!userExist) {
				scanMenuView.warning("El usuario no se ha encontrado");
				scanMenuView.clearImage();
				uploadImage.delete();
				return;
			}

			bdImage = ucvLector.findBdImage(ci);

			if(bdImage == null) {
				scanMenuView.warning("El usuario no tiene una imagen asociada en la base de datos.");
				scanMenuView.clearImage();
				uploadImage.delete();
			}

			isEqual = ScannerData.isEqual(bdImage, uploadImage);

			if (!isEqual) {
				scanMenuView.warning("El escaneo ha fallado, no coincide con la información de secretaria.");
				scanMenuView.clearImage();
				uploadImage.delete();
				return;
			}

			ucvLector.findCi(ci);
			userType = ucvLector.getUserType();

			amount = ccbData.getCCBByTypeUser(userType);

			succesDiscount = scuManager.discount(amount, ci);

			if (!succesDiscount) {
				scanMenuView.warning("El usuario no posee saldo suficiente en su cuenta");
				scanMenuView.clearImage();
				uploadImage.delete();
				return;
			}

			scanMenuView.confirm("El escaneo ha sido satisfactorio, se ha descontado " + amount + " bs al usuario.");

			uploadImage.delete(); // Se borra para evitar que queden archivos basuras en assets
			scanMenuView.clearImage();
		}
		else if ("IMAGEN".equals(command)) {
			scanMenuView.openFileSearch();
		}
		else if ("VOLVER".equals(command)) {
			scanMenuView.dispose(); 
			Navigate.getInstance().initPrincipal();  
		}
	}
}

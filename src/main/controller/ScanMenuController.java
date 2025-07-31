package src.main.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.main.model.*;
import src.main.utils.*;
import src.main.view.pages.ScanMenuView;
import java.io.File;

public class ScanMenuController implements ActionListener {

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
			boolean userExist;
			boolean isEqual;

			scuManager = new SCUDataManager();
			ucvLector = new UCVDataReader();

			uploadImage = new File(uploadImageDirection);

			if(ci.isEmpty() || uploadImageDirection == null) {
				scanMenuView.warning("Por favor, rellene todos los campos.");
				uploadImage.delete();
				return;
			}

			userExist = scuManager.userExist(ci);
			
			if(!userExist) {
				scanMenuView.warning("El usuario no se ha encontrado");
				uploadImage.delete();
				return;
			}

			bdImage = ucvLector.findBdImage(ci);

			isEqual = ScannerData.isEqual(bdImage, uploadImage);

			if(!isEqual) {
				scanMenuView.warning("El escaneo ha fallado, no coincide con la información de secretaria.");
				uploadImage.delete();
				return;
			}

			if(isEqual) scanMenuView.confirm("El escaneo ha sido satisfactorio, el usuario puede pasar a la cola de bandeja.");

			uploadImage.delete(); // Se borra para evitar que queden archivos basuras en assets
			
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

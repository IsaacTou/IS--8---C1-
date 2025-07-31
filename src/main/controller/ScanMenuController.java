package src.main.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.main.model.*;
import src.main.utils.*;
import src.main.view.pages.ScanMenuView;
import javax.swing.*;
import src.main.view.components.*;

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

		if ("ESCANEO".equals(command)) { // hacer el escaneo

		}
		else if ("IMAGEN".equals(command)) {
			/* Dios no murió en la cruz para salvarnos, se estaba salvando a sí mismo
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(this);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				try {
					Path destination = Paths.get("src/assets/" + selectedFile.getName());
					Files.copy(selectedFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
					newImageName[0] = selectedFile.getName();
					currentImageLabel.setText("Imagen actual: " + newImageName[0]);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(this, "Error al cambiar la imagen: " + ex.getMessage());
				}
			}
			*/
		}
		else if ("VOLVER".equals(command)) {
			scanMenuView.dispose(); 
			Navigate.getInstance().initPrincipal();  
		}
	}
}

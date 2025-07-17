package src.main;
import src.main.utils.*;


public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Navigate.getInstance().initWelcomeView(); 
        });
    }
}
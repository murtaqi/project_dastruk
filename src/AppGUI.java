import gui.LoginGUI;
import model.DataStorage;
import model.HashAccount;

import javax.swing.*;

/**
 * GUI Application Launcher
 * Modern desktop application for financial management
 */
public class AppGUI {
    
    public static void main(String[] args) {
        // Set Nimbus Look and Feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize on EDT
        SwingUtilities.invokeLater(() -> {
            // Load data from storage
            HashAccount hashAccount = new HashAccount(16);
            DataStorage.loadAllAccounts(hashAccount);

            // Open Login GUI
            LoginGUI loginGUI = new LoginGUI(hashAccount);
            loginGUI.setVisible(true);
        });
    }
}

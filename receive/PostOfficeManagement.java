package receive;

import javax.swing.*;

public class PostOfficeManagement {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PostOfficeUI postOfficeUI = new PostOfficeUI();
            postOfficeUI.showReceivedPackages();
        });
    }
}

import Controller.GameController;
import Controller.MainMenuController;
import Controller.MapSizeChooserController;
import View.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainWindow = new MainFrame();

            new MapSizeChooserController(mainWindow);
            new MainMenuController(mainWindow);


            mainWindow.pack();
            mainWindow.setLocationRelativeTo(null);
            mainWindow.setVisible(true);
        });
    }
}

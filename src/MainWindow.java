import Controller.MainMenuController;
import View.MainMenuView;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private CardLayout cardLayout;


    public MainWindow() {
        setTitle("Pac-Man");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);


        MainMenu mainMenu = new MainMenu(cardLayout, mainPanel);


        JPanel sizeChooserPanel = new JPanel();
        sizeChooserPanel.add(new JLabel("Size Chooser Screen"));

        JPanel scoresPanel = new JPanel();
        scoresPanel.add(new JLabel("High Scores Screen"));


        mainPanel.add(mainMenu, "MainMenu");
        mainPanel.add(sizeChooserPanel, "Size chooser");
        mainPanel.add(scoresPanel, "Scores");

        add(mainPanel);
        cardLayout.show(mainPanel, "MainMenu");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}
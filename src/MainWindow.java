
import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private CardLayout cardLayout;


    public MainWindow() {
        int windowWidth = 800;
        int windowHeight = 800;


        setTitle("Pac-Man");
        setSize(windowWidth, windowHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);


        MainMenu mainMenu = new MainMenu(cardLayout, mainPanel);


        MapSizeChooser sizeChooserPanel = new MapSizeChooser(cardLayout, mainPanel, windowWidth, windowHeight);

        JPanel scoresPanel = new JPanel();
        scoresPanel.add(new JLabel("High Scores Screen"));


        mainPanel.add(mainMenu, "MainMenu");
        mainPanel.add(sizeChooserPanel, "Size chooser");
        mainPanel.add(scoresPanel, "Scores");

        add(mainPanel);
        cardLayout.show(mainPanel, "MainMenu");
    }

    public void showGamePanel(int rows, int cols, int tileSize) {
        GamePanel gamePanel = new GamePanel(rows, cols, tileSize);
        getContentPane().removeAll();
        add(gamePanel);
        pack(); // Resizes window to fit the GamePanel
        gamePanel.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}
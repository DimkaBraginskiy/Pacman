package View;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;


    public MainFrame() {
        int windowWidth = 800;
        int windowHeight = 800;


        setTitle("Pac-Man");
        setSize(windowWidth, windowHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        add(cardPanel);
    }

    public void addPanel(String name, JPanel panel){
        cardPanel.add(panel, name);
    }

    public void showPanel(String name){
        cardLayout.show(cardPanel, name);
    }

    public void showGamePanel(int rows, int cols, int tileSize) {
        GamePanel gamePanel = new GamePanel(rows, cols, tileSize);
        addPanel("GamePanel", gamePanel);
        showPanel("GamePanel");
    }

    public JPanel getPanel(){
        return cardPanel;
    }
}
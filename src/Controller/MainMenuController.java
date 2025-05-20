package Controller;

import javax.swing.*;
import java.awt.*;

public class MainMenuController {
    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    public MainMenuController(CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
    }

    public void startNewGame() {
        cardLayout.show(cardPanel, "Size chooser");
        cardPanel.getComponent(1).requestFocusInWindow();
    }

    public void showScores() {
        cardLayout.show(cardPanel, "Scores");
    }

    public void exitGame() {
        System.exit(0);
    }
}

package Controller;

import View.HighScoresPanel;
import View.MainFrame;

public class HighScoresController {
    public HighScoresController(MainFrame mainFrame) {
        HighScoresPanel panel = new HighScoresPanel();
        panel.setScores(mainFrame.getHighScoreManager().getHighScores());

        panel.getBackButton().addActionListener(e -> mainFrame.showPanel("MainMenu"));

        mainFrame.addPanel("HighScores", panel);
        mainFrame.showPanel("HighScores");
    }
}

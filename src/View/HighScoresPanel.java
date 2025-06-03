package View;

import Model.HighScore;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class HighScoresPanel extends JPanel {
    private final JTextArea scoreArea;
    private final JButton backButton;

    public HighScoresPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JLabel title = new JLabel("High Scores", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.YELLOW);
        add(title, BorderLayout.NORTH);

        scoreArea = new JTextArea();
        scoreArea.setEditable(false);
        scoreArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        scoreArea.setBackground(Color.BLACK);
        scoreArea.setForeground(Color.WHITE);

        add(new JScrollPane(scoreArea), BorderLayout.CENTER);

        backButton = new JButton("Back");
        add(backButton, BorderLayout.SOUTH);
    }

    public void setScores(List<HighScore> scores) {
        StringBuilder sb = new StringBuilder();
        int rank = 1;
        for (HighScore score : scores) {
            sb.append(String.format("%2d. %-15s %5d\n", rank++, score.getPlayerName(), score.getScore()));
        }
        scoreArea.setText(sb.toString());
    }

    public JButton getBackButton() {
        return backButton;
    }
}

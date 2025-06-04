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
            scoreArea.setLineWrap(false);
            scoreArea.setWrapStyleWord(false);

            JScrollPane scrollPane = new JScrollPane(scoreArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            add(scrollPane, BorderLayout.CENTER);

            backButton = new JButton("Back");
            backButton.setFocusPainted(false);
            backButton.setFont(new Font("Arial", Font.BOLD, 16));
            backButton.setBackground(Color.DARK_GRAY);
            backButton.setForeground(Color.WHITE);
            backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            bottomPanel.setBackground(Color.BLACK);
            bottomPanel.add(backButton);
            add(bottomPanel, BorderLayout.SOUTH);
        }

        public void setScores(List<HighScore> scores) {
            StringBuilder sb = new StringBuilder();
            int rank = 1;
            for (HighScore score : scores) {
                sb.append(String.format("%2d. %-15s %5d\n", rank++, score.getPlayerName(), score.getScore()));
            }
            scoreArea.setText(sb.toString());
            scoreArea.setCaretPosition(0); // scroll to top
        }

        public JButton getBackButton() {
            return backButton;
        }
    }

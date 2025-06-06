package View;

import Model.HighScore;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HighScoresPanel extends JPanel {
    private final JList<HighScore> scoreList;
    private final DefaultListModel<HighScore> listModel;
    private final JButton backButton;

    public HighScoresPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JLabel title = new JLabel("High Scores", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.YELLOW);
        add(title, BorderLayout.NORTH);

        // JList setup
        listModel = new DefaultListModel<>();
        scoreList = new JList<>(listModel);
        scoreList.setCellRenderer(new HighScoreRenderer());
        scoreList.setBackground(Color.BLACK);
        scoreList.setForeground(Color.WHITE);
        scoreList.setSelectionBackground(Color.DARK_GRAY);
        scoreList.setSelectionForeground(Color.YELLOW);
        scoreList.setFont(new Font("Monospaced", Font.PLAIN, 18));

        JScrollPane scrollPane = new JScrollPane(scoreList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(0,0));
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
        listModel.clear();
        int rank = 1;
        for (HighScore score : scores) {
            listModel.addElement(new HighScoreWithRank(score, rank++));
        }
        scoreList.ensureIndexIsVisible(0); // scroll to top
    }

    public JButton getBackButton() {
        return backButton;
    }

    // Wrapper class to keep rank
    private static class HighScoreWithRank extends HighScore {
        private final int rank;
        public HighScoreWithRank(HighScore base, int rank) {
            super(base.getPlayerName(), base.getScore());
            this.rank = rank;
        }
        public int getRank() {
            return rank;
        }
    }

    // Custom renderer
    private static class HighScoreRenderer extends JLabel implements ListCellRenderer<HighScore> {

        public HighScoreRenderer() {
            setOpaque(true);

            setFont(new Font("Arial", Font.PLAIN, 18));

        }

        @Override
        public Component getListCellRendererComponent(JList<? extends HighScore> list, HighScore value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            int rank = (value instanceof HighScoreWithRank r) ? r.getRank() : index + 1;
            setText(String.format("%2d. %-15s %5d", rank, value.getPlayerName(), value.getScore()));

            setBackground(isSelected ? Color.DARK_GRAY : Color.BLACK);
            setForeground(isSelected ? Color.YELLOW : Color.WHITE);

            return this;
        }
    }
}

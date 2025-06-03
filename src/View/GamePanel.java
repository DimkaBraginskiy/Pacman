package View;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private MapRenderer mapRenderer;
    private PacManView pacManView;
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private JLabel lifeLabel;

    public GamePanel(int rows, int cols, int tileSize, int[][] map, PacManView pacManView, List<GhostView> ghostViews) {
        setLayout(null);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
        setFocusable(true);

        this.pacManView = pacManView;
        //this.ghostView = ghostView;
        add(pacManView);
        //add(ghostView);

        for (GhostView ghostView : ghostViews) {
            add(ghostView);
        }

        // Create map renderer
        mapRenderer = new MapRenderer(map, rows, cols, tileSize);
        mapRenderer.setBounds(0, 0, cols * tileSize, rows * tileSize);
        add(mapRenderer);

        //Score label:
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(10, rows * tileSize + 5, 100, 30);
        add(scoreLabel);

        //Time label:
        timeLabel = new JLabel("Time: 0");
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setBounds(150, rows * tileSize + 5, 100, 30 );
        add(timeLabel);

        lifeLabel = new JLabel("Lives: 3");
        lifeLabel.setForeground(Color.WHITE);
        lifeLabel.setBounds(10, rows * tileSize + 20, 100, 30);
        add(lifeLabel);
    }

    public void updateScore(int score){
        SwingUtilities.invokeLater(() -> scoreLabel.setText("Score: " + score));
    }

    public void updateTime(int timeInSeconds){
        SwingUtilities.invokeLater(() -> timeLabel.setText("Time: " + timeInSeconds));
    }

    public void updateLifes(int lives){ SwingUtilities.invokeLater(() -> lifeLabel.setText("Lives: " + lives));}

    public void attachKeyListener(KeyAdapter adapter){
        addKeyListener(adapter);
    }
}
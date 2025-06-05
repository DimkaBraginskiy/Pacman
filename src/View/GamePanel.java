package View;

import Controller.PacManController;
import Model.GhostModel;
import Model.MapModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private MapRenderer mapRenderer;
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private JLabel lifeLabel;

    public GamePanel(int rows, int cols, int tileSize, MapModel mapModel, PacManController pacManController, List<GhostModel> ghostModels) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setFocusable(true);


        // Create map renderer
        mapRenderer = new MapRenderer(mapModel, rows, cols, tileSize, pacManController, ghostModels);
        mapRenderer.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
        add(mapRenderer, BorderLayout.CENTER);


        JPanel hudPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 5));
        hudPanel.setBackground(Color.BLACK);

        //Score label:
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setForeground(Color.WHITE);
        //Time label:
        timeLabel = new JLabel("Time: 0");
        timeLabel.setForeground(Color.WHITE);
        //Life label:
        lifeLabel = new JLabel("Lives: 3");
        lifeLabel.setForeground(Color.WHITE);
        hudPanel.add(lifeLabel);

        hudPanel.add(scoreLabel);
        hudPanel.add(timeLabel);
        add(hudPanel, BorderLayout.SOUTH);
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

    public MapRenderer getMapRenderer() {
        return mapRenderer;
    }
}
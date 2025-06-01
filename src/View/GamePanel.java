package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;

public class GamePanel extends JPanel {
    private MapRenderer mapRenderer;
    private PacManView pacManView;

    public GamePanel(int rows, int cols, int tileSize, int[][] map, PacManView pacManView) {
        setLayout(null);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
        setFocusable(true);

        this.pacManView = pacManView;
        add(pacManView);

        // Create map renderer
        mapRenderer = new MapRenderer(map, rows, cols, tileSize);
        mapRenderer.setBounds(0, 0, cols * tileSize, rows * tileSize);
        add(mapRenderer);
    }

    public void updateDot(int x, int y) {
        mapRenderer.getModel().setValueAt(-1, y, x); // -1 represents empty space
    }

    public void attachKeyListener(KeyAdapter adapter){
        addKeyListener(adapter);
    }
}
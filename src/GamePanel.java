import newMapApproach.MapModel;
import newMapApproach.NewMapRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private NewMapRenderer mapRenderer;
    private PacMan pacMan;
    private int tileSize;

    public GamePanel(int rows, int cols, int tileSize) {
        this.tileSize = tileSize;
        setLayout(null);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));

        // Initialize map
        MapModel mapModel = new MapModel(rows, cols);
        int[][] map = mapModel.getMap();

        // Create map renderer
        mapRenderer = new NewMapRenderer(map, rows, cols, tileSize);
        mapRenderer.setBounds(0, 0, cols * tileSize, rows * tileSize);
        add(mapRenderer);

        // Create Pac-Man (starting at position 1,1)
        pacMan = new PacMan(this, map, tileSize, 1, 1);
        add(pacMan);

        // Set focus and key listener
        setFocusable(true);
        setupKeyListener();
    }

    private void setupKeyListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        pacMan.setDirection(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        pacMan.setDirection(Direction.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        pacMan.setDirection(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        pacMan.setDirection(Direction.RIGHT);
                        break;
                }
            }
        });
    }

    public void updateDot(int x, int y) {
        // Update the map model and refresh the renderer
        mapRenderer.getModel().setValueAt(-1, y, x); // -1 represents empty space
    }
}
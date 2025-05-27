import newMapApproach.MapModel;
import newMapApproach.NewMapRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private NewMapRenderer mapRenderer;
    private PacManView pacManView;
    private PacManController pacManController;
    private int tileSize;

    public GamePanel(int rows, int cols, int tileSize) {
        this.tileSize = tileSize;
        setLayout(null);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));

        // Initialize map
        MapModel mapModel = new MapModel(rows, cols);
        int[][] map = mapModel.getMap();

        PacManModel pacManModel = new PacManModel(1,1,tileSize,map);
        pacManView = new PacManView(tileSize);
        pacManController = new PacManController(pacManModel, pacManView);

        pacManView.setLocation(pacManModel.getPixelX(), pacManModel.getPixelY());
        add(pacManView);


        // Create map renderer
        mapRenderer = new NewMapRenderer(map, rows, cols, tileSize);
        mapRenderer.setBounds(0, 0, cols * tileSize, rows * tileSize);
        add(mapRenderer);




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
                        pacManController.setDirection(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        pacManController.setDirection(Direction.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        pacManController.setDirection(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        pacManController.setDirection(Direction.RIGHT);
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
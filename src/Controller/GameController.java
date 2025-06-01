package Controller;

import Model.Direction;
import Model.PacManModel;
import View.GamePanel;
import View.MainFrame;
import View.PacManView;
import Model.MapModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController {
    private final GamePanel gamePanel;
    private final PacManModel pacManModel;
    private final PacManView pacManView;
    private final PacManController pacManController;


    public GameController(MainFrame mainFrame, int rows, int cols, int tileSize) {
        MapModel mapModel = new MapModel(rows, cols);
        int[][] map = mapModel.getMap();

        pacManModel = new PacManModel(1, 1, tileSize, map);
        pacManView = new PacManView(tileSize);
        pacManView.setLocation(pacManModel.getPixelX(), pacManModel.getPixelY());

        gamePanel = new GamePanel(rows, cols, tileSize, map, pacManView);

        pacManController = new PacManController(pacManModel, pacManView);

        gamePanel.attachKeyListener(createKeyListener());

        mainFrame.addPanel("GamePanel", gamePanel);
        mainFrame.showPanel("GamePanel");

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);

        gamePanel.requestFocusInWindow();
    }

    private KeyAdapter createKeyListener() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Direction dir = switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> Direction.UP;
                    case KeyEvent.VK_DOWN -> Direction.DOWN;
                    case KeyEvent.VK_LEFT -> Direction.LEFT;
                    case KeyEvent.VK_RIGHT -> Direction.RIGHT;
                    default -> null;
                };
                if (dir != null) {
                    pacManController.setDirection(dir);
                }
            }
        };
    }
}

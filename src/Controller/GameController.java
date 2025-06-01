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
        int[][] map = mapModel.getMap(); // getting map from mapmodel

        pacManModel = new PacManModel(1, 1, tileSize, mapModel);
        pacManView = new PacManView(tileSize);
        pacManView.setLocation(pacManModel.getPixelX(), pacManModel.getPixelY());

        gamePanel = new GamePanel(rows, cols, tileSize, map, pacManView);

        pacManController = new PacManController(pacManModel, pacManView);

        gamePanel.attachKeyListener(pacManController.getKeyAdapter());

        mainFrame.addPanel("GamePanel", gamePanel);
        mainFrame.showPanel("GamePanel");

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);

        gamePanel.requestFocusInWindow();
    }
}

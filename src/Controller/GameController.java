package Controller;

import Model.Direction;
import Model.GhostModel;
import Model.PacManModel;
import View.GamePanel;
import View.GhostView;
import View.MainFrame;
import View.PacManView;
import Model.MapModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private final GamePanel gamePanel;
    private final PacManModel pacManModel;
    private final PacManView pacManView;
    private final PacManController pacManController;

    private final List<GhostModel> ghostModels = new ArrayList<>();
    private final List<GhostView> ghostViews = new ArrayList<>();
    private final List<GhostController> ghostControllers = new ArrayList<>();

    private volatile int score = 0;
    private volatile int timeInSeconds = 0;


    public GameController(MainFrame mainFrame, int rows, int cols, int tileSize) {
        MapModel mapModel = new MapModel(rows, cols);
        int[][] map = mapModel.getMap(); // getting map from mapmodel

        pacManModel = new PacManModel(1, 1, tileSize, mapModel, this);
        pacManView = new PacManView(tileSize);
        pacManView.setLocation(pacManModel.getPixelX(), pacManModel.getPixelY());


//        ghostModel = new GhostModel(map.length-2, 1,tileSize,mapModel,this);
//        ghostView = new GhostView(tileSize);
//        ghostView.setLocation(ghostModel.getPixelX(), ghostModel.getPixelY());
//        ghostController = new GhostController(ghostModel, ghostView, pacManModel);

        int[][] ghostSpawns = {
                {map.length/2-1, map.length/2+1},
                {map.length/2, map.length/2+1},
                {map.length/2+1, map.length/2+1}
        };

        for (int[] spawn : ghostSpawns) {
            GhostModel ghostModel = new GhostModel(spawn[0], spawn[1], tileSize, mapModel, this);
            GhostView ghostView = new GhostView(tileSize);
            ghostView.setLocation(ghostModel.getPixelX(), ghostModel.getPixelY());
            GhostController ghostController = new GhostController(ghostModel, ghostView, pacManModel);

            ghostModels.add(ghostModel);
            ghostViews.add(ghostView);
            ghostControllers.add(ghostController);
        }

        gamePanel = new GamePanel(rows, cols, tileSize, map, pacManView, ghostViews);

        pacManController = new PacManController(pacManModel, pacManView);
        gamePanel.attachKeyListener(pacManController.getKeyAdapter());





        mainFrame.addPanel("GamePanel", gamePanel);
        mainFrame.showPanel("GamePanel");

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);

        gamePanel.requestFocusInWindow();
        startTimeCounterThread();
    }

    public void increaseScore(){
        synchronized (this){
            score +=10;
        }
        gamePanel.updateScore(score);
    }

    private void startTimeCounterThread(){
        Thread timeCounterThread = new Thread(() -> {
            while (true){
                try{
                    Thread.sleep(1000);
                    synchronized (this){
                        timeInSeconds++;
                    }
                    gamePanel.updateTime(timeInSeconds);
                }catch (InterruptedException ex){
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        timeCounterThread.setDaemon(true);
        timeCounterThread.start();
    }
}

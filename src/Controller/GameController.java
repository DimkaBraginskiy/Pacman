package Controller;

import Model.*;
import View.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private final MainFrame mainFrame;
    private final GamePanel gamePanel;
    private final PacManModel pacManModel;
    private final PacManView pacManView;
    private final PacManController pacManController;

    private final List<GhostModel> ghostModels = new ArrayList<>();
    private final List<GhostView> ghostViews = new ArrayList<>();
    private final List<GhostController> ghostControllers = new ArrayList<>();

    private volatile int score = 0;
    private volatile int timeInSeconds = 0;
    private volatile int lifes = 3;

    private volatile boolean isEatingEnabled = false;


    public GameController(MainFrame mainFrame, int rows, int cols, int tileSize) {
        this.mainFrame = mainFrame;
        MapModel mapModel = new MapModel(rows, cols);
        int[][] map = mapModel.getMap(); // getting map from mapmodel

        pacManModel = new PacManModel(map.length/2, map.length/2+2, tileSize, mapModel, this);
        pacManView = new PacManView(tileSize);
        pacManView.setLocation(pacManModel.getPixelX(), pacManModel.getPixelY());


        int[][] ghostSpawns = {
                {map.length/2, map.length/2-2},
                {map.length/2, map.length/2},
                {map.length/2+1, map.length/2},
                {map.length/2+1, map.length/2}
        };

        String[] colors = {
                "Red",
                "Pink",
                "Blue",
                "Yellow"
        };


        int colorIndex = 0;
        for (int[] spawn : ghostSpawns) {
            GhostModel ghostModel = new GhostModel(spawn[0], spawn[1], tileSize, mapModel, this, 0.65);
            GhostView ghostView = new GhostView(tileSize, colors[colorIndex]);
            ghostView.setLocation(ghostModel.getPixelX(), ghostModel.getPixelY());
            GhostController ghostController = new GhostController(ghostModel, ghostView, pacManModel);

            ghostModels.add(ghostModel);
            ghostViews.add(ghostView);
            ghostControllers.add(ghostController);
            colorIndex++;
        }

        gamePanel = new GamePanel(rows, cols, tileSize, map, pacManView, ghostViews);

        pacManController = new PacManController(pacManModel, pacManView, this);
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
            score += 10;
        }
        gamePanel.updateScore(score);
    }

    public void increaseScore(int value){
        synchronized (this){
            score += value;
        }
        gamePanel.updateScore(score);
    }

    public void decreaseLifes(){
        synchronized (this){
            lifes--;
        }
        gamePanel.updateLifes(lifes);
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

    public void handleGameOver(){
        SwingUtilities.invokeLater(()->{
            stopAllThreads();

            GameOverDialog gameOverDialog = new GameOverDialog(mainFrame, score);
            gameOverDialog.setVisible(true);

            if(gameOverDialog.isConfirmed()){
                String name = gameOverDialog.getPlayerName();
                mainFrame.getHighScoreManager().addScore(new HighScore(name, score));
            }

            mainFrame.showPanel("MainMenu");
        });

    }

    public void stopAllThreads(){
        pacManController.stopThread();
        pacManView.stopThread();
        for(GhostController ghostModel : ghostControllers){
            ghostModel.stopThread();
        }
    }

    public void respawnAllCharacters(){
        pacManModel.resetPosition();
        pacManView.updatePosition(pacManModel.getPixelX(), pacManModel.getPixelY());

        for(int i = 0; i < ghostModels.size(); i++){
            GhostModel ghostModel = ghostModels.get(i);
            GhostView ghostView = ghostViews.get(i);

            ghostModel.resetPosition();
            ghostView.updatePosition(ghostModel.getPixelX(), ghostModel.getPixelY());
        }
    }

    public void activateEatingMode(){
        isEatingEnabled = true;
        for(GhostView ghost : ghostViews){
            ghost.isScared(true);
        }

        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }finally {
                isEatingEnabled = false;
                for(GhostView ghost : ghostViews){
                    ghost.isScared(false);
                }

            }
        }).start();
    }

    public boolean isEatingEnabled() {
        return isEatingEnabled;
    }

    public List<GhostModel> getGhostModels() {
        return ghostModels;
    }
}

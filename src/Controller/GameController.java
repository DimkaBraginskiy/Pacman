package Controller;

import Model.*;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController implements GhostStateProvider{
    private final MainFrame mainFrame;
    private final GamePanel gamePanel;
    private final PacManModel pacManModel;
    private final PacManController pacManController;


    private final List<GhostModel> ghostModels = new ArrayList<>();
    private final List<GhostController> ghostControllers = new ArrayList<>();
    private final List<Upgrade> upgrades = new ArrayList<>();


    private volatile int score = 0;
    private volatile int timeInSeconds = 0;
    private volatile int lifes = 3;

    private volatile boolean isEatingEnabled = false;
    private volatile boolean gameEnded = false;

    private Thread eatingThread;


    public GameController(MainFrame mainFrame, int rows, int cols, int tileSize) {

        this.mainFrame = mainFrame;
        MapModel mapModel = new MapModel(rows, cols);

        MapTableModel tempMapTableModel = new MapTableModel(mapModel);

        pacManModel = new PacManModel(
                mapModel.getMap().length/2,
                mapModel.getMap().length/2+2,
                tileSize,
                mapModel,
                this,
                tempMapTableModel
        );


        pacManController = new PacManController(pacManModel, this);

        int[][] ghostSpawns = {
                {mapModel.getMap().length/2, mapModel.getMap().length/2-2},
                {mapModel.getMap().length/2, mapModel.getMap().length/2},
                {mapModel.getMap().length/2+1, mapModel.getMap().length/2},
                {mapModel.getMap().length/2+1, mapModel.getMap().length/2}
        };


        String[] colors = {"Red", "Pink", "Blue", "Yellow"};
        for (int i = 0; i < ghostSpawns.length; i++) {
            int[] spawn = ghostSpawns[i];
            String color = colors[i];

            GhostModel ghostModel = new GhostModel(spawn[0],
                    spawn[1],
                    tileSize,
                    mapModel,
                    this,
                    0.20,
                    color);


            ghostModels.add(ghostModel);
        }


        gamePanel = new GamePanel(rows, cols, tileSize, mapModel, pacManController, ghostModels, this, upgrades);


        gamePanel.addKeyListener(pacManController.getKeyAdapter());


        //exit shortcut:
        gamePanel.exitShortcut(() -> {
            stopAllThreads();
            mainFrame.showPanel("MainMenu");
        });



        for(GhostModel ghostModel : ghostModels){
            GhostController ghostController = new GhostController(ghostModel, pacManModel, this);
            ghostControllers.add(ghostController);
        }



        mainFrame.addPanel("GamePanel", gamePanel);
        mainFrame.showPanel("GamePanel");

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);

        gamePanel.requestFocusInWindow();
        startTimeCounterThread();
        startUpgradeSpawner();
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

    public void activateSpeedBoost(){
        pacManModel.setMovementDelay(50);

        Thread resetSpeedThread = new Thread(() -> {
            try {
                Thread.sleep(5000); // Speed boost lasts for 5 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                pacManModel.resetMovementDelay();
            }
        });

        resetSpeedThread.setDaemon(true);
        resetSpeedThread.start();
    }

    private void startUpgradeSpawner() {
        Thread upgradeThread = new Thread(() -> {
            while (!gameEnded) {
                try {
                    Thread.sleep(5000);

                    boolean anyUpgradeSpawned = false;

                    for (GhostModel ghost : ghostModels) {
                        if (Math.random() < 0.25) {
                            int row = ghost.getY();
                            int col = ghost.getX();

                            UpgradeType type = UpgradeType.SPEED_BOOST; // test with one for now

                            Upgrade upgrade = new Upgrade(row, col, type);
                            synchronized (upgrades) {
                                upgrades.add(upgrade);
                            }
                            anyUpgradeSpawned = true;
                        }
                    }

                    if (anyUpgradeSpawned) {
                        SwingUtilities.invokeLater(() -> {
                            gamePanel.getMapRenderer().repaint();
                        });
                        Thread.sleep(100);
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        upgradeThread.setDaemon(true); // optional: won't prevent app from exiting
        upgradeThread.start();
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
        if (gameEnded) return;
        SwingUtilities.invokeLater(()->{
            stopAllThreads();

            GameOverDialog gameOverDialog = new GameOverDialog(
                    mainFrame,
                    score,
                    "Game over! :(",
                    "Enter you name to be saved to leaderboard:",
                    Color.RED);
            gameOverDialog.setVisible(true);

            if(gameOverDialog.isConfirmed()){
                String name = gameOverDialog.getPlayerName();
                mainFrame.getHighScoreManager().addScore(new HighScore(name, score));
            }

            mainFrame.showPanel("MainMenu");
        });
    }

    public void handleGameWin() {
        if (gameEnded) return;
        SwingUtilities.invokeLater(() -> {
            stopAllThreads();

            GameOverDialog gameWinDialog = new GameOverDialog(
                    mainFrame,
                    score,
                    "Congratulations!",
                    "You have collected all the dots!Enter your name for the leaderboard:",
                    Color.GREEN
            );
            gameWinDialog.setVisible(true);

            if (gameWinDialog.isConfirmed()) {
                String name = gameWinDialog.getPlayerName();
                mainFrame.getHighScoreManager().addScore(new HighScore(name, score));
            }

            mainFrame.showPanel("MainMenu");
        });
    }

    public void stopAllThreads(){
        pacManController.stopThread();

        for(GhostController ghostModel : ghostControllers){
            ghostModel.stopThread();
        }
    }

    public void respawnAllCharacters(){
        pacManModel.resetPosition();


        for(int i = 0; i < ghostModels.size(); i++){
            GhostModel ghostModel = ghostModels.get(i);


            ghostModel.resetPosition();

        }
    }


    public void activateEatingMode(){
        if (eatingThread != null && eatingThread.isAlive()) return;

        isEatingEnabled = true;
        eatingThread = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ignored) {
            } finally {
                isEatingEnabled = false;
            }
        });
        eatingThread.start();
    }

    @Override
    public boolean isGhostsScared() {
        return isEatingEnabled;
    }

    public boolean isEatingEnabled() {
        return isEatingEnabled;
    }

    public List<GhostModel> getGhostModels() {
        return ghostModels;
    }

    public MapRenderer getMapRenderer(){
        return gamePanel.getMapRenderer();
    }

    public List<Upgrade> getUpgrades() {
        return upgrades;
    }
}

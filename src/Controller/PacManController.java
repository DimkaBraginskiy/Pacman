package Controller;

import Model.Direction;
import Model.GhostModel;
import Model.MapTableModel;
import Model.PacManModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class PacManController implements  ImageProvider{
        private final PacManModel model;

        private Thread movementThread;
        private final GameController gameController;


        private int currentFrame = 0;
        private Direction currentDirection = Direction.NONE;
        //private final MapTableModel mapTableModel;
        private Thread animationThread;
        private final Map<Direction, ImageIcon[]> animationFrames;



        public PacManController(PacManModel model, GameController gameController) {
            this.model = model;

            this.gameController = gameController;
            //this.mapTableModel = mapTableModel;
            this.animationFrames = loadAnimationFrames(model.getTileSize());
            startMovementThread();
            startAnimationThread();
        }


        public void setDirection(Direction direction){
            model.setDirection(direction); // pasing direction to model
            this.currentDirection = direction;

        }

        public void startMovementThread(){

            System.out.println("Starting movement thread...");

            movementThread = new Thread(() -> {

                System.out.println("Thread running...");

                while(!Thread.currentThread().isInterrupted()){
                    try {
                        System.out.println("Before move()");

                        model.move();

                        SwingUtilities.invokeLater(() ->{
                            gameController.getMapRenderer().repaint();
                        });

                        //checkForGhostCollision();
                        Thread.sleep(180);

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            movementThread.start();
        }

        public void startAnimationThread(){
            animationThread = new Thread(() -> {
                System.out.println("Anim thread running");
                while(!Thread.currentThread().isInterrupted()){
                    try{
                        if(currentDirection != Direction.NONE){
                            currentFrame = (currentFrame + 1) % 3;

                            SwingUtilities.invokeLater(()->{
                                gameController.getMapRenderer().repaint();
                            });
                        }

                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            animationThread.start();
        }

    public void stopThread() {
        if (movementThread != null) {
            movementThread.interrupt();
        }
    }

    private Map<Direction, ImageIcon[]> loadAnimationFrames(int tileSize) {
        Map<Direction, ImageIcon[]> frames = new HashMap<>();

        for (Direction dir : Direction.values()) {
            if (dir == Direction.NONE) continue;

            ImageIcon[] icons = new ImageIcon[3]; // Assuming 3-frame animation
            for (int i = 0; i < 3; i++) {
                String path = "icons/PacMan" + dir.name() + "/PacMan" + (i + 1) + dir.name() + ".png";
                ImageIcon icon = new ImageIcon(path);
                Image scaled = icon.getImage().getScaledInstance(tileSize, tileSize, Image.SCALE_SMOOTH);
                icons[i] = new ImageIcon(scaled);
            }
            frames.put(dir, icons);
        }
        return frames;
    }


    public KeyAdapter getKeyAdapter() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("KEY PRESSED: " + e.getKeyCode());  // <--- Add this!
                Direction dir = switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> Direction.UP;
                    case KeyEvent.VK_DOWN -> Direction.DOWN;
                    case KeyEvent.VK_LEFT -> Direction.LEFT;
                    case KeyEvent.VK_RIGHT -> Direction.RIGHT;
                    default -> null;
                };
                if (dir != null) {
                    setDirection(dir);
                }
            }
        };
    }

    public void checkForGhostCollision(){
        for(GhostModel ghost : gameController.getGhostModels()){
            if(model.getX() == ghost.getX() && model.getY() == ghost.getY()){
                if(gameController.isEatingEnabled()){
                    ghost.resetPosition();
                    gameController.increaseScore(200);
                }
                else{
                    model.decreaseLife();
                    gameController.decreaseLifes();
                    gameController.respawnAllCharacters();

                    if(model.getLives() == 0 ){
                        gameController.handleGameOver();
                    }
                }


            }
        }
    }
    @Override
    public ImageIcon getCurrentImageIcon(){
            if(currentDirection == Direction.NONE){
                return animationFrames.get(Direction.RIGHT)[0];
            }
            return animationFrames.get(currentDirection)[currentFrame];
    }


    public boolean isPacManAt(int row, int col) {
        return model.getX() == col && model.getY() == row;
    }

    public int getCurrentFrame(){
            return currentFrame;
    }

    public Direction getCurrentDirection(){
            return currentDirection;
    }
}



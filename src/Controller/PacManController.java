package Controller;

import Model.Direction;
import Model.GhostModel;
import Model.PacManModel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PacManController {
        private final PacManModel model;

        private Thread movementThread;
        private final GameController gameController;


        public PacManController(PacManModel model, GameController gameController) {
            this.model = model;

            this.gameController = gameController;
            startMovementThread();
        }

        public void setDirection(Direction direction){
            model.setDirection(direction); // pasing direction to model

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

    public void stopThread() {
        if (movementThread != null) {
            movementThread.interrupt();
        }
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
}



package Controller;

import Model.Direction;
import Model.GhostModel;
import Model.PacManModel;
import View.PacManView;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PacManController {
        private final PacManModel model;
        private final PacManView view;
        private Thread movementThread;
        private final GameController gameController;


        public PacManController(PacManModel model, PacManView view, GameController gameController) {
            this.model = model;
            this.view = view;
            this.gameController = gameController;
            startMovementThread();
        }

        public void setDirection(Direction direction){
            model.setDirection(direction); // pasing direction to model
            view.setCurrentDirection(direction);
        }

        public void startMovementThread(){
            movementThread = new Thread(() -> {
                while(true){
                    model.move();

                    checkForGhostCollision();

                    SwingUtilities.invokeLater(() -> view.updatePosition(model.getPixelX(), model.getPixelY()));
                    try{
                        Thread.sleep(300);
                    }catch (InterruptedException ex){
                        Thread.currentThread().interrupt();
                        return;
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
                model.decreaseLife();
                gameController.decreaseLifes();

                if(model.getLives() == 0 ){
                    gameController.handleGameOver();
                }else{
                    //start reset....
                }

            }
        }
    }
}



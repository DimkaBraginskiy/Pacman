import javax.swing.*;

public class PacManController {
    private final PacManModel model;
    private final PacManView view;
    private Thread movementThread;


    public PacManController(PacManModel model, PacManView view) {
        this.model = model;
        this.view = view;
        startMovementThread();
    }

    public void setDirection(Direction direction){
        model.setDirection(direction);
    }

    public void startMovementThread(){
        movementThread = new Thread(() -> {
            while(true){
                model.move();
                SwingUtilities.invokeLater(() -> view.updatePosition(model.getPixelX(), model.getPixelY()));
                try{
                    Thread.sleep(100);
                }catch (InterruptedException ex){
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });
        movementThread.start();
    }
}

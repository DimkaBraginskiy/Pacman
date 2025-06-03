package View;

import Model.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

public class PacManView extends JLabel {
    private final int tileSize;
    private final EnumMap<Direction, ImageIcon[]> animationFrames;
    private volatile Direction currentDirection = Direction.NONE; //volatile - for correct thread in-order operations
    private int currentFrame = 0;
    private Thread animThread;

    public PacManView(int tileSize) {
        this.tileSize = tileSize;
        setSize(tileSize, tileSize);
        setOpaque(false); // Critical for transparency

        animationFrames = new EnumMap<>(Direction.class);
        loadAnimationFrames();


        ImageIcon pacManIcon = iconGenerate("icons/PacManRight/PacMan1Right.png",tileSize,tileSize);
        setIcon(new ImageIcon(pacManIcon.getImage()));
        setSize(tileSize, tileSize);
        startAnimationThread();

    }

    private ImageIcon iconGenerate(String path, int sizeX, int sizeY){
        ImageIcon icon = new ImageIcon(path);
        Image originImage = icon.getImage();
        Image scaledImage = originImage.getScaledInstance(sizeX, sizeY, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon();
        scaledIcon.setImage(scaledImage);
        return scaledIcon;
    }

    private void loadAnimationFrames(){
        animationFrames.put(Direction.RIGHT, new ImageIcon[]{
                iconGenerate("icons/PacManRight/PacMan1Right.png", tileSize, tileSize),
                iconGenerate("icons/PacManRight/PacMan2Right.png", tileSize, tileSize),
                iconGenerate("icons/PacManRight/PacMan3Right.png", tileSize, tileSize)
        });

        animationFrames.put(Direction.LEFT, new ImageIcon[]{
                iconGenerate("icons/PacManLeft/PacMan1Left.png", tileSize, tileSize),
                iconGenerate("icons/PacManLeft/PacMan2Left.png", tileSize, tileSize),
                iconGenerate("icons/PacManLeft/PacMan3Left.png", tileSize, tileSize)
        });

        animationFrames.put(Direction.UP, new ImageIcon[]{
                iconGenerate("icons/PacManUp/PacMan1Up.png", tileSize, tileSize),
                iconGenerate("icons/PacManUp/PacMan2Up.png", tileSize, tileSize),
                iconGenerate("icons/PacManUp/PacMan3Up.png", tileSize, tileSize)
        });

        animationFrames.put(Direction.DOWN, new ImageIcon[]{
                iconGenerate("icons/PacManDown/PacMan1Down.png", tileSize, tileSize),
                iconGenerate("icons/PacManDown/PacMan2Down.png", tileSize, tileSize),
                iconGenerate("icons/PacManDown/PacMan3Down.png", tileSize, tileSize)
        });
    }

    private void startAnimationThread(){
        animThread = new Thread(() -> {
            while(true){
                if(currentDirection != Direction.NONE){
                    SwingUtilities.invokeLater(()->{
                        ImageIcon[] frames = animationFrames.get(currentDirection);
                        setIcon(frames[currentFrame]);

                    });
                    currentFrame = (currentFrame + 1) % 3; //?????
                }
                try{
                    Thread.sleep(150);
                }catch (InterruptedException ex){
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        animThread.start();
    }

    public void updatePosition(int x, int y){
        setLocation(x,y);
    }

    public void setCurrentDirection(Direction direction){
        this.currentDirection = direction;
        if(direction != Direction.NONE){
            currentFrame = 0; // Resetting the animation
        }
    }





}

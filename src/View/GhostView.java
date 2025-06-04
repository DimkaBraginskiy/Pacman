package View;

import Model.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

public class GhostView extends JLabel {
    private final int tileSize;
    private final EnumMap<Direction, ImageIcon[]> animationFrames;
    private volatile Direction currentDirection = Direction.NONE; //volatile - for correct thread in-order operations
    private int currentFrame = 0;
    private Thread animThread;

    private boolean isScared = false;
    private ImageIcon scaredIcon;


    public GhostView(int tileSize, String ghostColor){
        this.tileSize = tileSize;

        setSize(tileSize, tileSize);
        setOpaque(false); // Critical for transparency

        animationFrames = new EnumMap<>(Direction.class);
        loadAnimationFrames(ghostColor);
        scaredIcon = iconGenerate("icons/Ghosts/GhostToEat.png", tileSize, tileSize);


        currentDirection = Direction.RIGHT;
        setIcon(animationFrames.get(currentDirection)[0]);
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

    private void loadAnimationFrames(String ghostColor){
        for (Direction dir : Direction.values()) {
            if (dir == Direction.NONE) continue;

            ImageIcon[] frames = new ImageIcon[2]; // Adjust if you have more frames
            for (int i = 0; i < frames.length; i++) {
                String filePath = String.format("icons/Ghosts/%sGhost%s.png", ghostColor, dir.name().charAt(0) + dir.name().substring(1).toLowerCase());
                System.out.println(filePath);
                frames[i] = iconGenerate(filePath, tileSize, tileSize);
            }
            animationFrames.put(dir, frames);
        }
    }

    private void startAnimationThread() {
        animThread = new Thread(() -> {
            while (true) {
                SwingUtilities.invokeLater(() -> {
                    if(isScared){
                        setIcon(scaredIcon);

                    } else if (currentDirection != Direction.NONE) {
                        ImageIcon[] frames = animationFrames.get(currentDirection);
                        if (frames != null && frames.length > 0) {
                            setIcon(frames[currentFrame % frames.length]);

                        }
                    }
                });

                currentFrame++;

                try {
                    Thread.sleep(150);
                } catch (InterruptedException ex) {
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
    }

    public void isScared(Boolean isScared){
        this.isScared =  isScared;
    }
}

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

    public GhostView(int tileSize){
        this.tileSize = tileSize;

        setSize(tileSize, tileSize);
        setOpaque(false); // Critical for transparency

        animationFrames = new EnumMap<>(Direction.class);
        //loadAnimationFrames();


        ImageIcon pacManIcon = iconGenerate("icons/Ghosts/RedGhostRight.png",tileSize,tileSize);
        setIcon(new ImageIcon(pacManIcon.getImage()));
        setSize(tileSize, tileSize);
        //startAnimationThread();
    }

    private ImageIcon iconGenerate(String path, int sizeX, int sizeY){
        ImageIcon icon = new ImageIcon(path);
        Image originImage = icon.getImage();
        Image scaledImage = originImage.getScaledInstance(sizeX, sizeY, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon();
        scaledIcon.setImage(scaledImage);
        return scaledIcon;
    }

    public void updatePosition(int x, int y){
        setLocation(x,y);
    }
}

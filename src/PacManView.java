import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PacManView extends JLabel {
    public PacManView(int tileSize) {
        setSize(tileSize, tileSize);
        setOpaque(false); // Critical for transparency

        // Visual debugging
        ImageIcon pacManIcon = iconGenerate("icons/PacManRight/PacMan1Right.png",tileSize,tileSize);
        setIcon(new ImageIcon(pacManIcon.getImage()));
        setSize(tileSize, tileSize);

    }

    public void updatePosition(int x, int y){
        setLocation(x,y);
    }

    private ImageIcon iconGenerate(String path, int sizeX, int sizeY){
        ImageIcon icon = new ImageIcon(path);
        Image originImage = icon.getImage();
        Image scaledImage = originImage.getScaledInstance(sizeX, sizeY, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon();
        scaledIcon.setImage(scaledImage);
        return scaledIcon;
    }
}

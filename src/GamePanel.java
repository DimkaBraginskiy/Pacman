import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public GamePanel(int windowWidth, int windowHeight, int tileWidth, int tileHeight) {
        setLayout(null);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setBounds(0, 0, windowWidth, windowHeight);
    }
}

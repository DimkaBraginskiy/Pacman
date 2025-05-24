import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private MapModel mapModel;
    private MapRenderer mapRenderer;


    public GamePanel(int rows, int cols, int tileSize) {
        setLayout(null);
        setBackground(Color.BLACK);

        int panelWidth = tileSize * cols;
        int panelHeight = tileSize * rows;
        setPreferredSize(new Dimension(panelWidth, panelHeight));

        mapModel = new MapModel(rows, cols);
        int[][] map = mapModel.getMap();

        mapRenderer = new MapRenderer(map, rows, cols, tileSize);

        add(mapRenderer);
        setFocusable(true);
    }
}

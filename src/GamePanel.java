import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private MapModel mapModel;
    private MapRenderer mapRenderer;


    public GamePanel(int rows, int cols) {
        setLayout(null);
        setBackground(Color.BLACK);

        int panelWidth = 45 * cols;
        int panelHeight = 45 * rows;
        setPreferredSize(new Dimension(panelWidth, panelHeight));

        mapModel = new MapModel(rows, cols);
        int[][] map = mapModel.getMap();

        mapRenderer = new MapRenderer(map, rows, cols);

        add(mapRenderer);
        setFocusable(true);
    }
}

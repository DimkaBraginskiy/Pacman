import newMapApproach.NewMapRenderer;
import oldMapApproach.MapModel;
import oldMapApproach.MapRenderer;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private MapModel mapModel;
    //private MapRenderer mapRenderer;
    private NewMapRenderer mapRenderer;


    public GamePanel(int rows, int cols, int tileSize) {
        setLayout(null);
        setBackground(Color.BLACK);

        int panelWidth = tileSize * cols;
        int panelHeight = tileSize * rows;
        setPreferredSize(new Dimension(panelWidth, panelHeight));

        mapModel = new MapModel(rows, cols);
        int[][] map = mapModel.getMap();

        mapRenderer = new NewMapRenderer(map, rows, cols, tileSize);

        mapRenderer.setBounds(0, 0, panelWidth, panelHeight);

        add(mapRenderer);
        setFocusable(true);
    }
}

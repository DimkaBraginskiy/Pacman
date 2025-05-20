import javax.swing.*;
import java.awt.*;

public class MapRenderer extends JPanel {
    private MapModel mapModel;
    private final int cellSize = 45;
    private int[][] map;
    private JLabel[][] mapLabels;

    public MapRenderer(int[][] map, int rows, int cols) {
        this.map = map;
        this.mapLabels = new JLabel[rows][cols];

        setLayout(null);
        setBackground(Color.BLACK);
        setBounds(0, 0, cols * cellSize, rows * cellSize);

        initializeCells();
    }

    private void initializeCells(){
        for(int row = 0; row < map.length; row++){
            for(int col = 0; col < map[0].length; col++){
                JLabel cell = new JLabel();
                cell.setBounds(col * cellSize, row * cellSize, cellSize, cellSize);

                switch (map[row][col]){
                    case 1:
                        cell.setIcon(iconGenerate("icons/Walls/WallHorizontal.png", cellSize, cellSize));
                        break;
                    case 2:
                        cell.setIcon(iconGenerate("icons/Walls/WallVertical.png", cellSize, cellSize));
                        break;
                    case 3:
                        cell.setIcon(iconGenerate("icons/Walls/LeftUpClose.png", cellSize, cellSize));
                        break;
                    case 4:
                        cell.setIcon(iconGenerate("icons/Walls/RightUpClose.png", cellSize, cellSize));
                        break;
                    case 5:
                        cell.setIcon(iconGenerate("icons/Walls/LeftDownClose.png", cellSize, cellSize));
                        break;
                    case 6:
                        cell.setIcon(iconGenerate("icons/Walls/RightDownClose.png", cellSize, cellSize));
                        break;
                    case 0:
                        cell.setIcon(iconGenerate("icons/Dots/SmallDot.png", cellSize, cellSize));
                        break;
                }

                add(cell);
                mapLabels[row][col] = cell;
            }
        }
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

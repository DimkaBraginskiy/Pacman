    package View;

    import Model.MapModel;

    import javax.swing.*;
    import javax.swing.table.DefaultTableCellRenderer;
    import java.awt.*;

    public class MapCellRenderer extends DefaultTableCellRenderer {
        private final int cellSize;
        private final MapModel mapModel;

        public MapCellRenderer(int cellSize, MapModel mapModel) {
            this.cellSize = cellSize;
            this.mapModel = mapModel;
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row, int column) {

            JLabel label = new JLabel();
            label.setOpaque(true);
            label.setBackground(Color.BLACK);

            int cellValue = (int) value;

            if(mapModel.isPacManAt(row, column)){
                String path = "icons/PacManRight/PacMan1Right.png";
                ImageIcon icon = iconGenerate(path,cellSize,cellSize);
                label.setIcon(icon);
                return label;
            }

            String path = switch (cellValue) {
                case 1 -> "icons/Walls/Wall.png";
                case 2 -> "icons/Dots/SmallDot.png";
                case 3 -> "icons/Dots/BigDot.png";
                case -1 -> "icons/Walls/EmptyTile.png";
                default -> null;
            };



            if(path != null) {
                ImageIcon icon = new ImageIcon(path);

                Image scaled =icon.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(scaled));
            }
            return label;
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

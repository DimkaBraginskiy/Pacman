    package View;

    import javax.swing.*;
    import javax.swing.table.DefaultTableCellRenderer;
    import java.awt.*;

    public class MapCellRenderer extends DefaultTableCellRenderer {
        private final int cellSize;

        public MapCellRenderer(int cellSize) {
            this.cellSize = cellSize;
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

            int cellValue = (int) value;
            String path = switch (cellValue) {
                case -1 -> "icons/Walls/EmptyTile.png";
                case 1 -> "icons/Walls/WallHorizontal.png";
                case 2 -> "icons/Walls/WallVertical.png";
                case 3 -> "icons/Walls/LeftUpClose.png";
                case 4 -> "icons/Walls/RightUpClose.png";
                case 5 -> "icons/Walls/LeftDownClose.png";
                case 6 -> "icons/Walls/RightDownClose.png";
                case 7 -> "icons/Walls/HorizontalClosedRight.png";
                case 8 -> "icons/Walls/HorizontalClosedLeft.png";
                case 9 -> "icons/Walls/VerticalClosedUp.png";
                case 10 -> "icons/Walls/VerticalClosedDown.png";
                case 11 -> "icons/Walls/TriConnectorLeft.png";
                case 12 -> "icons/Walls/TriConnectorRight.png";
                case 13 -> "icons/Walls/TriConnectorUp.png";
                case 14 -> "icons/Walls/TriConnectorDown.png";
                case 0 -> "icons/Dots/SmallDot.png";
                default -> null;
            };

            if(path != null) {
                ImageIcon icon = new ImageIcon(path);
                Image scaled =icon.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(scaled));
            }
            return label;
        }
    }

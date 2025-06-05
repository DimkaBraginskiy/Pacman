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
    }

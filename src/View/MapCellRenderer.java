    package View;

    import Controller.ImageProvider;
    import Controller.PacManController;
    import Model.GhostModel;
    import Model.MapModel;

    import javax.swing.*;
    import javax.swing.table.DefaultTableCellRenderer;
    import java.awt.*;
    import java.util.List;


    public class MapCellRenderer extends DefaultTableCellRenderer {
        private final int cellSize;
        private final MapModel mapModel;
        private final ImageProvider pacManImageProvider;
        private final List<GhostModel> ghostModels;

        public MapCellRenderer(int cellSize, MapModel mapModel, ImageProvider pacManImageProvider, List<GhostModel> ghostModels) {
            this.cellSize = cellSize;
            this.mapModel = mapModel;
            this.pacManImageProvider = pacManImageProvider;
            this.ghostModels = ghostModels;
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
                ImageIcon icon = pacManImageProvider.getCurrentImageIcon();
                if (icon != null) {
                    label.setIcon(icon);
                    return label;
                }
            }

            for (GhostModel ghost : ghostModels) {
                if (ghost.getY() == row && ghost.getX() == column) {
                    ImageIcon ghostIcon = new ImageIcon("icons/Ghosts/PinkGhostRight.png");
                    Image scaled = ghostIcon.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(scaled));
                    return label;
                }
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

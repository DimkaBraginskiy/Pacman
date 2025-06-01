package View;

import Model.MapTableModel;

import javax.swing.*;
import java.awt.*;

public class MapRenderer extends JPanel {
    private JTable table;
    private MapTableModel tableModel;

    public MapRenderer(int[][] map, int rows, int cols, int cellSize) {
        setLayout(new BorderLayout());
        tableModel = new MapTableModel(map, rows, cols);
        table = new JTable(tableModel);

        // Set sizes
        table.setRowHeight(cellSize);
        table.setEnabled(false);

        // Set columns widths & renderers
        for (int i = 0; i < cols; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(cellSize);
            table.getColumnModel().getColumn(i).setMinWidth(cellSize);
            table.getColumnModel().getColumn(i).setMaxWidth(cellSize);
            table.getColumnModel().getColumn(i).setCellRenderer(new MapCellRenderer(cellSize));
        }

        // Remove table header and grid
        table.setTableHeader(null);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));

        // Make table non-scrollable (important!)
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        add(table, BorderLayout.CENTER);

        // Set preferred size explicitly to avoid layout stretching
        setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
        table.setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
    }

    public MapTableModel getModel() {
        return tableModel;
    }

}

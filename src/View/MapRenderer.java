package View;

import Model.MapModel;
import Model.MapTableModel;

import javax.swing.*;
import java.awt.*;

public class MapRenderer extends JPanel {
    private JTable table;
    private MapTableModel tableModel;

    public MapRenderer(MapModel mapModel, int rows, int cols, int cellSize) {
        setLayout(new BorderLayout());

        this.tableModel = new MapTableModel(mapModel);
        this.table = new JTable(tableModel);

        // Set sizes
        table.setRowHeight(cellSize);
        table.setEnabled(false);
        table.setTableHeader(null);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        // Set columns widths & renderers
        for (int i = 0; i < cols; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(cellSize);
            table.getColumnModel().getColumn(i).setMinWidth(cellSize);
            table.getColumnModel().getColumn(i).setMaxWidth(cellSize);

            table.getColumnModel().getColumn(i).setCellRenderer(new MapCellRenderer(cellSize));
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Color.BLACK);
        add(scrollPane, BorderLayout.CENTER);

        add(table, BorderLayout.CENTER);
        //setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
    }

    public MapTableModel getModel() {
        return tableModel;
    }

}

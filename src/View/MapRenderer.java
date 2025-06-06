package View;

import Controller.GhostStateProvider;
import Controller.ImageProvider;
import Controller.PacManController;
import Model.GhostModel;
import Model.MapModel;
import Model.MapTableModel;

import javax.swing.*;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class MapRenderer extends JPanel {
    private JTable table;
    private MapTableModel tableModel;
    private final MapModel mapModel;
    private final ImageProvider pacManImageProvider;

    public MapRenderer(MapModel mapModel, int rows, int cols, int cellSize, ImageProvider pacManimageProvider, List<GhostModel> ghostModels,
                       GhostStateProvider ghostStateProvider) {
        setLayout(new BorderLayout());

        this.pacManImageProvider = pacManimageProvider;

        this.mapModel = mapModel;
        this.tableModel = new MapTableModel(mapModel);
        this.table = new JTable(tableModel);


        // Set sizes
        //table.setRowHeight(cellSize);
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

            table.getColumnModel().getColumn(i).setCellRenderer(
                    new MapCellRenderer(cellSize, mapModel, pacManimageProvider, ghostModels, ghostStateProvider));
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Color.BLACK);
        add(scrollPane, BorderLayout.CENTER);

        resizeTableCells(getWidth(), getHeight(), ghostModels, ghostStateProvider);


        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeTableCells(getWidth(),getHeight(), ghostModels, ghostStateProvider);
            }
        });


        //add(table, BorderLayout.CENTER);
        //setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));

        //FOR PROPER RESIZING FROM THE BEGINNING.
        SwingUtilities.invokeLater(() -> {
            resizeTableCells(getWidth(), getHeight(), ghostModels, ghostStateProvider);
        });
    }

    private void resizeTableCells(int width, int height, List<GhostModel> ghostModels, GhostStateProvider ghostStateProvider){
        int rows = mapModel.getRows();
        int cols = mapModel.getCols();

        if (rows <= 0 || cols <= 0) return;

        int cellWidth =  width / cols;
        int cellHeight = height / rows;
        int newSize = Math.max(1, Math.min(cellWidth, cellHeight));

        table.setRowHeight(newSize);
        for(int i = 0; i < cols; i++){
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(newSize);
            column.setMinWidth(newSize);
            column.setMaxWidth(newSize);
            column.setCellRenderer(new MapCellRenderer(newSize, mapModel, pacManImageProvider, ghostModels, ghostStateProvider));
        }

        revalidate();
        repaint();
    }

    public MapTableModel getTableModel() {
        return tableModel;
    }
}

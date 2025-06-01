package Model;

import javax.swing.table.AbstractTableModel;

public class MapTableModel extends AbstractTableModel {
    private final int[][] map;
    private final int rows;
    private final int cols;

    public MapTableModel(int[][] map, int rows, int cols){
        this.map = map;
        this.rows = rows;
        this.cols = cols;
    }


    @Override
    public int getRowCount() {
        return rows;
    }

    @Override
    public int getColumnCount() {
        return cols;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return map[rowIndex][columnIndex];
    }

    public int getCellValue(int rowIndex, int columnIndex) {
        return map[rowIndex][columnIndex];
    }

    public void setValueAt(int value, int row, int col) {
        map[row][col] = value;
        fireTableCellUpdated(row, col);
    }

}

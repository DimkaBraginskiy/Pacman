package Model;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.EventListener;

public class MapTableModel extends AbstractTableModel {
    private final MapModel mapModel;

    public MapTableModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }


    @Override
    public int getRowCount(){
        return mapModel.getRows();
    }

    @Override
    public int getColumnCount(){
        return mapModel.getCols();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        return mapModel.getCell(rowIndex, columnIndex);
    }

    public void setValueAt(int rowIndex, int columnIndex, int value){
        mapModel.setCell(rowIndex, columnIndex, value);
        fireTableCellUpdated(rowIndex, columnIndex); // repainting table cell
    }

    public MapModel getMapModel() {
        return mapModel;
    }
}
